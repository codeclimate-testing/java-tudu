package tudu.web.ws.xfire.sdo;

import java.util.Collection;

import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Represents an array of TodoList.
 * </p>
 * <p>
 * Used as a workaround until Java5 collections are handled correctly by XFire.
 * </p>
 * 
 * @author Frederic Dreyfus
 * 
 */
@XmlType(name = "ArrayOfTodoList")
public class ArrayOfWSTodoList {

    /**
     * The list of TodoList
     */
    private Collection<WSTodoList> todoLists;

    /**
     * Minimal constructor
     */
    public ArrayOfWSTodoList() {

    }

    /**
     * @return the todoLists
     */
    public Collection<WSTodoList> getTodoLists() {
        return todoLists;
    }

    /**
     * @param todoLists
     *            the todoLists to set
     */
    public void setTodoLists(Collection<WSTodoList> todoLists) {
        this.todoLists = todoLists;
    }

}
