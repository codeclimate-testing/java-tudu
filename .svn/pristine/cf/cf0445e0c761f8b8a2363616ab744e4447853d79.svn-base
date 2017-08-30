package tudu.web.ws.xfire.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import tudu.domain.model.Todo;
import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.service.TodoListsManager;
import tudu.service.TodosManager;
import tudu.web.ws.xfire.WSException;
import tudu.web.ws.xfire.sdo.ArrayOfWSTodo;
import tudu.web.ws.xfire.sdo.ArrayOfWSTodoList;
import tudu.web.ws.xfire.sdo.WSTodo;
import tudu.web.ws.xfire.sdo.WSTodoList;
import tudu.web.ws.xfire.service.TodoListsService;

/**
 * Implementation of the Tudu Lists Service.
 * 
 * @author Frederic Dreyfus
 */
@WebService(endpointInterface = "tudu.web.ws.xfire.service.TodoListsService", serviceName = "TodoListsService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class TodoListsServiceImpl extends AbstractService implements
        TodoListsService {

    /**
     * Manager that handles todo lists
     */
    private TodoListsManager todoListsManager;

    /**
     * Manager that handles todos
     */
    private TodosManager todosManager;

    /**
     * Minimal constructor
     */
    public TodoListsServiceImpl() {

    }

    public void addTodo(String todoListId, WSTodo wsTodo) throws WSException {
        // Retrieves the todoList for the current user
        TodoList todoList = todoListsManager.findTodoList(todoListId);
        if (todoList == null) {
            throw new WSException("No todoList found for id : " + todoListId);
        }
        todosManager.createTodo(todoListId, (Todo) mapper.map(wsTodo,
                Todo.class));
    }

    public WSTodoList createTodoList(String name) throws WSException {
        TodoList emptyTodoList = new TodoList();
        emptyTodoList.setName(name);
        todoListsManager.createTodoList(emptyTodoList);
        return (WSTodoList) mapper.map(emptyTodoList, WSTodoList.class);

    }

    public ArrayOfWSTodoList getAllTodoLists() throws WSException {
        User user = userManager.getCurrentUser();
        if (user == null) {
            throw new SecurityException("No user authenticated");
        }
        List<WSTodoList> wsTodoLists = new ArrayList<WSTodoList>();
        for (TodoList todoList : user.getTodoLists()) {
            wsTodoLists
                    .add((WSTodoList) mapper.map(todoList, WSTodoList.class));
        }
        ArrayOfWSTodoList arrayOfWSTodoList = new ArrayOfWSTodoList();
        arrayOfWSTodoList.setTodoLists(wsTodoLists);
        return arrayOfWSTodoList;

    }

    public ArrayOfWSTodo getTodosByTodoList(String listId) throws WSException {
        List<WSTodo> wsTodos = new ArrayList<WSTodo>();
        TodoList todoList = todoListsManager.findTodoList(listId);
        if (todoList == null) {
            throw new WSException("No todo list found for [user="
                    + userManager.getCurrentUser().getLogin() + " ; listId ="
                    + listId + "]");
        }
        for (Todo todo : todoList.getTodos()) {
            wsTodos.add((WSTodo) mapper.map(todo, WSTodo.class));
        }
        ArrayOfWSTodo arrayOfWSTodo = new ArrayOfWSTodo();
        arrayOfWSTodo.setTodos(wsTodos);
        return arrayOfWSTodo;
    }

    public ArrayOfWSTodo getSubTodos(String todoId) throws WSException {
        List<WSTodo> wsSubTodos = new ArrayList<WSTodo>();
        Todo mainTodo = todosManager.findTodo(todoId);
        if (mainTodo == null) {
            throw new WSException("No todo found for [user="
                    + userManager.getCurrentUser().getLogin() + " ; todoId ="
                    + todoId + "]");
        }

        for (Todo subTodo : mainTodo.getSubTodos()) {
            wsSubTodos.add((WSTodo) mapper.map(subTodo, WSTodo.class));
        }
        ArrayOfWSTodo arrayOfWSTodo = new ArrayOfWSTodo();
        arrayOfWSTodo.setTodos(wsSubTodos);
        return arrayOfWSTodo;
    }

    public void addSubTodo(String todoId, WSTodo subTodo) throws WSException {
        Todo mainTodo = todosManager.findTodo(todoId);
        Set<Todo> subTodos = mainTodo.getSubTodos();
        if (subTodos == null) {
            subTodos = new HashSet<Todo>();
        }
        subTodos.add((Todo) mapper.map(subTodo, Todo.class));
        todosManager.updateTodo(mainTodo);
    }

    public void updateTodo(WSTodo todo) {
        todosManager.updateTodo((Todo) mapper.map(todo, Todo.class));
    }

    public void updateTodoList(WSTodoList todoList) {
        todoListsManager.updateTodoList((TodoList) mapper.map(todoList,
                TodoList.class));

    }

    /**
     * @return the todoListsManager
     */
    public TodoListsManager getTodoListsManager() {
        return todoListsManager;
    }

    /**
     * @param todoListsManager
     *            the todoListsManager to set
     */
    public void setTodoListsManager(TodoListsManager todoListsManager) {
        this.todoListsManager = todoListsManager;
    }

    /**
     * @return the todosManager
     */
    public TodosManager getTodosManager() {
        return todosManager;
    }

    /**
     * @param todosManager
     *            the todosManager to set
     */
    public void setTodosManager(TodosManager todosManager) {
        this.todosManager = todosManager;
    }

}
