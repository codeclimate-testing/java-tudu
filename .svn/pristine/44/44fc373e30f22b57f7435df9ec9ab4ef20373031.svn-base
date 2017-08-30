package tudu.web.gwt.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

public interface TuduGwtRemoteService extends RemoteService {

    /**
     * Create a todo on the server.
     */
    String createTodo(String listId, SerializableTodo sTodo);
    
    /**
     * Update a todo on the server.
     */
    void updateTodo(SerializableTodo sTodo);
    
    /**
     * Delete a todo on the server.
     */
    void deleteTodo(SerializableTodo sTodo);

    /**
     * Get a todo list from the server.
     */
    SerializableTodoList getTodoList(String listId);
    
    /**
     * Get all the current user's todo lists from the server.
     * 
     * @gwt.typeArgs <tudu.web.gwt.client.SerializableTodoList>
     */
    List getAllTodoLists();
}
