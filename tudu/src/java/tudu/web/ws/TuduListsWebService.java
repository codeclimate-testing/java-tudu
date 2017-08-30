package tudu.web.ws;

import tudu.web.ws.bean.WsTodo;
import tudu.web.ws.bean.WsTodoList;

/**
 * The Tudu List Web Service.
 * 
 * @author Julien Dubois
 */
public interface TuduListsWebService {

    /**
     * Find all the todo lists for the current user.
     */
    WsTodoList[] getAllTodoLists();
    
    /**
     * Find all todos from a todo list.
     */
    WsTodo[] getTodosByTodoList(String listId);
    
}
