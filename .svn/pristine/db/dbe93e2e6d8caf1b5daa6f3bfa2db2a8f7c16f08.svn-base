package tudu.domain.model.comparator;

import java.util.Comparator;

import tudu.domain.model.Todo;

/**
 * Comparator used to sort todos by their description.
 * 
 * @author Julien Dubois
 */
public class TodoByDescriptionComparator implements Comparator {

    public int compare(Object first, Object second) {
        Todo firstTodo = (Todo) first;
        Todo secondTodo = (Todo) second;
        
        if (firstTodo.isCompleted() && !secondTodo.isCompleted()) {
            return 1;
        } else if (!firstTodo.isCompleted() && secondTodo.isCompleted()) {
            return -1;
        }
        int order;
        order = firstTodo.getDescription().toLowerCase()
            .compareTo(secondTodo.getDescription().toLowerCase());
        
        if (order == 0) {
            order = firstTodo.getTodoId().compareTo(secondTodo.getTodoId());
        }
        return order;
    }
}
