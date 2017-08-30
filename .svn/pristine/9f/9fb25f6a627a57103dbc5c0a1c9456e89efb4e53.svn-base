package tudu.web.servlet;

import java.io.Writer;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.web.servlet.FrameworkServlet;

/**
 * Uses DBUnit to dump the database.
 * 
 * @author Julien Dubois
 */
public class DumpDatabaseServlet extends FrameworkServlet {

    private static final long serialVersionUID = 5693740223035300849L;

    @Override
    protected void doService(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws Exception {
        Writer writer = httpServletResponse.getWriter();
        try {
            DataSource dataSource = (DataSource) this
                    .getWebApplicationContext().getBean("dataSource");
            Connection jdbcConnection = dataSource.getConnection();
            IDatabaseConnection connection = new DatabaseConnection(
                    jdbcConnection);

            IDataSet fullDataSet = connection.createDataSet();
            httpServletResponse
                    .setContentType("Content-Type: application/force-download");
            FlatXmlDataSet.write(fullDataSet, writer);
        } catch (Exception e) {
            writer.write("An error has occured : " + e.getMessage());
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}
