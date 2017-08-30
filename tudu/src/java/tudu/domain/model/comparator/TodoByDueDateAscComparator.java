package tudu.domain.model.comparator;

import java.util.Comparator;

import tudu.domain.model.Todo;

/**
 * Comparator used to sort todos by their due date, in ascending order.
 * 
 * @author Julien Dubois
 */
public class TodoByDueDateAscComparator implements Comparator {

    public int compare(Object first, Object second) {
        Todo firstTodo = (Todo) first;
        Todo secondTodo = (Todo) second;
        
        if (firstTodo.isCompleted() && !secondTodo.isCompleted()) {
            return 1;
        } else if (!firstTodo.isCompleted() && secondTodo.isCompleted()) {
            return -1;
        }
        int order = 0;
        if (secondTodo.getDueDate() == null && firstTodo.getDueDate() != null) {
            return 1;
        } else if (secondTodo.getDueDate() != null && firstTodo.getDueDate() == null) {
            return -1;
        } else if (secondTodo.getDueDate() != null && firstTodo.getDueDate() != null) {
            long compare = secondTodo.getDueDate().getTime() - firstTodo.getDueDate().getTime();
            if (compare > 0) {
            	order = 1;
            } else if (compare < 0) {
            	order = -1;
            }
        }
        if (order == 0) {
            order = secondTodo.getTodoId().compareTo(firstTodo.getTodoId());
        }
        return order;
    }
}
