package tudu.web.ws.xfire.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.jws.WebService;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import org.acegisecurity.AuthenticationManager;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.xfire.handler.Handler;
import org.codehaus.xfire.jaxb2.JaxbServiceFactory;
import org.codehaus.xfire.security.wss4j.WSS4JInHandler;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.ServiceFactory;
import org.codehaus.xfire.service.invoker.BeanInvoker;
import org.codehaus.xfire.test.AbstractXFireTest;
import org.codehaus.xfire.util.dom.DOMInHandler;
import org.easymock.EasyMock;
import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import tudu.domain.model.Todo;
import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.web.ws.xfire.security.callback.PlainPasswordCallbackHandler;
import tudu.web.ws.xfire.security.handler.SetupCallbackToContextHandler;
import tudu.web.ws.xfire.service.impl.AbstractService;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * <p>
 * Abstract WebService test case.
 * </p>
 * <p>
 * All WebService tests must extend this class
 * </p>
 * 
 * @author Frederic Dreyfus
 * 
 */
public abstract class AbstractWebServiceTest<T extends AbstractService> extends
        AbstractXFireTest {

    protected Log logger = LogFactory.getLog(AbstractWebServiceTest.class);

    /**
     * @return the class under test
     */
    protected abstract Class<T> getClassUnderTest();

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ServiceFactory factory = new JaxbServiceFactory(getXFire()
                .getTransportManager());
        setServiceFactory(factory);
        addNamespace("tudu", AbstractService.NS);
    }

    /**
     * Registers an instanciated service object. Adds default mapper and all
     * handlers to the service.
     * 
     * @param serviceBean
     *            the service to register
     */
    protected void registerService(AbstractService serviceBean) {
        Service service = getServiceFactory().create(getClassUnderTest());

        if (serviceBean != null) {
            serviceBean.setMapper(getDefaultMapper());
            service.setInvoker(new BeanInvoker(serviceBean));
        }

        service.addInHandler(new DOMInHandler());
        service.addInHandler(getSetupCallbackToContextHandler());
        service.addInHandler(getWSS4JInHandler());
        getServiceRegistry().register(service);
    }

    protected void registerDefaultService() {
        this.registerService(null);
    }

    /**
     * Converts a {@link Document} to a String
     * 
     * @param document
     *            the document to convert
     * @return the converted document as a string
     * @throws IOException
     */
    protected String convertDocumentToString(Document document)
            throws IOException {
        XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat());
        StringWriter writer = new StringWriter();
        outputter.output(document, writer);
        return writer.toString();
    }

    /**
     * Returns the name of the service as it appears in
     * {@link WebService#serviceName()}
     * 
     * @return the name of the service
     * 
     * @see WebService#serviceName()
     */
    public String getServiceName() {
        return getClassUnderTest().getAnnotation(WebService.class)
                .serviceName();
    }

    /**
     * @return an initialized WSS4J hanlder
     */
    protected WSS4JInHandler getWSS4JInHandler() {
        WSS4JInHandler wss4jInHandler = new WSS4JInHandler();
        Properties props = new Properties();
        props.setProperty("action", "UsernameToken");
        wss4jInHandler.setProperties(props);
        return wss4jInHandler;
    }

    /**
     * Returns an instance of {@link SetupCallbackToContextHandler} that
     * authenticates a user successfully.
     * 
     * @return an initialized instance of {@link SetupCallbackToContextHandler}
     */
    protected Handler getSetupCallbackToContextHandler() {
        SetupCallbackToContextHandler setupCallbackToContextHandler = new SetupCallbackToContextHandler();
        PlainPasswordCallbackHandler plainPasswordCallbackHandler = new PlainPasswordCallbackHandler();

        AuthenticationManager authenticationManagerMock = EasyMock
                .createMock(AuthenticationManager.class);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                "user", "user");
        EasyMock.expect(authenticationManagerMock.authenticate(auth))
                .andReturn(auth);
        plainPasswordCallbackHandler
                .setAuthenticationManager(authenticationManagerMock);
        setupCallbackToContextHandler
                .setPasswordCallbackHandler(plainPasswordCallbackHandler);
        return setupCallbackToContextHandler;
    }

    /**
     * @return the default dozer mapper
     */
    protected MapperIF getDefaultMapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        List<String> mappingFiles = new ArrayList<String>();
        mappingFiles.add("dozerBeanMapping.xml");
        mapper.setMappingFiles(mappingFiles);
        return mapper;
    }

    /**
     * @return a default user
     */
    protected User getDefaultUser() {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("user", User.class);
        xstream.alias("todoLists", Set.class);
        xstream.alias("todoList", TodoList.class);
        xstream.alias("todos", Set.class);
        xstream.alias("todo", Todo.class);
        User defaultUsers = (User) xstream.fromXML(getClass()
                .getResourceAsStream("/tudu/web/ws/xfire/default-user.xml"));
        return defaultUsers;
    }
}
