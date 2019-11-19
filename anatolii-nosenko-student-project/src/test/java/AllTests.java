import app.StartTest;
import app.database.connection.ConnectionFactoryTest;
import app.database.query.QueryManagerTest;
import app.exception.AppExceptionTest;
import app.file.work.TextReaderTest;
import app.file.work.TextWriterTest;
import app.parsing.xml.TreeSaverToXMLTest;
import app.parsing.xml.TreeLoaderFromXMLTest;
import app.structure.model.ItemTest;
import app.structure.model.TreeModelTest;
import app.structure.model.TreeNodeTest;
import app.structure.model.base.node.BaseTreeNodeTest;
import app.structure.model.database.ColumnDatabaseTreeNodeTest;
import app.structure.model.database.ColumnsDatabaseTreeNodeTest;
import app.structure.model.database.DBTreeNodeTest;
import app.structure.model.database.DatabaseTreeNodeTest;
import app.structure.model.database.ForeignKeyDatabaseTreeNodeTest;
import app.structure.model.database.FunctionDatabaseTreeNodeTest;
import app.structure.model.database.FunctionsDatabaseTreeNodeTest;
import app.structure.model.database.RootDatabasesTreeNodeTest;
import app.structure.model.database.StoredProcedureDatabaseTreeNodeTest;
import app.structure.model.database.StoredProceduresDatabaseTreeNodeTest;
import app.structure.model.database.TableDatabaseTreeNodeTest;
import app.structure.model.database.TablesDatabaseTreeNodeTest;
import app.structure.model.database.TriggerDatabaseTreeNodeTest;
import app.structure.model.database.TriggersDatabaseTreeNodeTest;
import app.structure.model.database.ViewDatabaseTreeNodeTest;
import app.structure.model.database.ViewsDatabaseTreeNodeTest;
import app.structure.search.BreadthFirstSearcherTest;
import app.structure.search.DepthFirstSearcherTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( {
    ConnectionFactoryTest.class,
    QueryManagerTest.class,
    AppExceptionTest.class,
    TextWriterTest.class,
    TextReaderTest.class,
    TreeSaverToXMLTest.class,
    TreeLoaderFromXMLTest.class,
    BaseTreeNodeTest.class,
    BreadthFirstSearcherTest.class,
    DepthFirstSearcherTest.class,
    ItemTest.class,
    TreeModelTest.class,
    TreeNodeTest.class,
    DBTreeNodeTest.class,
    ColumnDatabaseTreeNodeTest.class,
    ColumnsDatabaseTreeNodeTest.class,
    DatabaseTreeNodeTest.class,
    ForeignKeyDatabaseTreeNodeTest.class,
    FunctionDatabaseTreeNodeTest.class,
    FunctionsDatabaseTreeNodeTest.class,
    RootDatabasesTreeNodeTest.class,
    StoredProcedureDatabaseTreeNodeTest.class,
    StoredProceduresDatabaseTreeNodeTest.class,
    TableDatabaseTreeNodeTest.class,
    TablesDatabaseTreeNodeTest.class,
    TriggerDatabaseTreeNodeTest.class,
    TriggersDatabaseTreeNodeTest.class,
    ViewDatabaseTreeNodeTest.class,
    ViewsDatabaseTreeNodeTest.class,
    StartTest.class
})

public class AllTests {
}
