package tudu.web.ws.xfire.service;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import tudu.web.ws.xfire.WSException;
import tudu.web.ws.xfire.sdo.WSUser;
import tudu.web.ws.xfire.service.impl.AbstractService;

/**
 * <p>
 * Exposed User Services.
 * </p>
 * <p>
 * For security concerns, creation/update of users should not be available
 * through Web Services for now.
 * </p>
 * 
 * @author Frederic Dreyfus
 */
@WebService(name = "UserServicePort", targetNamespace = AbstractService.NS)
public interface UserService {

    /**
     * Gets user details.
     * 
     * @return the current user details
     */
    @WebMethod(operationName = "GetUserDetails", action = AbstractService.NS
            + "/GetUserDetails")
    @WebResult(name = "UserDetails")
    public WSUser getUserDetails() throws WSException;

}
