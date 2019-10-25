package app.service;

import app.database.connection.ConnectionFactory;
import app.database.connection.Props;
import app.exception.AppException;
import app.literals.Constants;
import app.model.ConnectionHolder;
import app.model.TreeHolder;
import app.parsing.json.Factory;
import app.structure.model.Item;
import app.structure.model.TreeModel;
import app.structure.model.database.RootDatabasesTreeNode;
import app.structure.search.BreadthFirstSearcher;
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
     * @param ip       Database server IP.
     * @param port     Database server port.
     * @param login    Database server user name.
     * @param password Database server password.
     * @return JSON array with root tree node and connection driver version,
     * or empty JSON array in case exception to connect database.
     */
    public String createConnection(String ip, String port, String login, String password) {
        StringBuilder builder = new StringBuilder();
        builder.append(Constants.START_JSON_ARRAY);
        Props props = new Props(
            Constants.URL_PREFIX.concat(ip).concat(Constants.URL_DIVIDER).concat(port).concat(Constants.URL_SUFFIX),
            login, password, Constants.DRIVER);

        Connection connection = null;

        try {
            connection = ConnectionFactory.getConnection(props);
        } catch (AppException e) {
            LOGGER.info("Can't create connection! \n".concat(props.toString()));
        }

        if (connection == null) {
            builder.append(Constants.FINISH_JSON_ARRAY);
            return builder.toString();
        }

        connectionHolder.setConnection(connection);

        TreeModel treeModel = new TreeModel(new BreadthFirstSearcher());
        treeModel.add(new RootDatabasesTreeNode(new Item()));
        treeHolder.setTreeModel(treeModel);

        builder.append(treeModel.getRoot().toJSON());

        try {
            builder.append(Constants.COMMA);
            builder.append(Constants.START_JSON);
            Factory.appendKeyValue(builder,
                Constants.CONNECTOR,
                connection.getMetaData().getDriverVersion());
            builder.append(Constants.FINISH_JSON);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        builder.append(Constants.FINISH_JSON_ARRAY);
        return builder.toString();
    }
}
