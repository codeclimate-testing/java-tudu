package tudu.domain.model.comparator;

import java.util.Comparator;

import tudu.domain.model.Todo;

/**
 * Comparator used to sort todos by their description, in ascending order.
 * 
 * @author Julien Dubois
 */
public class TodoByDescriptionAscComparator implements Comparator {

    public int compare(Object first, Object second) {
        Todo firstTodo = (Todo) first;
        Todo secondTodo = (Todo) second;
        
        if (firstTodo.isCompleted() && !secondTodo.isCompleted()) {
            return 1;
        } else if (!firstTodo.isCompleted() && secondTodo.isCompleted()) {
            return -1;
        }
        int order;
        order = secondTodo.getDescription().toLowerCase()
            .compareTo(firstTodo.getDescription().toLowerCase());
        
        if (order == 0) {
            order = secondTodo.getTodoId().compareTo(firstTodo.getTodoId());
        }
        return order;
    }
}
