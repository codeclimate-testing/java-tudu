package tudu.web.ws.xfire.security.callback;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationManager;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.context.SecurityContextImpl;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ws.security.WSPasswordCallback;

/**
 * Password <code>CallbackHandler</code> that performs authentication. <br />
 * In WS-Security specs, when using plain passwords, it is the callback handler
 * which has to perform the authentication.
 * 
 * @author Frederic Dreyfus
 */
public class PlainPasswordCallbackHandler implements CallbackHandler {

    protected Log logger = LogFactory
            .getLog(PlainPasswordCallbackHandler.class);

    private AuthenticationManager authenticationManager;

    /**
     * Performs the authentication of the caller.<br />
     * It registers to the Acegi {@link SecurityContext} if the authentication
     * is successful.
     */
    public void handle(Callback[] callbacks) throws IOException,
            UnsupportedCallbackException {
        // Retrieves username/password from request
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        String username = pc.getIdentifer();
        String password = pc.getPassword();

        if (username != null) {
            username = StringUtils.trim(username);
        }
        if (password != null) {
            password = StringUtils.trim(password);
        }

        // Authenticates user from input and registers its SecurityCOntext
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                username, password);
        Authentication authentication = authenticationManager
                .authenticate(auth);
        if (logger.isDebugEnabled()) {
            logger.debug("User '" + username + "' authenticated successfully");
        }

        SecurityContext securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
        if (logger.isDebugEnabled()) {
            logger.debug("Registering user '" + username
                    + "' to Acegi SecurtyContext");
        }
    }

    /**
     * @return the authenticationManager
     */
    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    /**
     * @param authenticationManager
     *            the authenticationManager to set
     */
    public void setAuthenticationManager(
            AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

}
