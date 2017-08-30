package tudu.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.ui.rememberme.TokenBasedRememberMeServices;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        log.debug("Execute action");
        request.getSession().invalidate();
        Cookie terminate = new Cookie(TokenBasedRememberMeServices.ACEGI_SECURITY_HASHED_REMEMBER_ME_COOKIE_KEY, null);
        terminate.setMaxAge(0);
        response.addCookie(terminate);
        return mapping.findForward("logout");
    }
}
