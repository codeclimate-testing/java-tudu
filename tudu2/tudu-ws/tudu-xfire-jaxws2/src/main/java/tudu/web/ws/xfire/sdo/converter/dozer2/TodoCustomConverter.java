package tudu.web.ws.xfire.sdo.converter.dozer2;

import java.util.Set;

import net.sf.dozer.util.mapping.MappingException;
import net.sf.dozer.util.mapping.converters.CustomConverter;
import tudu.domain.model.Todo;
import tudu.domain.model.User;
import tudu.web.ws.xfire.sdo.WSTodo;

/**
 * 
 * Custom Dozer2 converter between {@link Todo} and {@link WSTodo}.
 * 
 * @author Frederic Dreyfus
 * 
 */
// FIXME subTodos and assignUser
public class TodoCustomConverter implements CustomConverter {

    public Object convert(Object destination, Object source, Class destClass,
            Class sourceClass) {

        if (source instanceof Todo) {
            WSTodo dest;
            Todo todoSource = (Todo) source;
            if (destination == null) {
                dest = new WSTodo();
            } else {
                dest = (WSTodo) destination;
            }
            // Populate WSTodo instance from Todo state
            dest.setTodoId(todoSource.getTodoId());
            dest.setCreationDate(todoSource.getCreationDate());
            dest.setDescription(todoSource.getDescription());
            dest.setPriority(todoSource.getPriority());
            dest.setCompleted(todoSource.isCompleted());
            dest.setCompletion(todoSource.getCompletion());
            dest.setCompletionDate(todoSource.getCompletionDate());
            dest.setDueDate(todoSource.getDueDate());
            User assignedUser = todoSource.getAssignedUser();
            if (assignedUser != null) {
                dest.setAssignedUserLogin(assignedUser.getLogin());
            }
            dest.setNotes(todoSource.getNotes());
            Set<Todo> subTodos = todoSource.getSubTodos();
            if (subTodos != null && subTodos.size() > 0) {
                dest.setSubTodos(true);
            }

            return dest;
        } else if (source instanceof WSTodo) {
            Todo dest;
            WSTodo wsTodoSource = (WSTodo) source;
            if (destination == null) {
                dest = new Todo();
            } else {
                dest = (Todo) destination;
            }

            // Populate Todo instance from WSTodo state
            dest.setTodoId(wsTodoSource.getTodoId());
            dest.setCreationDate(wsTodoSource.getCreationDate());
            dest.setDescription(wsTodoSource.getDescription());
            dest.setPriority(wsTodoSource.getPriority());
            dest.setCompleted(wsTodoSource.isCompleted());
            dest.setCompletion(wsTodoSource.getCompletion());
            dest.setCompletionDate(wsTodoSource.getCompletionDate());
            dest.setDueDate(wsTodoSource.getDueDate());
            // User assignedUser = wsTodoSource.getAssignedUser();
            // if (assignedUser != null) {
            // dest.setAssignedUserLogin(assignedUser.getLogin());
            // }
            dest.setNotes(wsTodoSource.getNotes());
            // Set<Todo> subTodos = todoSource.getSubTodos();
            // if (subTodos != null && subTodos.size() > 0) {
            // dest.setSubTodos(true);
            // }
            return dest;
        } else {
            throw new MappingException(
                    "Converter TestCustomConverter used incorrectly. Arguments passed in were:"
                            + destination + " and " + source);
        }
    }
}
