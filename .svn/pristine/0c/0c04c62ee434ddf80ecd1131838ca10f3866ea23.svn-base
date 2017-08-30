package tudu.web;

import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.ui.rememberme.TokenBasedRememberMeServices;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * The Log out action.
 * 
 * @author Julien Dubois
 */
public class LogoutAction extends Action {

    private final Log log = LogFactory.getLog(LogoutAction.class);

    /**
     * Log out action.
     */
    @SuppressWarnings("unchecked")
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        log.debug("Execute action");
        // Remove the security information
        SecurityContextHolder.clearContext();

        // Remove all session data
        HttpSession session = request.getSession();
        for (Enumeration e = session.getAttributeNames(); e.hasMoreElements();) {
            session.removeAttribute((String) e.nextElement());
        }

        // Remove the cookie
        Cookie terminate = new Cookie(
                TokenBasedRememberMeServices.ACEGI_SECURITY_HASHED_REMEMBER_ME_COOKIE_KEY,
                null);
        terminate.setMaxAge(-1);
        terminate.setPath(request.getContextPath() + "/");
        response.addCookie(terminate);
        return mapping.findForward("logout");
    }
}
