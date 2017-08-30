package tudu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import tudu.service.ConfigurationManager;

/**
 * Application administration actions.
 * 
 * @author Julien Dubois
 */
public class AdministrationAction extends TuduDispatchAction {

    private final Log log = LogFactory.getLog(AdministrationAction.class);

    private ConfigurationManager configurationManager;
    
    public void setConfigurationManager(ConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }

    /**
     * Show the administration page action.
     */
    @Override
    public ActionForward display(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        log.debug("Execute display action");
        DynaActionForm administrationForm = (DynaActionForm) form;
        
        String smtpHost = configurationManager.getProperty("smtp.host").getValue();
        administrationForm.set("smtpHost", smtpHost);
        
        String smtpPort = configurationManager.getProperty("smtp.port").getValue();
        administrationForm.set("smtpPort", smtpPort);
        
        String smtpUser = configurationManager.getProperty("smtp.user").getValue();
        administrationForm.set("smtpUser", smtpUser);
        
        String smtpPassword = configurationManager.getProperty("smtp.password").getValue();
        administrationForm.set("smtpPassword", smtpPassword);
        
        String smtpFrom =configurationManager.getProperty("smtp.from").getValue();
        administrationForm.set("smtpFrom", smtpFrom);
        
        return mapping.findForward("administration");
    }
    
    /**
     * Show the administration page action.
     */
    public ActionForward update(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        log.debug("Execute update action");
        DynaActionForm administrationForm = (DynaActionForm) form;
        String smtpHost = (String) administrationForm.get("smtpHost");
        String smtpPort = (String) administrationForm.get("smtpPort");
        String smtpUser = (String) administrationForm.get("smtpUser");
        String smtpPassword = (String) administrationForm.get("smtpPassword");
        String smtpFrom = (String) administrationForm.get("smtpFrom");
        configurationManager.updateEmailProperties(smtpHost, smtpPort, smtpUser, smtpPassword,
                smtpFrom);
        
        request.setAttribute("success", "true");
        return display(mapping, form, request, response);
    }
    
    /**
     * Dump the database using DBUnit.
     */
    public ActionForward dumpDatabase(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        log.debug("Execute dumpDatabase action");
        return mapping.findForward("dump.database");
    }
}
