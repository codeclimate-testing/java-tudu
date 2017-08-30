package tudu.web.ws.xfire.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import tudu.web.ws.xfire.WSException;
import tudu.web.ws.xfire.sdo.ArrayOfWSTodo;
import tudu.web.ws.xfire.sdo.ArrayOfWSTodoList;
import tudu.web.ws.xfire.sdo.WSTodo;
import tudu.web.ws.xfire.sdo.WSTodoList;
import tudu.web.ws.xfire.service.impl.AbstractService;

/**
 * <p>
 * Exposed Todo List Services.
 * </p>
 * <p>
 * For each method, there is a <code>UsernameToken</code> parameter to
 * authenticate the user, which appears in headers of the SOAP envelope. <br />
 * Thus this object is not used in the implementations as the authentication is
 * delegated to security handlers.
 * </p>
 * 
 * @author Frederic Dreyfus
 * 
 * @see BasicHeaderAuthenticationHandler
 */
@WebService(name = "TodoListsPort", targetNamespace = AbstractService.NS)
public interface TodoListsService {

    /**
     * Creates an empty TodoList for current user.
     * 
     * @param name
     *            the todo list name
     * @return the newly created todoList
     * @throws WSException
     *             if an error occurs during creation
     */
    @WebMethod(operationName = "CreateEmptyTodoList", action = AbstractService.NS
            + "/CreateEmptyTodoList")
    @WebResult(name = "NewTodoList")
    public WSTodoList createTodoList(@WebParam(name = "Name")
    String name) throws WSException;

    /**
     * Adds a todo to a todo list.
     * 
     * @param todoListId
     *            the todo list id.
     * @param wsTodo
     *            the todo to be added.
     * @throws WSException
     *             if an error occurs during adding
     */
    @WebMethod(operationName = "AddTodo", action = AbstractService.NS
            + "/AddTodo")
    public void addTodo(@WebParam(name = "TodoListId")
    String todoListId, @WebParam(name = "Todo")
    WSTodo wsTodo) throws WSException;

    /**
     * Returns all todo lists for the current user.
     * 
     * @return all todo lists for the current user.
     * @throws WSException
     *             if an error occurs during searching
     */
    @WebMethod(operationName = "GetAllTodoLists", action = AbstractService.NS
            + "/GetAllTodoLists")
    @WebResult(name = "TodoLists")
    public ArrayOfWSTodoList getAllTodoLists() throws WSException;

    /**
     * Find all todos from a todo list.
     * 
     * @param listId
     *            the list id
     * @return todos from this todo list
     * @throws WSException
     *             if an error occurs during searching
     */
    @WebMethod(operationName = "GetTodosByTodoList", action = AbstractService.NS
            + "/GetTodosByTodoList")
    @WebResult(name = "Todos")
    public ArrayOfWSTodo getTodosByTodoList(@WebParam(name = "TodoListId")
    String listId) throws WSException;

    /**
     * Get sub-todos from the main <code>todoId</code>.
     * 
     * @param todoId
     *            the main todo id
     * @return the sub-todos
     * @throws WSException
     *             if an error occurs during searching
     */
    @WebMethod(operationName = "GetSubTodos", action = AbstractService.NS
            + "/GetSubTodos")
    @WebResult(name = "Todos")
    public ArrayOfWSTodo getSubTodos(@WebParam(name = "TodoId")
    String todoId) throws WSException;

    /**
     * Adds a sub-todo to the main <code>todoId</code>.
     * 
     * @param todoId
     *            the todo id where the sub-todo will be added
     * @param subTodo
     *            the sub-todo to add
     * @throws WSException
     *             if an error occurs during adding
     */
    @WebMethod(operationName = "AddSubTodos", action = AbstractService.NS
            + "/AddSubTodos")
    public void addSubTodo(@WebParam(name = "TodoId")
    String todoId, @WebParam(name = "SubTodo")
    WSTodo subTodo) throws WSException;

    /**
     * Updates a todo.
     * 
     * @param todo
     *            the todo to update
     * @throws WSException
     *             if an error occurs during update
     */
    @WebMethod(operationName = "UpdateTodo", action = AbstractService.NS
            + "/UpdateTodo")
    public void updateTodo(@WebParam(name = "Todo")
    WSTodo todo) throws WSException;

}
