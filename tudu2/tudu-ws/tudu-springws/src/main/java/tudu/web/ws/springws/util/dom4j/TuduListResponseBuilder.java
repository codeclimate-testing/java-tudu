package tudu.web.ws.springws.util.dom4j;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;


/**
 * @author Gildas Cuisinier (hikage@hikage.be)
 */
public class TuduListResponseBuilder {

    private Element root;
    private final QName rootQname;
    private final QName nameQname;
    private final QName listIdQname;
    private final QName tuduQname;


    public TuduListResponseBuilder() {
        Namespace namespace = new Namespace("tudu", "http://springws.ws.web.tudu");
        this.rootQname = new QName("GetAllTuduListsResponse", namespace);
        this.tuduQname = new QName("TuduList", namespace);
        this.listIdQname = new QName("listId", namespace);
        this.nameQname = new QName("name", namespace);

    }

    public TuduListResponseBuilder newResponse(Document document) {
        this.root = document.addElement(rootQname);
        return this;
    }

    public TuduListResponseBuilder newTuduList(String id, String name) {

        Element tudo = this.root.addElement(tuduQname);
        tudo.addElement(listIdQname).addText(id);
        tudo.addElement(nameQname).addText(name);


        return this;


    }

    public Element createResponse() {
        Element response = this.root;
        root = null;
        return response;
    }

}