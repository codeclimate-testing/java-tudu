package tudu.web.ws.xfire;

import javax.xml.namespace.QName;

import org.codehaus.xfire.fault.FaultInfoException;

import tudu.web.ws.xfire.sdo.FaultDetail;
import tudu.web.ws.xfire.service.impl.AbstractService;

/**
 * <p>
 * A Web Service exception which represents a SOAP fault.
 * </p>
 * 
 * @author Frederic Dreyfus
 * 
 */
public class WSException extends FaultInfoException {

    private static final long serialVersionUID = 7814707290943021198L;

    /**
     * The detail part of the SOAP fault
     */
    private FaultDetail faultDetail;

    /**
     * Exception that creates a detail part containing error cause details.
     * 
     * @param cause
     *            the exception cause
     * @param message
     *            the main exception message
     */
    public WSException(Throwable cause, String message) {
        super(message, cause);
        faultDetail = new FaultDetail();
        faultDetail.setReason(cause.getLocalizedMessage());
    }

    /**
     * @param message
     *            the main exception message
     */
    public WSException(String message) {
        super(message);
    }

    /**
     * @param cause
     *            the error cause
     */
    public WSException(Throwable cause) {
        super(cause);
    }

    /**
     * @return the exception detail
     */
    public FaultDetail getFaultInfo() {
        return faultDetail;
    }

    /**
     * @return the detail part qualified name
     */
    public static QName getFaultName() {
        return new QName(AbstractService.NS, "TodoFault");
    }

}