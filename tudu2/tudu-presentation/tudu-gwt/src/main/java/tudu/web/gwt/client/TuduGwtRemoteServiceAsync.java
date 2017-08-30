package tudu.web.gwt.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TuduGwtRemoteServiceAsync {
    
    /**
     * Create a todo on the server.
     */
    void  createTodo(String listId, SerializableTodo sTodo, AsyncCallback callback);
    
    /**
     * Update a todo on the server.
     */
    void updateTodo(SerializableTodo sTodo, AsyncCallback callback);
    
    /**
     * Delete a todo on the server.
     */
    void deleteTodo(SerializableTodo sTodo, AsyncCallback callback);

    /**
     * Get a todo list from the server.
     */
    void  getTodoList(String listId, AsyncCallback callback);
    
    /**
     * Get all the current user's todo lists from the server.
     */
    void  getAllTodoLists(AsyncCallback callback);
}
