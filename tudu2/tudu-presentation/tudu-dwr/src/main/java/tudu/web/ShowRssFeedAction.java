package tudu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import tudu.domain.model.TodoList;
import tudu.service.TodoListsManager;

/**
 * Generate the RSS feed.
 * 
 * @author Julien Dubois
 */
public class ShowRssFeedAction extends Action {

    private final Log log = LogFactory.getLog(ShowRssFeedAction.class);

    private TodoListsManager todoListsManager = null;

    public void setTodoListsManager(TodoListsManager todoListsManager) {
        this.todoListsManager = todoListsManager;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        log.debug("Execute action");
        DynaActionForm todoListForm = (DynaActionForm) form;
        String listId = (String) todoListForm.get("listId");

        TodoList todoList = todoListsManager.unsecuredFindTodoList(listId);

        if (todoList.isRssAllowed()) {
            if (log.isDebugEnabled()) {
                log.debug("Rendering RSS feed for Todo List ID '"
                        + todoList.getListId() + "', named '"
                        + todoList.getName() + "'");
            }

            request.setAttribute("todoList", todoList);
            request.setAttribute("link", request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/secure/showTodos.action");

            return mapping.findForward("rssFeed");
        } else {
            if (log.isDebugEnabled()) {
                log.debug("Rendering RSS feed for Todo List ID '"
                        + todoList.getListId() + "' is not allowed");
            }
            return mapping.findForward("notAllowed");
        }
    }
}
