package tudu.web.ws.xfire.sdo;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import tudu.domain.model.Todo;

/**
 * A todo.
 * 
 * @author Frederic Dreyfus
 * 
 * @see Todo
 */
@XmlType(name = "Todo")
public class WSTodo {

    private String todoId;

    private Date creationDate;

    private String description;

    private int priority;

    private boolean completed;

    private int completion;

    private Date completionDate;

    private Date dueDate;

    private String assignedUserLogin;

    private String notes;

    private boolean subTodos;

    /**
     * Minimal constructor
     */
    public WSTodo() {

    }

    /**
     * @return the assignedUserLogin
     */
    @XmlElement(required = true)
    public String getAssignedUserLogin() {
        return assignedUserLogin;
    }

    /**
     * @param assignedUserLogin
     *            the assignedUserLogin to set
     */
    public void setAssignedUserLogin(String assignedUserLogin) {
        this.assignedUserLogin = assignedUserLogin;
    }

    /**
     * @return the completed
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * @param completed
     *            the completed to set
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * @return the completion
     */
    public int getCompletion() {
        return completion;
    }

    /**
     * @param completion
     *            the completion to set
     */
    public void setCompletion(int completion) {
        this.completion = completion;
    }

    /**
     * @return the completionDate
     */
    public Date getCompletionDate() {
        return completionDate;
    }

    /**
     * @param completionDate
     *            the completionDate to set
     */
    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate
     *            the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the description
     */
    @XmlElement(required = true)
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the dueDate
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate
     *            the dueDate to set
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes
     *            the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority
     *            the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @return the todoId
     */
    public String getTodoId() {
        return todoId;
    }

    /**
     * @param todoId
     *            the todoId to set
     */
    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    /**
     * @return the subTodos
     */
    public boolean isSubTodos() {
        return subTodos;
    }

    /**
     * @param subTodos
     *            the subTodos to set
     */
    public void setSubTodos(boolean subTodos) {
        this.subTodos = subTodos;
    }

}
