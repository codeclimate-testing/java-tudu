package tudu.web.gwt.client;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * A todo.
 *
 * @author Julien Dubois
 */
public class SerializableTodo implements IsSerializable, Cloneable {

    private String todoId;
    
    private String description;
    
    private int priority;

    private boolean completed;

    public SerializableTodo() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTodoId() {
        return todoId;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    public Object clone() {
        SerializableTodo todoClone = new SerializableTodo();
        todoClone.setDescription(this.getDescription());
        todoClone.setCompleted(this.isCompleted());
        return todoClone;
    }
}
