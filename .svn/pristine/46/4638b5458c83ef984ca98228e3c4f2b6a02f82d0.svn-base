package otn.todo.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import otn.todo.client.Todo;
import otn.todo.client.TodoListBackupService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class TodoListBackupServiceImpl extends RemoteServiceServlet implements
		TodoListBackupService {

	private static final String TODOLIST_KEY = "TODOLIST_KEY";
	
	public void saveTodoList(List todoList) {
		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession session = request.getSession();
		session.setAttribute(TODOLIST_KEY, todoList);
	}

	public List getTodoList() {
		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession session = request.getSession();
		if (session.getAttribute(TODOLIST_KEY) == null) {
			List todoList = new ArrayList();
			Todo todo = new Todo("Hello from the server");
			todoList.add(todo);
			return todoList;			
		} else {
			return (List) session.getAttribute(TODOLIST_KEY);
		}
	}
}
