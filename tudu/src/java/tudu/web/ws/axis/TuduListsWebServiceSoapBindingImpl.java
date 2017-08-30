package tudu.web.ws.axis;

/**
 * TuduListsWebServiceSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 * 
 * @author Julien Dubois
 */
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import tudu.web.ws.TuduListsWebService;

public class TuduListsWebServiceSoapBindingImpl extends ServletEndpointSupport implements tudu.web.ws.axis.AxisTuduLists{
    
    private TuduListsWebService service;
    
    public final void onInit() {
        this.service = (TuduListsWebService) getWebApplicationContext()
            .getBean("tuduListsWebService");
    }
    
    public tudu.web.ws.bean.WsTodoList[] getAllTodoLists() throws java.rmi.RemoteException {
        return service.getAllTodoLists();
    }

    public tudu.web.ws.bean.WsTodo[] getTodosByTodoList(java.lang.String in0) throws java.rmi.RemoteException {
        return service.getTodosByTodoList(in0);
    }

}
