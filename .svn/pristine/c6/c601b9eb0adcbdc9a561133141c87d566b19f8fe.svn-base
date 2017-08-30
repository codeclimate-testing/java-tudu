package tudu.web.ws.xfire.sdo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import tudu.web.ws.xfire.service.impl.AbstractService;

/**
 * <p>
 * A fault detail. <br />
 * Represents the detail part of a SOAP fault.
 * </p>
 * 
 * @author Frederic Dreyfus
 * 
 */
@XmlType(name = "FaultDetail", namespace = AbstractService.NS)
public class FaultDetail {

    /**
     * A detailed explanation of the fault.
     */
    private String reason;

    /**
     * An error code to identify the fault.
     */
    private long errorCode;

    /**
     * Minimal constructor.
     */
    public FaultDetail() {

    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason
     *            the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the errorCode
     */
    @XmlElement(required = true, defaultValue = "-1")
    public long getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }

}
