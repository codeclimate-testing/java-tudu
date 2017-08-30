package tudu.web.ws.xfire.sdo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlType;

import tudu.domain.model.TodoList;

/**
 * A Todo List.
 * 
 * @author Frederic Dreyfus
 * 
 * @see TodoList
 */
@XmlType(name = "TodoList")
public class WSTodoList {

    private String listId;

    private String name;

    private Date lastUpdate;

    private Set<WSTodo> todos = new HashSet<WSTodo>();

    /**
     * Minimal constructor
     */
    public WSTodoList() {

    }

    /**
     * @return the lastUpdate
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate
     *            the lastUpdate to set
     */
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return the listId
     */
    public String getListId() {
        return listId;
    }

    /**
     * @param listId
     *            the listId to set
     */
    public void setListId(String listId) {
        this.listId = listId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the todos
     */
    public Set<WSTodo> getTodos() {
        return todos;
    }

    /**
     * @param todos
     *            the todos to set
     */
    public void setTodos(Set<WSTodo> todos) {
        this.todos = todos;
    }

    public int compareTo(Object o) {
        WSTodoList that = (WSTodoList) o;
        return (this.getName().toLowerCase() + this.getListId()).compareTo(that
                .getName().toLowerCase()
                + that.getListId());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WSTodoList)) {
            return false;
        }

        final WSTodoList todoList = (WSTodoList) o;

        if (listId != null ? !listId.equals(todoList.listId)
                : todoList.listId != null) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        return (listId != null ? listId.hashCode() : 0);
    }

}
