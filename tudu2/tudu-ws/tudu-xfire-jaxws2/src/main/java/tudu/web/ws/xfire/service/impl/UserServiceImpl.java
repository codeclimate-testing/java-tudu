package tudu.web.ws.xfire.service.impl;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import tudu.domain.model.User;
import tudu.web.ws.xfire.sdo.WSUser;
import tudu.web.ws.xfire.service.UserService;

/**
 * Implementation of the User Service.
 * 
 * @author Frederic Dreyfus
 */
@WebService(endpointInterface = "tudu.web.ws.xfire.service.UserService", serviceName = "UserService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class UserServiceImpl extends AbstractService implements UserService {

    /**
     * Minimal constructor
     */
    public UserServiceImpl() {

    }

    public WSUser getUserDetails() {
        User user = userManager.getCurrentUser();
        return (WSUser) mapper.map(user, WSUser.class);
    }

}
