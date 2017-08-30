package tudu.web.ws.xfire.sdo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * A user's role.
 * 
 * @author Frederic Dreyfus
 */
@XmlType(name = "Role")
public class WSRole {

    /**
     * The user role
     */
    private String role;

    /**
     * Minimal constructor
     */
    public WSRole() {

    }

    /**
     * @return the role
     */
    @XmlElement(required = true)
    public String getRole() {
        return role;
    }

    /**
     * @param role
     *            the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

}
