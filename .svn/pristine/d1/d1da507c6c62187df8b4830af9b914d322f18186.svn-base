package tudu.web;

import java.util.Collection;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.service.UserManager;
import tudu.Constants;

/**
 * Show the Todos belonging to the current List.
 * 
 * @author Julien Dubois
 */
public class ShowTodosAction extends Action {

    private final Log log = LogFactory.getLog(ShowTodosAction.class);

    private UserManager userManager = null;

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Show the Todos main page.
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        log.debug("Execute show action");
        User user = userManager.getCurrentUser();
        Collection<TodoList> todoLists = new TreeSet<TodoList>(user
                .getTodoLists());

        if (!todoLists.isEmpty()) {
            String listId = request.getParameter("listId");
            if (listId != null) {
                request.setAttribute("defaultList", listId);
            } else {
                request.setAttribute("defaultList", todoLists.iterator().next()
                        .getListId());
            }
        }

        String dateFormat = user.getDateFormat();
        request.getSession().setAttribute("dateFormat", dateFormat);
        String calendarDateFormat = "%m/%d/%Y";
        if (Constants.DATEFORMAT_US_SHORT.equals(dateFormat)) {
            calendarDateFormat = "%m/%d/%y";
        } else if (Constants.DATEFORMAT_FRENCH.equals(dateFormat)) {
            calendarDateFormat = "%d/%m/%Y";
        } else if (Constants.DATEFORMAT_FRENCH_SHORT.equals(dateFormat)) {
            calendarDateFormat = "%d/%m/%y";
        }
        request.getSession().setAttribute("calendarDateFormat",
                calendarDateFormat);

        return mapping.findForward("show");
    }
}
