package tudu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * Base DispatchAction for Tudu Lists.
 * 
 * @author Julien Dubois
 */
public abstract class TuduDispatchAction extends DispatchAction {

    private final Log log = LogFactory.getLog(TuduDispatchAction.class);

    /**
     * Show the default page.
     */
    public abstract ActionForward display(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response);

    /**
     * Cancel the action.
     */
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        log.debug("Execute cancel action");
        return display(mapping, form, request, response);
    }

    /**
     * Go to the "unspecified" page.
     */
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        log.debug("Execute unspecified action");
        return display(mapping, form, request, response);
    }
}
