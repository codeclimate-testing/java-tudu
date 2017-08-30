package tudu.web.ws.xfire.service.impl;

import tudu.service.UserManager;
import net.sf.dozer.util.mapping.MapperIF;

/**
 * Parent class of all exposed services.
 * 
 * @author Frederic Dreyfus
 * 
 */
public abstract class AbstractService {

    /**
     * The common namespace
     */
    public static final String NS = "http://web.ws.tudu2/v1";

    /**
     * The Dozer2 mapper
     */
    protected MapperIF mapper;

    /**
     * The user manager
     */
    protected UserManager userManager;

    /**
     * @return the mapper
     */
    public MapperIF getMapper() {
        return mapper;
    }

    /**
     * @param mapper
     *            the mapper to set
     */
    public void setMapper(MapperIF dozerMapper) {
        this.mapper = dozerMapper;
    }

    /**
     * @return the userManager
     */
    public UserManager getUserManager() {
        return userManager;
    }

    /**
     * @param userManager
     *            the userManager to set
     */
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

}
