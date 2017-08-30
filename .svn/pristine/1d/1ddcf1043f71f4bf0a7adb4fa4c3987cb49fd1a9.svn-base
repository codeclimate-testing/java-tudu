package tudu.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.dao.UserCache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;

import tudu.Constants;
import tudu.domain.RolesEnum;
import tudu.domain.dao.PropertyDAO;
import tudu.domain.dao.RoleDAO;
import tudu.domain.dao.TodoDAO;
import tudu.domain.dao.TodoListDAO;
import tudu.domain.dao.UserDAO;
import tudu.domain.model.Property;
import tudu.domain.model.Role;
import tudu.domain.model.Todo;
import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.service.UserAlreadyExistsException;
import tudu.service.UserManager;

/**
 * Implementation of the tudu.service.UserManager interface.
 * 
 * @author Julien Dubois
 */
@Transactional
public class UserManagerImpl implements UserManager {

    private final Log log = LogFactory.getLog(UserManagerImpl.class);

    private UserDAO userDAO = null;

    private RoleDAO roleDAO = null;

    private TodoListDAO todoListDAO = null;

    private TodoDAO todoDAO = null;

    private PropertyDAO propertyDAO = null;

    private UserCache userCache = null;

    public void setUserDAO(UserDAO userDao) {
        this.userDAO = userDao;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public void setTodoDAO(TodoDAO todoDAO) {
        this.todoDAO = todoDAO;
    }

    public void setTodoListDAO(TodoListDAO todoListDAO) {
        this.todoListDAO = todoListDAO;
    }

    public void setPropertyDAO(PropertyDAO propertyDAO) {
        this.propertyDAO = propertyDAO;
    }

    public void setUserCache(UserCache userCache) {
        this.userCache = userCache;
    }

    /**
     * Get the number of users.
     * 
     * @see tudu.service.UserManager#getNumberOfUsers()
     */
    @Transactional(readOnly = true)
    public long getNumberOfUsers() {
        return userDAO.getNumberOfUsers();
    }

    /**
     * Find a user by login.
     * 
     * @see tudu.service.UserManager#findUser(String)
     */
    @Transactional(readOnly = true)
    public User findUser(String login) {
        User user = userDAO.getUser(login);
        if (user == null) {
            if (log.isDebugEnabled()) {
                log.debug("Could not find User ID=" + login);
            }
            throw new ObjectRetrievalFailureException(User.class, login);
        }
        if (log.isDebugEnabled()) {
            log.debug("User ID=" + login + " found, user is called "
                    + user.getFirstName() + " " + user.getLastName());
        }
        return user;
    }

    /**
     * Find all users with a login starting with the "loginStart" string.
     * 
     * @see tudu.service.UserManager#findUsersByLogin(java.lang.String)
     */
    @Transactional(readOnly = true)
    public List<User> findUsersByLogin(String loginStart) {
        return userDAO.findUsersByLogin(loginStart);
    }

    /**
     * Find the current Tudu List user.
     * 
     * @see tudu.service.UserManager#getCurrentUser()
     */
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        org.acegisecurity.userdetails.User acegiUser = (org.acegisecurity.userdetails.User) securityContext
                .getAuthentication().getPrincipal();

        return this.findUser(acegiUser.getUsername());
    }

    /**
     * Update a user's information.
     * 
     * @see tudu.service.UserManager#updateUser(tudu.domain.model.User)
     */
    public void updateUser(User user) {
        if (log.isDebugEnabled()) {
            log.debug("Updating user '" + user.getLogin() + "'.");
        }
        userDAO.updateUser(user);
    }

    /**
     * Enable a user account.
     * 
     * @param login
     *            The user login
     * @see tudu.service.UserManager#enableUser(tudu.domain.model.User)
     */
    public void enableUser(String login) {
        User user = this.findUser(login);
        user.setEnabled(true);
        this.userCache.removeUserFromCache(login);
    }

    /**
     * Disable a user account.
     * 
     * @param login
     *            The user login
     * @see tudu.service.UserManager#disableUser(tudu.domain.model.User)
     */
    public void disableUser(String login) {
        User user = this.findUser(login);
        user.setEnabled(false);
        this.userCache.removeUserFromCache(login);
    }

    /**
     * Create a new user.
     * 
     * @see tudu.service.UserManager#createUser(tudu.domain.model.User)
     */
    public void createUser(User user) throws UserAlreadyExistsException {
        if (log.isDebugEnabled()) {
            log.debug("Creating user '" + user.getLogin() + "'.");
        }

        User testUser = userDAO.getUser(user.getLogin());
        if (testUser != null) {
            if (log.isDebugEnabled()) {
                log.debug("User login '" + user.getLogin()
                        + "' already exists.");
            }
            throw new UserAlreadyExistsException("User already exists.");
        }
        user.setEnabled(true);
        Date now = Calendar.getInstance().getTime();
        user.setCreationDate(now);
        user.setLastAccessDate(now);
        user.setDateFormat(Constants.DATEFORMAT_US);
        Role role = roleDAO.getRole(RolesEnum.ROLE_USER.toString());
        user.getRoles().add(role);
        userDAO.saveUser(user);

        TodoList todoList = new TodoList();
        todoList.setName("Welcome!");
        todoList.setLastUpdate(Calendar.getInstance().getTime());
        todoListDAO.saveTodoList(todoList);
        user.getTodoLists().add(todoList);
        todoList.getUsers().add(user);

        Todo welcomeTodo = new Todo();
        welcomeTodo.setDescription("Welcome to Tudu Lists!");
        welcomeTodo.setPriority(100);
        welcomeTodo.setCreationDate(now);
        welcomeTodo.setCompletionDate(now);
        welcomeTodo.setTodoList(todoList);
        todoList.getTodos().add(welcomeTodo);
        todoDAO.saveTodo(welcomeTodo);
        todoListDAO.updateTodoList(todoList);
    }

    /**
     * @see tudu.service.UserManager#sendPassword(tudu.domain.model.User)
     */
    @Transactional(readOnly = true)
    public void sendPassword(User user) {
        if (log.isDebugEnabled()) {
            log.debug("Send password of user '" + user.getLogin() + "'.");
        }
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        Property smtpHost = propertyDAO.getProperty("smtp.host");
        sender.setHost(smtpHost.getValue());
        Property smtpPort = propertyDAO.getProperty("smtp.port");
        int port = 25;
        try {
            port = Integer.parseInt(smtpPort.getValue());
        } catch (NumberFormatException e) {
            log.error("The supplied SMTP port is not a number.");
        }
        sender.setPort(port);
        Property smtpUser = propertyDAO.getProperty("smtp.user");
        sender.setUsername(smtpUser.getValue());
        Property smtpPassword = propertyDAO.getProperty("smtp.password");
        sender.setPassword(smtpPassword.getValue());

        SimpleMailMessage message = new SimpleMailMessage();
        Property smtpFrom = propertyDAO.getProperty("smtp.from");
        message.setTo(user.getEmail());
        message.setFrom(smtpFrom.getValue());
        message.setSubject("Your Tudu Lists password");
        message
                .setText("Dear "
                        + user.getFirstName()
                        + " "
                        + user.getLastName()
                        + ",\n\n"
                        + "Your Tudu Lists password is \""
                        + user.getPassword()
                        + "\".\n"
                        + "Now that this password has been sent by e-mail, we recommend that "
                        + "you change it as soon as possible.\n\n"
                        + "Regards,\n\n" + "Tudu Lists.");

        sender.send(message);
    }
}
