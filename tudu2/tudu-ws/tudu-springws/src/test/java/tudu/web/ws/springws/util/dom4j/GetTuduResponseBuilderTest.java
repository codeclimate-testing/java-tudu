package tudu.web.ws.springws.util.dom4j;

import junit.framework.TestCase;
import org.dom4j.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Gildas Cuisinier (hikage@hikage.be)
 */
public class GetTuduResponseBuilderTest extends TestCase {

    private GetTuduResponseBuilder builder;

    public void setUp() {

        this.builder = new GetTuduResponseBuilder();
    }

    public void testCreateEmptyResponse() {

        Document document = DocumentHelper.createDocument();
        builder.newResponse(document);

        Element response = builder.createResponse();
        assertEquals("GetTuduResponse", response.getQName().getName());
        assertEquals("http://springws.ws.web.tudu", response.getQName().getNamespaceURI());
    }

    public void testCreateSingleElementResponse() {

        Document document = DocumentHelper.createDocument();
        builder.newResponse(document);

        builder.newTudu("todoId", "description", false, 10);

        Element response = builder.createResponse();

        XPath xpath = response.createXPath("//tudu:GetTuduResponse/tudu:Tudu");
        xpath.setNamespaceURIs(Collections.singletonMap("tudu", "http://springws.ws.web.tudu"));
        Node tuduNode = xpath.selectSingleNode(response);

        assertTuduNode(new Object[]{"todoId", "description", false, 10}, tuduNode);


    }

    public void testCreateManyElementsResponse() {

        Document document = DocumentHelper.createDocument();
        builder.newResponse(document);

        builder.newTudu("todoId", "description", false, 10);
        builder.newTudu("todoId2", "description2", true, 20);

        Element response = builder.createResponse();

        XPath xpath = response.createXPath("//tudu:GetTuduResponse/tudu:Tudu");
        xpath.setNamespaceURIs(Collections.singletonMap("tudu", "http://springws.ws.web.tudu"));
        List<Node> tuduNodes = xpath.selectNodes(response);


        assertEquals(2, tuduNodes.size());
        Node node1 = tuduNodes.get(0);
        Node node2 = tuduNodes.get(1);

        assertTuduNode(new Object[]{"todoId", "description", false, 10}, node1);
        assertTuduNode(new Object[]{"todoId2", "description2", true, 20}, node2);


    }

    private void assertTuduNode(Object[] objects, Node tuduNode) {

        XPath completetedPath = tuduNode.createXPath("tudu:completed");
        XPath priorityPath = tuduNode.createXPath("tudu:priority");
        XPath descriptionPath = tuduNode.createXPath("tudu:description");
        XPath tuduIdPath = tuduNode.createXPath("tudu:todoId");
        Map<String, String> namespaceMap = Collections.singletonMap("tudu", "http://springws.ws.web.tudu");
        completetedPath.setNamespaceURIs(namespaceMap);
        priorityPath.setNamespaceURIs(namespaceMap);
        descriptionPath.setNamespaceURIs(namespaceMap);
        tuduIdPath.setNamespaceURIs(namespaceMap);

        assertEquals(objects[0], tuduIdPath.selectSingleNode(tuduNode).getStringValue());
        assertEquals(objects[1], descriptionPath.selectSingleNode(tuduNode).getStringValue());
        assertEquals(objects[2], Boolean.valueOf(completetedPath.selectSingleNode(tuduNode).getStringValue()));
        assertEquals(objects[3], Integer.valueOf(priorityPath.selectSingleNode(tuduNode).getStringValue()));


    }
}
