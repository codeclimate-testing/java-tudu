package tudu.security;

import java.util.Calendar;
import java.util.Set;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import tudu.domain.model.Role;
import tudu.domain.model.User;
import tudu.service.UserManager;

/**
 * An implementation of Acegi Security's AuthenticationDao.
 *
 * @author Julien Dubois
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final Log log = LogFactory.getLog(UserDetailsServiceImpl.class);

    private UserManager userManager = null;
    
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Load a user for Acegi Security.
     */
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException, DataAccessException {
        login = login.toLowerCase();
        if (log.isDebugEnabled()) {
            log.debug("Security verification for user '" + login + "'");
        }
        User user = userManager.findUser(login);
        
        user.setLastAccessDate(Calendar.getInstance().getTime());
        userManager.updateUser(user);
        
        Set<Role> roles = user.getRoles();
        GrantedAuthority[] arrayAuths = new GrantedAuthority[roles.size()];
        int index = 0;
        for (Role role : roles) {
            arrayAuths[index++] = new GrantedAuthorityImpl(role.getRole());
        }

        return new org.acegisecurity.userdetails.User(
                login, user.getPassword(), user.isEnabled(),
                true, true, true, arrayAuths);
    }
}
