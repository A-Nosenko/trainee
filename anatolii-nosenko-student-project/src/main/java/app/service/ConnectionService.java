package app.service;

import app.database.connection.ConnectionFactory;
import app.database.connection.Props;
import app.exception.AppException;
import app.literals.Constants;
import app.model.ConnectionHolder;
import app.model.ConnectionPostDto;
import app.model.ConnectionPostDtoResponse;
import app.model.TreeHolder;
import app.structure.model.Item;
import app.structure.model.TreeModel;
import app.structure.model.database.RootDatabasesTreeNode;
import app.structure.search.BreadthFirstSearcher;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to create connection and initialise tree in TreeHolder bean.
 */
@Service
public class ConnectionService {
    private static final Logger LOGGER = Logger.getLogger(ConnectionService.class.getName());

    @Autowired
    private TreeHolder treeHolder;

    @Autowired
    private ConnectionHolder connectionHolder;


    /**
     * Method to initialise database connection and create root tree node.
     *
     * @param connectionPostDto Object with IP, port, login and password to connect database.
     * @return Object with root tree node and connection driver version
     */
    public ConnectionPostDtoResponse createConnection(ConnectionPostDto connectionPostDto) {
        Props props = new Props(
            Constants.URL_PREFIX.concat(connectionPostDto.getIp())
                .concat(Constants.URL_DIVIDER).concat(connectionPostDto.getPort())
                .concat(Constants.URL_SUFFIX),
            connectionPostDto.getLogin(), connectionPostDto.getPassword(), Constants.DRIVER);

        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection(props);
        } catch (AppException e) {
            LOGGER.info("Can't create connection! \n".concat(props.toString()));
        }

        connectionHolder.setConnection(connection);

        if (connection == null) {
            treeHolder.setTreeModel(null);
            return null;
        }

        TreeModel treeModel = new TreeModel(new BreadthFirstSearcher());
        treeModel.add(new RootDatabasesTreeNode(new Item()));
        treeHolder.setTreeModel(treeModel);

        Gson gson = new Gson();
        ConnectionPostDtoResponse response = new ConnectionPostDtoResponse();
        response.setRoot(gson.toJson(treeModel.getRoot()));

        try {
            response.setConnector(connection.getMetaData().getDriverVersion());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return response;
    }
}
