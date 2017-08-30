package otn.todo.client;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * A to-do.
 *
 * @author Julien Dubois
 */
public class Todo implements IsSerializable, Cloneable {

    private String description;

    private boolean done;

    public Todo() {
    }

    public Todo(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    
    public Object clone() {
        Todo todoClone = new Todo();
        todoClone.setDescription(this.getDescription());
        todoClone.setDone(this.isDone());
        return todoClone;
    }
}
