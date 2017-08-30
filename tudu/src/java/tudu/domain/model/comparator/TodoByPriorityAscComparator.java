package tudu.domain.model.comparator;

import java.util.Comparator;

import tudu.domain.model.Todo;

/**
 * Comparator used to sort todos by their priority, in ascending order.
 * 
 * @author Julien Dubois
 */
public class TodoByPriorityAscComparator implements Comparator {

    public int compare(Object first, Object second) {
        Todo firstTodo = (Todo) first;
        Todo secondTodo = (Todo) second;

        int order = firstTodo.getPriority() - secondTodo.getPriority();
        if (firstTodo.isCompleted()) {
            order += 10000;
        }
        if (secondTodo.isCompleted()) {
            order -= 10000;
        }
        if (order == 0) {
            order = (secondTodo.getDescription() + secondTodo.getTodoId())
                    .compareTo(firstTodo.getDescription()
                            + firstTodo.getTodoId());
        }
        return order;
    }
}
