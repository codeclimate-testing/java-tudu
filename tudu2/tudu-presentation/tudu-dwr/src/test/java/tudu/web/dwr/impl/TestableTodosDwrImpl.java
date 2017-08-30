package tudu.web.dwr.impl;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Testable version of the TodosDwrImpl bean, as the renderTodos method cannot
 * be tested easily.
 * 
 * @author Julien Dubois
 * @see tudu.web.dwr.impl.TodosDwrImpl
 */
public class TestableTodosDwrImpl extends TodosDwrImpl {

    @Override
    public String renderTodos(String listId, Date tableDate) {
        return "";
    }

    @Override
    protected SimpleDateFormat getDateFormatter() {
        return new SimpleDateFormat("MM/dd/yyyy");
    }
}
