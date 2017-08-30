package tudu.web.ws.bean;

/**
 * A Todo exposed as a Web Service.
 * 
 * @author Julien Dubois
 */
public class WsTodo  implements java.io.Serializable {

    private static final long serialVersionUID = 3190400595759700097L;

    private boolean completed;

    private java.lang.String description;

    private int priority;

    private java.lang.String todoId;

    /**
     * Gets the completed value for this WsTodo.
     * 
     * @return completed
     */
    public boolean isCompleted() {
        return completed;
    }


    /**
     * Sets the completed value for this WsTodo.
     * 
     * @param completed
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    /**
     * Gets the description value for this WsTodo.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this WsTodo.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the priority value for this WsTodo.
     * 
     * @return priority
     */
    public int getPriority() {
        return priority;
    }


    /**
     * Sets the priority value for this WsTodo.
     * 
     * @param priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }


    /**
     * Gets the todoId value for this WsTodo.
     * 
     * @return todoId
     */
    public java.lang.String getTodoId() {
        return todoId;
    }


    /**
     * Sets the todoId value for this WsTodo.
     * 
     * @param todoId
     */
    public void setTodoId(java.lang.String todoId) {
        this.todoId = todoId;
    }

}
