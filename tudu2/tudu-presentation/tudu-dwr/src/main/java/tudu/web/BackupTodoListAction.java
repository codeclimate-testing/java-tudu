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
import org.jdom.Document;

import tudu.domain.model.TodoList;
import tudu.service.TodoListsManager;

/**
 * Backup a Todo List.
 * 
 * @author Julien Dubois
 */
public class BackupTodoListAction extends Action {

    private final Log log = LogFactory.getLog(BackupTodoListAction.class);

    private TodoListsManager todoListsManager = null;

    public void setTodoListsManager(TodoListsManager todoListsManager) {
        this.todoListsManager = todoListsManager;
    }

    /**
     * Backup a Todo List.
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        log.debug("Execute action");
        DynaActionForm todoListForm = (DynaActionForm) form;
        String listId = (String) todoListForm.get("listId");
        TodoList todoList = todoListsManager.findTodoList(listId);
        Document doc = todoListsManager.backupTodoList(todoList);
        request.getSession().setAttribute("todoListDocument", doc);
        return mapping.findForward("backup");
    }
}
