package tudu.web.gwt.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.web.context.support.WebApplicationContextUtils;

import tudu.domain.model.Todo;
import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.service.TodoListsManager;
import tudu.service.TodosManager;
import tudu.service.UserManager;
import tudu.web.gwt.client.SerializableTodo;
import tudu.web.gwt.client.SerializableTodoList;
import tudu.web.gwt.client.TuduGwtRemoteService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class TuduGwtRemoteServiceImpl extends RemoteServiceServlet implements
		TuduGwtRemoteService {

	private static final long serialVersionUID = -8104310076326914361L;
	
	private TodosManager todosManager;
	
	private TodoListsManager todoListsManager;
	
	private UserManager userManager;
	
	/**
	 * Initialize the servlet.
	 */
	public void init() throws ServletException {
	    super.init();
	    
	    this.todosManager = 
	        (TodosManager) WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean("todosManager");

	    this.todoListsManager = 
	        (TodoListsManager) WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean("todoListsManager");

	    this.userManager = 
	        (UserManager) WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean("userManager");

	}
	
    /**
     * Create a todo on the server.
     */
	public String createTodo(String listId, SerializableTodo sTodo) {
	    Todo todo = new Todo();
        todo.setDescription(sTodo.getDescription());
        todo.setPriority(sTodo.getPriority());
        todosManager.createTodo(listId, todo);
        sTodo.setTodoId(todo.getTodoId());
        return todo.getTodoId();
	}
    
    /**
     * Update a todo on the server.
     */
	public void updateTodo(SerializableTodo sTodo) {
	    Todo todo = todosManager.findTodo(sTodo.getTodoId());
        todo.setDescription(sTodo.getDescription());
        todo.setPriority(sTodo.getPriority());
        todosManager.updateTodo(todo);
        if (todo.isCompleted() && !sTodo.isCompleted()) {
            todosManager.reopenTodo(sTodo.getTodoId());
        } else if (!todo.isCompleted() && sTodo.isCompleted()) {
            todosManager.completeTodo(sTodo.getTodoId());
        }
	}
    
    /**
     * Delete a todo on the server.
     */
	public void deleteTodo(SerializableTodo sTodo) {
	    todosManager.deleteTodo(sTodo.getTodoId());
	}

    /**
     * Get a todo list from the server.
     */
    public SerializableTodoList getTodoList(String listId) {
        TodoList list = todoListsManager.findTodoList(listId);
        return makeSerializable(list);   
    }
    
    /**
     * Get all the current user's todo lists from the server.
     * 
     * @gwt.typeArgs <tudu.web.gwt.client.SerializableTodoList>
     */
    public List getAllTodoLists() {
        User user = userManager.getCurrentUser();
        List<SerializableTodoList> sTodoLists = new ArrayList<SerializableTodoList>();
        for (TodoList todoList : user.getTodoLists()) {
            SerializableTodoList sTodoList = makeSerializable(todoList);
            sTodoLists.add(sTodoList);
        }
        return sTodoLists;
    }
    
    /**
     * Transforms a domain TodoList into a GWT-serializable Todo List.
     * 
     * @param list a domain TodoList
     * @return a GWT-serializable Todo List
     */
    private SerializableTodoList makeSerializable(TodoList list) {
        SerializableTodoList sList = new SerializableTodoList();
        sList.setListId(list.getListId());
        sList.setName(list.getName());
        sList.setRssAllowed(list.isRssAllowed());
        sList.setTodos(new ArrayList());
        for (Todo todo : list.getTodos()) {
            SerializableTodo sTodo = new SerializableTodo();
            sTodo.setTodoId(todo.getTodoId());
            sTodo.setPriority(todo.getPriority());
            sTodo.setDescription(todo.getDescription());
            sTodo.setCompleted(todo.isCompleted());
            sList.getTodos().add(sTodo);
        }
        return sList;
    }
}
