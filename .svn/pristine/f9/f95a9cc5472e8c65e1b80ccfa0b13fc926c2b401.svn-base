package tudu.web.ws.xfire.security.handler;

import javax.security.auth.callback.CallbackHandler;

import org.apache.ws.security.handler.WSHandler;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.codehaus.xfire.MessageContext;
import org.codehaus.xfire.handler.AbstractHandler;
import org.codehaus.xfire.handler.Phase;
import org.codehaus.xfire.security.wss4j.WSS4JInHandler;

/**
 * This handler sets the <code>passwordCallbackHandler</code> to the
 * {@link MessageContext}. This will allow to inject an instance of the
 * <code>passwordCallbackHandler</code> instead of using a class which will be
 * instanciated at each call.
 * 
 * @author Frederic Dreyfus
 * @see WSHandler
 */
public class SetupCallbackToContextHandler extends AbstractHandler {

    private CallbackHandler passwordCallbackHandler;

    @SuppressWarnings("unchecked")
    public SetupCallbackToContextHandler() {
        super();
        setPhase(Phase.PARSE);
        getBefore().add(WSS4JInHandler.class.getName());
    }

    public void invoke(MessageContext context) throws Exception {
        context.setProperty(WSHandlerConstants.PW_CALLBACK_REF,
                passwordCallbackHandler);
    }

    /**
     * @return the passwordCallbackHandler
     */
    public CallbackHandler getPasswordCallbackHandler() {
        return passwordCallbackHandler;
    }

    /**
     * @param passwordCallbackHandler
     *            the passwordCallbackHandler to set
     */
    public void setPasswordCallbackHandler(
            CallbackHandler passwordCallbackHandler) {
        this.passwordCallbackHandler = passwordCallbackHandler;
    }

}
