package tudu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tudu.domain.RolesEnum;

/**
 * The Welcome action.
 * 
 * @author Julien Dubois
 */
public class WelcomeAction extends Action {

    private final Log log = LogFactory.getLog(WelcomeAction.class);

    /**
     * Welcome action.
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        log.debug("Execute action");
        if (request.isUserInRole(RolesEnum.ROLE_USER.toString())) {
            return mapping.findForward("showTodosAction");
        }
        return mapping.findForward("login");
    }

}
