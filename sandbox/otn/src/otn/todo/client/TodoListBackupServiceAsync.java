package otn.todo.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TodoListBackupServiceAsync {

    /**
     * Save the to-do list on the server.
     */
    void saveTodoList(List todoList, AsyncCallback callback);

    /**
     * Get the to-do list on the server.
     */
    void getTodoList(AsyncCallback callback);
}
