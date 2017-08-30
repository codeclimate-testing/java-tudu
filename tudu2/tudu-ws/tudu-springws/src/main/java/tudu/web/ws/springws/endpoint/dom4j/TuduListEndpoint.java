package tudu.web.ws.springws.endpoint.dom4j;

import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.ws.server.endpoint.AbstractDom4jPayloadEndpoint;
import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.service.UserManager;
import tudu.web.ws.springws.util.dom4j.TuduListResponseBuilder;

/**
 * @author Gildas Cuisinier (hikage@hikage.be)
 */
public class TuduListEndpoint extends AbstractDom4jPayloadEndpoint {

    /**
     * Reference to the users manager
     */
    private UserManager userManager;

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    
    protected Element invokeInternal(Element element, Document document) throws Exception {


        User user = userManager.getCurrentUser();

        TuduListResponseBuilder builder = new TuduListResponseBuilder();
        builder.newResponse(document);

        for (TodoList todoList : user.getTodoLists()) {
            builder.newTuduList(todoList.getListId(), todoList.getName());
        }


        return builder.createResponse();


    }


}
