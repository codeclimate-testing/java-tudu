package tudu.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A Todo List.
 * 
 * @author Julien Dubois
 */
public class TodoList implements Serializable, Comparable {

    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 4048798961366546485L;

    private String listId;

    private String name;
    
    private boolean rssAllowed;
    
    private Date lastUpdate;

    private Set<Todo> todos = new HashSet<Todo>();
    
    private Set<User> users = new HashSet<User>();

    public String getListId() {
        return listId;
    }

    public String getName() {
        return name;
    }

    public Set<Todo> getTodos() {
        return todos;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRssAllowed() {
        return rssAllowed;
    }

    public void setRssAllowed(boolean rssAllowed) {
        this.rssAllowed = rssAllowed;
    }
    
    public Date getLastUpdate() {
        return lastUpdate;
    }
    
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setTodos(Set<Todo> todos) {
        this.todos = todos;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public int compareTo(Object o) {
        TodoList that = (TodoList) o;
        return (this.getName().toLowerCase() 
                + this.getListId())
                .compareTo(that.getName().toLowerCase()
                + that.getListId());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TodoList)) {
            return false;
        }

        final TodoList todoList = (TodoList) o;

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
