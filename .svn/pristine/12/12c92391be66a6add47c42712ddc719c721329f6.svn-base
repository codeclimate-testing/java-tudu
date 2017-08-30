package tudu.domain.model.comparator;

import java.util.Comparator;

import tudu.domain.model.Todo;

/**
 * Comparator used to sort todos by their due date.
 * 
 * @author Julien Dubois
 */
public class TodoByDueDateComparator implements Comparator {

    public int compare(Object first, Object second) {
        Todo firstTodo = (Todo) first;
        Todo secondTodo = (Todo) second;
        
        if (firstTodo.isCompleted() && !secondTodo.isCompleted()) {
            return 1;
        } else if (!firstTodo.isCompleted() && secondTodo.isCompleted()) {
            return -1;
        }
        int order = 0;
        if (firstTodo.getDueDate() == null && secondTodo.getDueDate() != null) {
            return 1;
        } else if (firstTodo.getDueDate() != null && secondTodo.getDueDate() == null) {
            return -1;
        } else if (firstTodo.getDueDate() != null && secondTodo.getDueDate() != null) {
            long compare = firstTodo.getDueDate().getTime() - secondTodo.getDueDate().getTime();
            if (compare > 0) {
            	order = 1;
            } else if (compare < 0) {
            	order = -1;
            }
        }
        if (order == 0) {
            order = firstTodo.getTodoId().compareTo(secondTodo.getTodoId());
        }
        return order;
    }
}
