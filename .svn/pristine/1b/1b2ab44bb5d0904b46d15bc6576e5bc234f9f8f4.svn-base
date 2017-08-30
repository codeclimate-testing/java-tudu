package tudu.integration;

import javax.sql.DataSource;

import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.context.SecurityContextImpl;
import org.acegisecurity.providers.ProviderManager;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.test.jpa.AbstractAspectjJpaTests;
import org.springframework.transaction.PlatformTransactionManager;

import tudu.domain.RolesEnum;
import tudu.domain.model.Role;
import tudu.domain.model.Todo;
import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.service.TodoListsManager;
import tudu.service.TodosManager;
import tudu.service.UserAlreadyExistsException;
import tudu.service.UserManager;

public class IntegrationTest extends AbstractAspectjJpaTests {

    private UserManager userManager;

    private TodoListsManager todoListsManager;

    private TodosManager todosManager;

    public IntegrationTest() {
        super();
        this.setAutowireMode(AUTOWIRE_NO);
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[] {
                "classpath:/tudu/domain/applicationContext-jpa.xml",
                "classpath:/tudu/service/applicationContext.xml",
                "classpath:/tudu/security/applicationContext-security.xml" };
    }

    @Override
    protected void injectDependencies() throws Exception {        
        this.setDataSource((DataSource) 
                this.applicationContext.getBean("dataSource"));
        
        this.setTransactionManager((PlatformTransactionManager) 
                this.applicationContext.getBean("transactionManager"));
    }

    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();
        userManager = (UserManager) this.applicationContext
                .getBean("userManager");

        todoListsManager = (TodoListsManager) this.applicationContext
                .getBean("todoListsManager");

        todosManager = (TodosManager) this.applicationContext
                .getBean("todosManager");
    }

    public void testCreateUser() {
        try {
            userManager.findUser("test_user");
            fail("User already exists in the database.");
        } catch (ObjectRetrievalFailureException orfe) {
            // User should not already exist in the database.
        }

        User user = new User();
        user.setLogin("test_user");
        user.setFirstName("First name");
        user.setLastName("Last name");
        try {
            userManager.createUser(user);
            assertTrue(user.isEnabled());
            assertNotNull(user.getCreationDate());
            assertNotNull(user.getLastAccessDate());
            assertEquals(1, user.getRoles().size());
            Role testRole = user.getRoles().iterator().next();
            assertEquals(RolesEnum.ROLE_USER.toString(), testRole.getRole());
            assertEquals(1, user.getTodoLists().size());
            TodoList testTodoList = user.getTodoLists().iterator().next();
            assertNotNull(testTodoList.getLastUpdate());
            assertEquals(1, testTodoList.getTodos().size());
        } catch (UserAlreadyExistsException e) {
            fail("User already exists in the database.");
        }

        try {
            User userFoundInDatabase = userManager.findUser("test_user");
            assertEquals("First name", userFoundInDatabase.getFirstName());
            assertEquals("Last name", userFoundInDatabase.getLastName());
        } catch (ObjectRetrievalFailureException orfe) {
            fail("User should have been found in the database.");
        }
    }

    public void testCreateTodoList() {
        createAuthenticatedUser();

        TodoList todoList = new TodoList();
        todoList.setName("test_list");

        assertEquals(1, userManager.getCurrentUser().getTodoLists().size());

        todoListsManager.createTodoList(todoList);

        assertEquals(1, todoList.getUsers().size());
        assertEquals("test_user", todoList.getUsers().iterator().next()
                .getLogin());
        assertEquals(2, userManager.getCurrentUser().getTodoLists().size());

        TodoList todoListFromDatabase = todoListsManager.findTodoList(todoList
                .getListId());
        assertEquals("test_list", todoListFromDatabase.getName());
    }

    public void testDeleteTodoList() {
        createAuthenticatedUser();

        TodoList todoList = new TodoList();
        todoList.setName("test_list");

        assertEquals(1, userManager.getCurrentUser().getTodoLists().size());
        todoListsManager.createTodoList(todoList);
        assertEquals(2, userManager.getCurrentUser().getTodoLists().size());
        todoListsManager.deleteTodoList(todoList.getListId());
        assertEquals(1, userManager.getCurrentUser().getTodoLists().size());

        try {
            todoListsManager.findTodoList(todoList.getListId());
            fail("The todo list should have been deleted");
        } catch (ObjectRetrievalFailureException orfe) {
            // The todo list should not exist.
        }
    }

    public void testCreateTodo() {
        createAuthenticatedUser();

        TodoList todoList = new TodoList();
        todoListsManager.createTodoList(todoList);

        Todo todo = new Todo();
        todo.setDescription("test_todo");

        todosManager.createTodo(todoList.getListId(), todo);
        assertNotNull(todo.getCreationDate());
        assertEquals(1, todoList.getTodos().size());
    }

    public void testDeleteTodo() {
        createAuthenticatedUser();

        TodoList todoList = new TodoList();
        todoListsManager.createTodoList(todoList);

        Todo todo = new Todo();
        todo.setDescription("test_todo");

        todosManager.createTodo(todoList.getListId(), todo);
        todosManager.deleteTodo(todo.getTodoId());
        assertEquals(0, todoList.getTodos().size());
    }

    public void testSharedList() {
        createAuthenticatedUser();
        User user2 = new User();
        user2.setLogin("test_user2");
        user2.setPassword("test_password");
        try {
            userManager.createUser(user2);
        } catch (UserAlreadyExistsException e) {
            fail("User already exists in the database.");
        }
        TodoList todoList = new TodoList();
        todoListsManager.createTodoList(todoList);
        todoListsManager.addTodoListUser(todoList.getListId(), "test_user2");
        assertEquals(2, todoList.getUsers().size());
        assertEquals(2, user2.getTodoLists().size());

        Todo todo = new Todo();
        todo.setDescription("test_todo");
        todosManager.createTodo(todoList.getListId(), todo);
        assertEquals(1, todoList.getTodos().size());

        todoListsManager.deleteTodoList(todoList.getListId());
        assertEquals(1, userManager.getCurrentUser().getTodoLists().size());
        assertEquals(1, user2.getTodoLists().size());
    }

    /**
     * Authenticate the current user using Acegi Security.
     */
    private void createAuthenticatedUser() {
        User user = new User();
        user.setLogin("test_user");
        user.setPassword("test_password");
        try {
            userManager.createUser(user);
        } catch (UserAlreadyExistsException e) {
            fail("User already exists in the database.");
        }

        SecurityContextImpl secureContext = new SecurityContextImpl();

        UserDetailsService userDetailsService = (UserDetailsService) this.applicationContext
                .getBean("userDetailsService");

        UserDetails userDetails = userDetailsService
                .loadUserByUsername("test_user");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                userDetails, "test_password");

        ProviderManager authenticationManager = (ProviderManager) this.applicationContext
                .getBean("authenticationManager");

        authenticationManager.doAuthentication(token);
        secureContext.setAuthentication(token);
        SecurityContextHolder.setContext(secureContext);
    }
}
