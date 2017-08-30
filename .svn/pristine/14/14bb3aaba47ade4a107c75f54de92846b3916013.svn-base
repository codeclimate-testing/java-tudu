package tudu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.springframework.orm.ObjectRetrievalFailureException;

import tudu.domain.model.User;
import tudu.service.UserManager;

/**
 * Recover a user's lost password.
 * 
 * @author Julien Dubois
 */
public class RecoverPasswordAction extends TuduDispatchAction {

    private final Log log = LogFactory.getLog(RecoverPasswordAction.class);

    private UserManager userManager = null;

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Show the recover password page action.
     */
    @Override
    public ActionForward display(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        log.debug("Execute display action");
        DynaActionForm recoverPasswordForm = (DynaActionForm) form;
        recoverPasswordForm.set("login", "");
        return mapping.findForward("recover");
    }

    /**
     * Send an email with the new password to the user.
     */
    public ActionForward sendMail(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        log.debug("Execute sendMail action");
        ActionMessages errors = form.validate(mapping, request);
        if (errors.size() != 0) {
            saveErrors(request, errors);
            return mapping.getInputForward();
        }
        DynaActionForm recoverPasswordForm = (DynaActionForm) form;
        String login = (String) recoverPasswordForm.get("login");
        login = login.toLowerCase();
        User user;
        try {
            user = userManager.findUser(login);
        } catch (ObjectRetrievalFailureException orfe) {
            ActionMessage message = new ActionMessage(
                    "recover.password.no.user", login);

            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
            saveErrors(request, errors);
            return mapping.getInputForward();
        }
        if (user.getEmail() == null || user.getEmail().equals("")) {
            ActionMessage message = new ActionMessage(
                    "recover.password.no.email", login);

            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
            saveErrors(request, errors);
            return mapping.getInputForward();
        }
        try {
            userManager.sendPassword(user);
            request.setAttribute("success", "true");
        } catch (RuntimeException e) {
            log.warn("Mail sending error : " + e.getMessage());
            ActionMessage message = new ActionMessage(
                    "recover.password.smtp.error", login);

            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
            saveErrors(request, errors);
            return mapping.getInputForward();
        }
        return mapping.findForward("recover");
    }

    /**
     * Cancel the action.
     */
    @Override
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        log.debug("Execute cancel action");
        return mapping.findForward("cancel");
    }
}
