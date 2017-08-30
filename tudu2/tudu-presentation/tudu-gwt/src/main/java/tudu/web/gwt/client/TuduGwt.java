package tudu.web.gwt.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusListenerAdapter;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class TuduGwt implements EntryPoint {

    private List allTodoLists;
    
	private SerializableTodoList currentTodoList;
	
	private DockPanel mainPanel = new DockPanel();

	private FlexTable table = new FlexTable();

	private TuduGwtRemoteServiceAsync tuduGwtRemoteService = null;
	
	private Label currentTodoListLabel = new Label();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		tuduGwtRemoteService = (TuduGwtRemoteServiceAsync) GWT
				.create(TuduGwtRemoteService.class);

		ServiceDefTarget endpoint = (ServiceDefTarget) tuduGwtRemoteService;
		endpoint.setServiceEntryPoint(GWT.getHostPageBaseURL() + "secure/tudu_lists_remote_service");


		RootPanel.get("main").add(mainPanel);

		//Panel showing the available lists
		Panel listsPanel = new VerticalPanel();
		mainPanel.add(listsPanel, DockPanel.WEST);
		
		//Panel showing the current todos
		Panel todosPanel = new VerticalPanel();
		mainPanel.add(todosPanel, DockPanel.CENTER);
		currentTodoListLabel.setStyleName("todo-list-label");
		todosPanel.add(currentTodoListLabel);
		
		Panel newTodoPanel = new HorizontalPanel();
		Label newTodoLabel = new Label("Create a new to-do : ");
		
		final TextBox newTodoDescription = new TextBox();
		newTodoDescription.addKeyboardListener(new KeyboardListenerAdapter() {
		    public void onKeyPress(Widget sender, char keyCode, int modifiers) {
		        if (keyCode == KeyboardListener.KEY_ENTER) {
		            SerializableTodo todo = new SerializableTodo();
		            todo.setDescription(newTodoDescription.getText());
		            
		            createTodoOnServer(todo);
		            currentTodoList.getTodos().add(todo);
		            newTodoDescription.setText("");
		        }
		    }
		});
		
		newTodoPanel.add(newTodoLabel);
		newTodoPanel.add(newTodoDescription);
		todosPanel.add(newTodoPanel);
		todosPanel.add(table);

		newTodoDescription.setFocus(true);

		getAllTodoLists();
	}

	/**
	 * Print the to-do list on the page.
	 */
	private void printTodoList() {
		table.clear();
		for (int todoIndex = 0; todoIndex < currentTodoList.getTodos().size(); todoIndex++) {
		    table.setWidget(todoIndex, 0, writeDescription(todoIndex));
			table.setWidget(todoIndex, 1, createCheckBoxWidget(todoIndex));
			table.setWidget(todoIndex, 2, createDeleteWidget(todoIndex));
		}
	}

	/**
	 * Create a checkbox widget for telling which to-dos are done.
	 * 
	 * @param todoIndex
	 *            The todo ID
	 * @return The widget
	 */
	private Widget createCheckBoxWidget(final int todoIndex) {
		final SerializableTodo todo = (SerializableTodo) currentTodoList.getTodos().get(todoIndex);
		CheckBox checkBox = new CheckBox();
		if (todo.isCompleted()) {
			checkBox.setChecked(true);
		}
		checkBox.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				boolean checked = ((CheckBox) sender).isChecked();
				todo.setCompleted(checked);
				updateTodoOnServer(todo);
			}
		});
		return checkBox;
	}

	/**
	 * Create a delete button for a to-do.
	 * 
	 * @param todoIndex
	 *            The id of the to-do to be deleted
	 * @return The delete widget
	 */
	private Widget createDeleteWidget(final int todoIndex) {
		Image deleteImage = new Image("bin_closed.png");
		deleteImage.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				SerializableTodo todo = (SerializableTodo) currentTodoList.getTodos().get(todoIndex);
				if (Window.confirm("Are you sure you want to delete \""
						+ todo.getDescription() + "\"")) {

				    currentTodoList.getTodos().remove(todoIndex);
				    deleteTodoOnServer(todo);
				}
			}
		});
		deleteImage.setStyleName("selection-image");
		return deleteImage;
	}

	/**
	 * Write the (editable) description of the to-do.
	 * 
	 * @param todoIndex
	 *            The id of the to-do
	 * @return The (editable description)
	 */
	private Widget writeDescription(final int todoIndex) {
		final SerializableTodo todo = (SerializableTodo) currentTodoList.getTodos().get(todoIndex);
		Label todoLabel = new Label(todo.getDescription());
		todoLabel.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				final TextBox editableTodoDescription = new TextBox();
				editableTodoDescription.setText(todo.getDescription());
				editableTodoDescription
						.addKeyboardListener(new KeyboardListenerAdapter() {
							public void onKeyPress(Widget sender, char keyCode,
									int modifiers) {
								if (keyCode == KeyboardListener.KEY_ENTER) {
									todo.setDescription(editableTodoDescription
											.getText());
									
									updateTodoOnServer(todo);
								}
							}
						});
				editableTodoDescription
						.addFocusListener(new FocusListenerAdapter() {
							public void onLostFocus(Widget sender) {
								todo.setDescription(editableTodoDescription
										.getText());
								
								updateTodoOnServer(todo);
							}
						});
				table.setWidget(todoIndex, 0, editableTodoDescription);
			}
		});
		return todoLabel;
	}
	
	/**
     * Get all the user's todo lists from the server.
     */
    private void getAllTodoLists() {

        AsyncCallback callback = new AsyncCallback() {
            public void onSuccess(Object result) {
                allTodoLists = (List) result;
                currentTodoList = (SerializableTodoList) allTodoLists.get(0);
                getTodoList(currentTodoList.getListId());
            }

            public void onFailure(Throwable caught) {
                Window.alert("ERROR : The server could not be reached : "
                                + caught.getMessage());

            }
        };

        tuduGwtRemoteService.getAllTodoLists(callback);
    }

	/**
	 * Get a todo list from the server.
	 */
	private void getTodoList(String listId) {

		AsyncCallback callback = new AsyncCallback() {
			public void onSuccess(Object result) {
				currentTodoList = (SerializableTodoList) result;
				currentTodoListLabel.setText(currentTodoList.getName());
				printTodoList();
			}

			public void onFailure(Throwable caught) {
				Window.alert("ERROR : The server could not be reached : "
								+ caught.getMessage());

			}
		};

		tuduGwtRemoteService.getTodoList(listId, callback);
	}

	/**
	 * Create a todo on the server.
	 */
	private void createTodoOnServer(final SerializableTodo sTodo) {

		AsyncCallback callback = new AsyncCallback() {
			public void onSuccess(Object result) {
			    sTodo.setTodoId((String) result);
				printTodoList();
			}

			public void onFailure(Throwable caught) {
				Window.alert("ERROR : the todo could not be created "
						+ "on the server. Maybe the server is down.");
			}
		};

		tuduGwtRemoteService.createTodo(currentTodoList.getListId(), sTodo, callback);
	}
	
	/**
     * Update a todo on the server.
     */
    private void updateTodoOnServer(SerializableTodo sTodo) {

        AsyncCallback callback = new AsyncCallback() {
            public void onSuccess(Object result) {
                printTodoList();
            }

            public void onFailure(Throwable caught) {
                Window.alert("ERROR : the todo could not be updated "
                        + "on the server. Maybe the server is down.");
            }
        };

        tuduGwtRemoteService.updateTodo(sTodo, callback);
    }
    
    /**
     * Delete a todo on the server.
     */
    private void deleteTodoOnServer(SerializableTodo sTodo) {

        AsyncCallback callback = new AsyncCallback() {
            public void onSuccess(Object result) {
                printTodoList();
            }

            public void onFailure(Throwable caught) {
                Window.alert("ERROR : the todo could not be deleted "
                        + "on the server. Maybe the server is down.");
            }
        };

        tuduGwtRemoteService.deleteTodo(sTodo, callback);
    }
}
