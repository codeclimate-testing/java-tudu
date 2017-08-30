package tudu.web.servlet;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

/**
 * Uses DBUnit to dump the database.
 * 
 * @author Julien Dubois
 */
public class DumpDatabaseServlet extends HttpServlet {

    private static final long serialVersionUID = 5693740223035300849L;

    protected final void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        Writer writer = response.getWriter();
        try {
            Context ctx = new InitialContext();
            DataSource dataSource = (DataSource) ctx
                    .lookup("java:comp/env/jdbc/tudu");
            
            Connection jdbcConnection = dataSource.getConnection();
            IDatabaseConnection connection = new DatabaseConnection(
                    jdbcConnection);
            
            IDataSet fullDataSet = connection.createDataSet();
            response.setContentType("Content-Type: application/force-download");
            FlatXmlDataSet.write(fullDataSet, writer);
        } catch (Exception e) {
            writer.write("An error has occured : " + e.getMessage());
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}
