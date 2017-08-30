package tudu.web.ws.xfire.sdo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import tudu.domain.model.User;

/**
 * A user.
 * 
 * @author Frederic Dreyfus
 * 
 * @see User
 */
@XmlType(name = "User")
public class WSUser {

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private Date creationDate;

    private Date lastAccessDate;

    private boolean enabled;

    private Set<WSRole> roles = new HashSet<WSRole>();

    /**
     * Minimal Constructor.
     */
    public WSUser() {

    }

    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate
     *            the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     *            the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastAccessDate
     */
    public Date getLastAccessDate() {
        return lastAccessDate;
    }

    /**
     * @param lastAccessDate
     *            the lastAccessDate to set
     */
    public void setLastAccessDate(Date lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login
     *            the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    @XmlTransient
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the roles
     */
    public Set<WSRole> getRoles() {
        return roles;
    }

    /**
     * @param roles
     *            the roles to set
     */
    public void setRoles(Set<WSRole> roles) {
        this.roles = roles;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WSUser)) {
            return false;
        }
        final WSUser user = (WSUser) o;

        if (!login.equals(user.login)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return login.hashCode();
    }

    public int compareTo(Object o) {
        WSUser that = (WSUser) o;
        return this.getLogin().compareTo(that.getLogin());
    }
}
