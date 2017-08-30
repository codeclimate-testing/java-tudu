package tudu.web.ws.xfire.service;

import java.io.ByteArrayOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.EasyMock;
import org.jdom.Document;

import tudu.service.UserManager;
import tudu.web.ws.xfire.service.impl.TodoListsServiceImpl;

/**
 * @author Frederic Dreyfus
 * 
 */
public class TodoListsWebServiceTest extends
        AbstractWebServiceTest<TodoListsServiceImpl> {

    protected Log logger = LogFactory.getLog(TodoListsWebServiceTest.class);

    /**
     * Just prints the WSDL
     * 
     * @throws Exception
     */
    public void testWsdl() throws Exception {
        registerDefaultService();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        getWSDL(getServiceName()).write(output);
        logger.info(output.toString());
    }

    /**
     * Test method for
     * {@link tudu.web.ws.xfire.service.TodoListsService#getAllTodoLists()}.
     */
    public void testGetAllTodoLists() throws Exception {
        TodoListsServiceImpl todoListsService = new TodoListsServiceImpl();
        UserManager userManagerMock = EasyMock.createMock(UserManager.class);
        EasyMock.expect(userManagerMock.getCurrentUser()).andReturn(
                getDefaultUser());
        EasyMock.replay(userManagerMock);
        todoListsService.setUserManager(userManagerMock);
        registerService(todoListsService);
        Document response = invokeService(getServiceName(),
                "/soapRequest/wssecurity/getAllTodoLists-WSSP.soap.xml");
        logger.info("SOAP response : \n" + convertDocumentToString(response));

        assertValid("//s:Envelope/s:Body/tudu:GetAllTodoListsResponse",
                response);
        assertXPathEquals(
                "//s:Envelope/s:Body/tudu:GetAllTodoListsResponse/tudu:TodoLists/tudu:todoLists[1]/tudu:listId",
                "listId1", response);
        assertXPathEquals(
                "//s:Envelope/s:Body/tudu:GetAllTodoListsResponse/tudu:TodoLists/tudu:todoLists[2]/tudu:listId",
                "listId2", response);
    }

    @Override
    protected Class<TodoListsServiceImpl> getClassUnderTest() {
        return TodoListsServiceImpl.class;
    }

}
