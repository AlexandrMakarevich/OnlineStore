import com.tacitknowledge.util.migration.MigrationException;
import com.tacitknowledge.util.migration.jdbc.AutoPatchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-store-application.xml")
public class TestDbPatching {

    @Autowired
    private DataSource dataSource;

    @Test
    public void test() throws MigrationException {
        AutoPatchService autoPatchService = new AutoPatchService();
        autoPatchService.setDataSource(dataSource);
        autoPatchService.setPatchPath("db_scripts");
        autoPatchService.setSystemName("storeAdmin");
        autoPatchService.setDatabaseType("mysql");
//        autoPatchService.patch();
    }

    @Test
    public void test1() {

    }
}
