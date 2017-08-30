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

import tudu.domain.model.User;
import tudu.service.UserAlreadyExistsException;
import tudu.service.UserManager;

/**
 * Register a new Tudu Lists user.
 * 
 * @author Julien Dubois
 */
public class RegisterAction extends TuduDispatchAction {

    private final Log log = LogFactory.getLog(RegisterAction.class);

    private UserManager userManager = null;

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Show the "register a new user" page.
     */
    @Override
    public ActionForward display(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        log.debug("Execute showRegistrationPage action");
        return mapping.findForward("register");
    }

    /**
     * Register a new user.
     */
    public ActionForward register(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        log.debug("Execute register action");
        DynaActionForm registerForm = (DynaActionForm) form;
        ActionMessages errors = form.validate(mapping, request);
        if (errors.size() != 0) {
            saveErrors(request, errors);
            return mapping.getInputForward();
        }
        String login = (String) registerForm.get("login");
        login = login.toLowerCase();
        String password = (String) registerForm.get("password");
        String firstName = (String) registerForm.get("firstName");
        String lastName = (String) registerForm.get("lastName");
        String email = (String) registerForm.get("email");
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        try {
            userManager.createUser(user);
        } catch (UserAlreadyExistsException e) {
            ActionMessage message = new ActionMessage(
                    "register.user.already.exists", login);

            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
            saveErrors(request, errors);
            return mapping.getInputForward();
        }
        request.setAttribute("login", login);
        return mapping.findForward("success");
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
