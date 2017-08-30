package tudu.web.dwr;

import java.util.Date;

import tudu.web.dwr.bean.RemoteTodo;
import tudu.web.dwr.bean.RemoteTodoList;

/**
 * Todos actions presented with DWR.
 * 
 * @author Julien Dubois
 */
public interface TodosDwr {
    
    /**
     * Get an array containing the user's Todo Lists
     */
    RemoteTodoList[] getCurrentTodoLists();
    
    /**
     * Get a Todo by ID.
     * 
     * @param todoId The Todo ID
     * @return The Todo
     */
    RemoteTodo getTodoById(String todoId);
    
    /**
     * Render the Todo List.
     * <p>
     * If no data has changed after the last rendering date (the tableDate variable),
     * this method will return an empty String.
     * </p>
     * 
     * @param listId The List ID
     * @param tableDate The date when the actual todos where rendered
     * @return The HTML list, or an empty String
     */
    String renderTodos(String listId, Date tableDate);
    
    /**
     * Force the rendering of the Todo List.
     * 
     * @param listId The List ID
     * @return The HTML list
     */
    String forceRenderTodos(String listId);
    
    /**
     * Sort and render the Todo List.
     * 
     * @param listId The List ID
     * @param sorter The element used to sort the list
     * @return The HTML list
     */
    String sortAndRenderTodos(String listId, String sorter);
    
    /**
     * Add a new Todo.
     * 
     * @param listId The list ID
     * @param description The description
     * @param priority The priority
     * @param dueDate The due date
     * @return The HTML list
     */
    String addTodo(String listId, String description, String priority, String dueDate);
    
    /**
     * Re open a Todo.
     * 
     * @param todoId The todo ID
     * @return The HTML list
     */
    String reopenTodo(String todoId);
    
    /**
     * Complete a Todo.
     * 
     * @param todoId The todo ID
     * @return The HTML list
     */
    String completeTodo(String todoId);
    
    /**
     * Edit a Todo.
     * 
     * @param todoId The Todo ID
     * @param description The description
     * @param priority The priority
     * @param dueDate The due date
     * @return The HTML list
     */
    String editTodo(String todoId, String description, String priority, String dueDate);
    
    /**
     * Delete a Todo.
     * 
     * @param todoId The todo ID
     * @return The HTML list
     */
    String deleteTodo(String todoId);
}
