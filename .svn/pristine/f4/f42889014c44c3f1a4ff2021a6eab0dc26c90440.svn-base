package tudu.web.ws.xfire.sdo;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Represents an array of Todo.
 * </p>
 * <p>
 * Used as a workaround until Java5 collections are handled correctly by XFire.
 * </p>
 * 
 * @author Frederic Dreyfus
 * 
 */
@XmlType(name = "ArrayOfTodo")
public class ArrayOfWSTodo {

    /**
     * The list of todos
     */
    private Collection<WSTodo> todos;

    /**
     * Minimal constructor
     */
    public ArrayOfWSTodo() {

    }

    /**
     * @return the todos
     */
    @XmlElement(name = "Todos")
    public Collection<WSTodo> getTodos() {
        return todos;
    }

    /**
     * @param todos
     *            the todos to set
     */
    public void setTodos(Collection<WSTodo> todos) {
        this.todos = todos;
    }

}
