package otn.todo.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusListenerAdapter;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import java.util.Iterator;
import java.util.Map;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TodoApp implements EntryPoint, HistoryListener {

    private List todoList = new ArrayList();

    private FlexTable table = new FlexTable();

    private int historyToken = 0;

    private HashMap historyMap = new HashMap();

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        Label newTodoLabel = new Label("Create a new to-do : ");

        final TextBox newTodoDescription = new TextBox();
        newTodoDescription.addKeyboardListener(new KeyboardListenerAdapter() {
                    public void onKeyPress(Widget sender, char keyCode,
                                           int modifiers) {
                        if (keyCode == KeyboardListener.KEY_ENTER) {
                            Todo todo = new Todo(newTodoDescription.getText());
                            todoList.add(todo);
                            saveTodoListOnServer();
                            newTodoDescription.setText("");
                        }
                    }
                });

        RootPanel.get("create").add(newTodoLabel);
        RootPanel.get("create").add(newTodoDescription);
        
        RootPanel.get("todo_list").add(table);

        newTodoDescription.setFocus(true);

        updateTodoListFromServer();
        History.addHistoryListener(this);
        
    }

    /**
     * Print the to-do list on the page.
     */
    private void printTodoList() {
        table.clear();
        for (int todoId = 0; todoId < todoList.size(); todoId++) {
            table.setWidget(todoId, 0, createCheckBoxWidget(todoId));
            table.setWidget(todoId, 1, createUpWidget(todoId));
            table.setWidget(todoId, 2, createDownWidget(todoId));
            table.setWidget(todoId, 3, createDeleteWidget(todoId));
            table.setWidget(todoId, 4, writeDescription(todoId));
        }
    }

    /**
     * Create a checkbox widget for telling which to-dos are done.
     *
     * @param todoId The todo ID
     * @return The widget
     */
    private Widget createCheckBoxWidget(final int todoId) {
        final Todo todo = (Todo)todoList.get(todoId);
        CheckBox checkBox = new CheckBox();
        if (todo.isDone()) {
            checkBox.setChecked(true);
        }
        checkBox.addClickListener(new ClickListener() {
                    public void onClick(Widget sender) {
                        boolean checked = ((CheckBox)sender).isChecked();
                        todo.setDone(checked);
                        saveTodoListOnServer();
                    }
                });
        return checkBox;
    }

    /**
     * Create a button for moving a to-do upwards.
     *
     * @param todoId
     *            The id of the to-do to be moved
     * @return The up widget
     */
    private Widget createUpWidget(final int todoId) {
        Image upImage = new Image("up.gif");
        upImage.addClickListener(new ClickListener() {
                    public void onClick(Widget sender) {
                        if (todoId > 0) {
                            Todo secondTodo = (Todo)todoList.get(todoId);
                            Todo firstTodo = (Todo)todoList.get(todoId - 1);
                            todoList.set(todoId - 1, secondTodo);
                            todoList.set(todoId, firstTodo);
                            saveTodoListOnServer();
                        }
                    }
                });
        upImage.setStyleName("selection-image");
        return upImage;
    }

    /**
     * Create a button for moving a to-do downwards.
     *
     * @param todoId
     *            The id of the to-do to be moved
     * @return The down widget
     */
    private Widget createDownWidget(final int todoId) {
        Image downImage = new Image("down.gif");
        downImage.addClickListener(new ClickListener() {
                    public void onClick(Widget sender) {
                        if (todoId < todoList.size() - 1) {
                            Todo firstTodo = (Todo)todoList.get(todoId);
                            Todo secondTodo = (Todo)todoList.get(todoId + 1);
                            todoList.set(todoId, secondTodo);
                            todoList.set(todoId + 1, firstTodo);
                            saveTodoListOnServer();
                        }
                    }
                });
        downImage.setStyleName("selection-image");
        return downImage;
    }

    /**
     * Create a delete button for a to-do.
     *
     * @param todoId
     *            The id of the to-do to be deleted
     * @return The delete widget
     */
    private Widget createDeleteWidget(final int todoId) {
        Image deleteImage = new Image("delete.gif");
        deleteImage.addClickListener(new ClickListener() {
                    public void onClick(Widget sender) {
                        Todo todo = (Todo)todoList.get(todoId);
                        if (Window.confirm("Are you sure you want to delete \"" +
                                           todo.getDescription() + "\"")) {

                            todoList.remove(todoId);
                            saveTodoListOnServer();
                        }
                    }
                });
        deleteImage.setStyleName("selection-image");
        return deleteImage;
    }

    /**
     * Write the (editable) description of the to-do.
     *
     * @param todoId
     *            The id of the to-do
     * @return The (editable description)
     */
    private Widget writeDescription(final int todoId) {
        final Todo todo = (Todo)todoList.get(todoId);
        Label todoLabel = new Label(todo.getDescription());
        todoLabel.addClickListener(new ClickListener() {
                    public void onClick(Widget sender) {
                        final TextBox editableTodoDescription = new TextBox();
                        editableTodoDescription.setText(todo.getDescription());
                        editableTodoDescription.addKeyboardListener(new KeyboardListenerAdapter() {
                                    public void onKeyPress(Widget sender,
                                                           char keyCode,
                                                           int modifiers) {
                                        if (keyCode ==
                                            KeyboardListener.KEY_ENTER) {
                                            todo.setDescription(editableTodoDescription.getText());
                                            saveTodoListOnServer();
                                        }
                                    }
                                });
                        editableTodoDescription.addFocusListener(new FocusListenerAdapter() {
                                    public void onLostFocus(Widget sender) {
                                        todo.setDescription(editableTodoDescription.getText());
                                        saveTodoListOnServer();
                                    }
                                });
                        table.setWidget(todoId, 4, editableTodoDescription);
                    }
                });
        return todoLabel;
    }


    /**
     * Update the todo list with data from the server.
     */
    private void updateTodoListFromServer() {
        TodoListBackupServiceAsync todoListBackupService =
            (TodoListBackupServiceAsync)GWT.create(TodoListBackupService.class);

        ServiceDefTarget endpoint = (ServiceDefTarget)todoListBackupService;
        endpoint.setServiceEntryPoint("/todoListBackupService");

        AsyncCallback callback = new AsyncCallback() {
                public void onSuccess(Object result) {
                    todoList = (List)result;
                    saveTodoListInHistory();
                }

                public void onFailure(Throwable caught) {
                    Todo todo =
                        new Todo("ERROR!! Server could not be reached.");
                    todoList.add(todo);
                    saveTodoListInHistory();
                }
            };

        todoListBackupService.getTodoList(callback);
    }

    /**
     * Save the todo list on the server.
     */
    private void saveTodoListOnServer() {
        saveTodoListInHistory();
        
        TodoListBackupServiceAsync todoListBackupService =
            (TodoListBackupServiceAsync)GWT.create(TodoListBackupService.class);

        ServiceDefTarget endpoint = (ServiceDefTarget)todoListBackupService;
        endpoint.setServiceEntryPoint("/todoListBackupService");

        AsyncCallback callback = new AsyncCallback() {
                public void onSuccess(Object result) {
                    printTodoList();
                }

                public void onFailure(Throwable caught) {
                    Window.alert("Warning : the to-do list could not be saved on the server. Maybe the server is down.");
                }
            };

        todoListBackupService.saveTodoList(todoList, callback);
    }

    /**
     * This method is called whenever the application's history changes.
     */
    public void onHistoryChanged(String _historyToken) {
        if (Integer.parseInt(_historyToken) + 1 != historyToken) {
            if (historyMap.get(_historyToken) != null) {
                historyToken = Integer.parseInt(_historyToken);
                todoList = (List) historyMap.get(_historyToken);
            }
        }
        printTodoList();
    }
    
    /**
     * Save the current list in the client's History.
     */
    private void saveTodoListInHistory() {
        List todoListClone = new ArrayList();
        Iterator it = todoList.iterator();
        while (it.hasNext()) {
            Todo todo = (Todo) it.next();
            todoListClone.add(todo.clone());
        }
        historyMap.put(String.valueOf(historyToken), todoListClone);
        History.newItem(String.valueOf(historyToken));
        historyToken++;
    }
}
