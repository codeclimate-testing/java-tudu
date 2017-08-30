package tudu.domain.configuration;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public class DataSourceFactory {

    private final Log log = LogFactory.getLog(DataSourceFactory.class);

    private String jndiDatasourceName;

    private DataSource mysqlDataSource;

    private DataSource hsqldbDataSource;

    private DataSource currentDataSource = null;

    private HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();

    public void setJndiDatasourceName(String jndiDatasourceName) {
        this.jndiDatasourceName = jndiDatasourceName;
    }

    public void setHsqldbDataSource(DataSource hsqldbDataSource) {
        this.hsqldbDataSource = hsqldbDataSource;
    }

    public void setMysqlDataSource(DataSource mysqlDataSource) {
        this.mysqlDataSource = mysqlDataSource;
    }

    public void init() {
        log.warn("Initializing database connection pool.");
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource jndiDataSource = (DataSource) envContext
                    .lookup(this.jndiDatasourceName);

            Connection conn = jndiDataSource.getConnection();
            conn.close();
            this.currentDataSource = jndiDataSource;
            this.hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
            log.warn("MySQL database found in JNDI.");
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
            findMysqlDataSource();
        }
    }

    /**
     * Find a MySQL instance and create a datasource from it.
     */
    private void findMysqlDataSource() {
        log.warn("No database configured in JNDI at \"java:/comp/env"
                + this.jndiDatasourceName + "\"");

        try {
            Connection conn = mysqlDataSource.getConnection();
            conn.close();
            this.currentDataSource = mysqlDataSource;
            this.hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
            log.warn("MySQL database found.");
        } catch (SQLException mysqlE) {
            log.warn("MySQL database could not be found");
            if (log.isDebugEnabled()) {
                mysqlE.printStackTrace();
            }
            launchHsqldbDataSource();
        }
    }

    /**
     * Launch an in-memory HSQLDB database, and create a datasource from it.
     */
    private void launchHsqldbDataSource() {
        this.hibernateJpaVendorAdapter.setDatabase(Database.HSQL);
        this.hibernateJpaVendorAdapter.setGenerateDdl(true);
        this.hibernateJpaVendorAdapter.setShowSql(true);
        try {
            Connection conn = hsqldbDataSource.getConnection();
            conn.close();
            this.currentDataSource = hsqldbDataSource;
            log.warn("HSQLDB database started.");
        } catch (SQLException sqle) {
            log.warn("HSQLDB database could not be started : "
                    + sqle.getMessage());

            sqle.printStackTrace();
            log.fatal("No database could be used, the application cannot run!");
        }
    }

    public DataSource getDataSource() {
        return this.currentDataSource;
    }

    public HibernateJpaVendorAdapter getHibernateJpaVendorAdapter() {
        return this.hibernateJpaVendorAdapter;
    }
}
