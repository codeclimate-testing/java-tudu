package tudu.service;

import tudu.domain.model.User;

/**
 * Manage the security: user authentification and autorizations.
 * 
 * @author Julien Dubois
 */
public interface UserManager {

    /**
     * Find a user by login.
     * 
     * @param login
     *            The user login
     * @return The user value object
     */
    User findUser(String login);
    
    /**
     * Find the current user.
     * <p>
     * This method relies on Acegy Security.
     * </p>
     * 
     * @return The current user.
     */
    User getCurrentUser();

    /**
     * Update a user's information.
     * 
     * @param user
     *            The user to update
     */
    void updateUser(User user);

    /**
     * Create a new user.
     * 
     * @param user
     *            The user to create
     */
    void createUser(User user) throws UserAlreadyExistsException;
    
    /**
     * Send a user's password by email.
     * 
     * @param user The user
     */
    void sendPassword(User user);
}
