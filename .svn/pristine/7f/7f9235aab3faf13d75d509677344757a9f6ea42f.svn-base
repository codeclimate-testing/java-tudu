package tudu.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import tudu.domain.model.User;
import tudu.service.ConfigurationManager;
import tudu.service.UserManager;

/**
 * Application administration actions.
 * 
 * @author Julien Dubois
 */
public class AdministrationAction extends TuduDispatchAction {

    private ConfigurationManager configurationManager;

    private UserManager userManager;

    public void setConfigurationManager(
            ConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Show the administration page action.
     */
    @Override
    public ActionForward display(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        DynaActionForm administrationForm = (DynaActionForm) form;
        String page = (String) administrationForm.get("page");

        if (page == null || page.equals("")) {
            page = "configuration";
        }

        if (page.equals("configuration")) {
            request.setAttribute("page", "configuration");

            String propertyStaticPath = this.configurationManager.getProperty(
                    "application.static.path").getValue();

            administrationForm.set("propertyStaticPath", propertyStaticPath);

            String googleAnalyticsKey = configurationManager.getProperty(
                    "google.analytics.key").getValue();

            administrationForm.set("googleAnalyticsKey", googleAnalyticsKey);

            String smtpHost = configurationManager.getProperty("smtp.host")
                    .getValue();
            administrationForm.set("smtpHost", smtpHost);

            String smtpPort = configurationManager.getProperty("smtp.port")
                    .getValue();
            administrationForm.set("smtpPort", smtpPort);

            String smtpUser = configurationManager.getProperty("smtp.user")
                    .getValue();
            administrationForm.set("smtpUser", smtpUser);

            String smtpPassword = configurationManager.getProperty(
                    "smtp.password").getValue();
            administrationForm.set("smtpPassword", smtpPassword);

            String smtpFrom = configurationManager.getProperty("smtp.from")
                    .getValue();
            administrationForm.set("smtpFrom", smtpFrom);

        } else if (page.equals("users")) {
            request.setAttribute("page", "users");
            request.setAttribute("numberOfUsers", this.userManager
                    .getNumberOfUsers());

        } else if (page.equals("database")) {
            request.setAttribute("page", "database");
        }

        return mapping.findForward("administration");
    }

    /**
     * Update the application configuration.
     */
    public ActionForward updateConfiguration(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {

        DynaActionForm administrationForm = (DynaActionForm) form;
        String staticPath = (String) administrationForm
                .get("propertyStaticPath");
        String googleAnalyticsKey = (String) administrationForm
                .get("googleAnalyticsKey");

        this.configurationManager.updateApplicationProperties(staticPath,
                googleAnalyticsKey);

        String smtpHost = (String) administrationForm.get("smtpHost");
        String smtpPort = (String) administrationForm.get("smtpPort");
        String smtpUser = (String) administrationForm.get("smtpUser");
        String smtpPassword = (String) administrationForm.get("smtpPassword");
        String smtpFrom = (String) administrationForm.get("smtpFrom");
        configurationManager.updateEmailProperties(smtpHost, smtpPort,
                smtpUser, smtpPassword, smtpFrom);

        request.setAttribute("success", "true");
        return display(mapping, form, request, response);
    }

    /**
     * Search for users.
     */
    public ActionForward searchUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        DynaActionForm administrationForm = (DynaActionForm) form;
        String loginStart = (String) administrationForm.get("loginStart");
        if (loginStart == null) {
            loginStart = "";
        }
        List<User> users = this.userManager.findUsersByLogin(loginStart);
        request.setAttribute("users", users);
        return display(mapping, form, request, response);
    }

    /**
     * Disable a user.
     */
    public ActionForward disableUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        DynaActionForm administrationForm = (DynaActionForm) form;
        String login = (String) administrationForm.get("login");
        if (login == null || login.equals("")) {
            return searchUser(mapping, form, request, response);
        }
        this.userManager.disableUser(login);
        request.setAttribute("success", "true");
        return searchUser(mapping, form, request, response);
    }

    /**
     * Enable a user.
     */
    public ActionForward enableUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        DynaActionForm administrationForm = (DynaActionForm) form;
        String login = (String) administrationForm.get("login");
        if (login == null || login.equals("")) {
            return searchUser(mapping, form, request, response);
        }
        this.userManager.enableUser(login);
        request.setAttribute("success", "true");
        return searchUser(mapping, form, request, response);
    }

    /**
     * Dump the database using DBUnit.
     */
    public ActionForward dumpDatabase(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        return mapping.findForward("dump.database");
    }
}
