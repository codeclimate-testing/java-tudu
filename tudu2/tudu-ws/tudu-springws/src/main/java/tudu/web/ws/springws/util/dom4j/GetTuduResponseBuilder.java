package tudu.web.ws.springws.util.dom4j;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;


/**
 *
 * @author Gildas Cuisinier (hikage@hikage.be)
 */
public class GetTuduResponseBuilder {

    private Element root;
    private final QName rootQname;
    private final QName tuduQname;
    private final QName completedQname;
    private final QName descriptionQname;
    private final QName priorityQname;
    private final QName todoIdQname;


    public GetTuduResponseBuilder() {
        Namespace namespace = new Namespace("tudu", "http://springws.ws.web.tudu");
        this.rootQname = new QName("GetTuduResponse", namespace);
        this.tuduQname = new QName("Tudu", namespace);
        this.completedQname = new QName("completed", namespace);
        this.descriptionQname = new QName("description", namespace);
        this.todoIdQname = new QName("todoId", namespace);
        this.priorityQname = new QName("priority", namespace);
    }

    public GetTuduResponseBuilder newResponse(Document document) {
        this.root = document.addElement(rootQname);
        return this;
    }

    public GetTuduResponseBuilder newTudu(String todoId, String description, boolean completed, Integer priority) {

        Element tudo = this.root.addElement(tuduQname);
        tudo.addElement(this.todoIdQname).addText(todoId);
        tudo.addElement(this.priorityQname).addText(Integer.toString(priority));
        tudo.addElement(this.completedQname).addText(Boolean.toString(completed));
        tudo.addElement(this.descriptionQname).addText(description);
        return this;


    }

    public Element createResponse() {
        Element response = this.root;
        root = null;
        return response;
    }

}