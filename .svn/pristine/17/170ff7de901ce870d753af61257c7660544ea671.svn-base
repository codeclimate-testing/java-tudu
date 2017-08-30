package tudu.web.ws.springws.endpoint.dom4j;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.springframework.ws.server.endpoint.AbstractDom4jPayloadEndpoint;
import tudu.domain.model.Todo;
import tudu.domain.model.TodoList;
import tudu.service.TodoListsManager;
import tudu.web.ws.springws.util.dom4j.GetTuduResponseBuilder;

import java.util.Collections;

/**
 * @author Gildas Cuisinier (hikage@hikage.be)
 */
public class GetTuduEndpoint extends AbstractDom4jPayloadEndpoint {

    /**
     * Reference to the tudu list manager
     */
    private TodoListsManager todoListsManager;


    /**
     * Tudu list manager setter
     *
     * @param todoListsManager the tudu list manager
     */
    public void setTodoListsManager(TodoListsManager todoListsManager) {
        this.todoListsManager = todoListsManager;
    }


    protected Element invokeInternal(Element requestElement, Document responseDocument) throws Exception {

        XPath xpath = requestElement.createXPath("//tudu:GetTuduRequest/tudu:id");
        xpath.setNamespaceURIs(Collections.singletonMap("tudu", "http://springws.ws.web.tudu"));
        Node id = xpath.selectSingleNode(requestElement);

        TodoList todoList = null;
        todoList = todoListsManager.findTodoList(id.getText());

        GetTuduResponseBuilder builder = new GetTuduResponseBuilder();

        builder.newResponse(responseDocument);

        for (Todo todo : todoList.getTodos()) {
            builder.newTudu(todo.getTodoId(), todo.getDescription(), todo.isCompleted(), todo.getPriority());
        }

        return builder.createResponse();
    }


}
