<%@ page language="java" errorPage="/WEB-INF/jsp/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/header.jsp"%>

<link rel='stylesheet' type='text/css' media='all' href='${ctx}/css/calendar-blue.css' title='calendar-blue' />
<script type='text/javascript' src='${ctx}/scripts/calendar.js'></script>
<script type='text/javascript' src='${ctx}/scripts/calendar-en.js'></script>
<script type='text/javascript' src='${ctx}/scripts/calendar-setup.js'></script>

<script type='text/javascript' src='${ctx}/secure/dwr/interface/todos.js'></script>

<script type='text/javascript'>
//Render the menu
function renderMenu() {
 todos.getCurrentTodoLists(replyCurrentTodoLists);
 Effect.Appear("todoListsMenuBody");
}

var replyCurrentTodoLists = function(data) {
 DWRUtil.removeAllRows("todoListsMenuBody");
 DWRUtil.addRows("todoListsMenuBody", data, [ selectTodoListLink ]);
}  

function selectTodoListLink(data) {
 return "<a href=\"javascript:renderTableListId('" 
  + data.listId + "')\">" + data.description + "</a>";
}

// Hide the "add" and "edit" layers.
function hideTodosLayers() {
 $("addNewTodoDiv").style.display='none';
 document.forms.addNewTodoForm.description.value = '';
 document.forms.addNewTodoForm.priority.value = '';
 document.forms.addNewTodoForm.dueDate.value = '';
 $("editTodoDiv").style.display='none';
 document.forms.editTodoForm.description.value = '';
 document.forms.editTodoForm.priority.value = '';
 document.forms.editTodoForm.dueDate.value = '';
}

// Show the "add a new todo" layer.
function showAddTodo() {
 hideTodosLayers();
 Effect.Appear('addNewTodoDiv');
 document.forms.addNewTodoForm.description.focus();
}

// Show the "edit a todo" layer.
function showEditTodo(todoId) {
 hideTodosLayers();
 document.forms.editTodoForm.todoId.value = todoId;
 Effect.Appear('editTodoDiv');
 todos.getTodoById(replyGetTodoById, todoId);
 document.forms.editTodoForm.description.focus();
}

var replyGetTodoById = function(todo) {
 document.forms.editTodoForm.description.value = todo.description;
 document.forms.editTodoForm.priority.value = todo.priority;
 document.forms.editTodoForm.dueDate.value = todo.dueDate;
}  

//Render the main todo table.
function renderTableListId(listId) {
 hideTodosLayers();
 $("todosTable").style.display='none';
 document.forms.todoForm.listId.value = listId;
 todos.forceRenderTodos(replyRenderTable, listId);
 Effect.Appear('todosTable');
}

var tableDate;
//Render the main todo table.
function renderTable() {
 var listId = document.forms.todoForm.listId.value;
 if (tableDate == null) {
  todos.forceRenderTodos(replyRenderTable, listId);
 } else {
  todos.renderTodos(replyRenderTable, listId, tableDate);
 }
}

var replyRenderTable = function(data) {
 var htmlData = DWRUtil.toDescriptiveString(data, 1);
 if (htmlData.length > 0) {
  DWRUtil.setValue('todosTable', htmlData);
 }
 tableDate = new Date();
}

//Sort the main todo table
function sortTable(sorter) {
 hideTodosLayers();
 $("todosTable").style.display='none';
 var listId = document.forms.todoForm.listId.value;
 todos.sortAndRenderTodos(replyRenderTable, listId, sorter);
 Effect.Appear('todosTable');
}

//Add a todo
function addTodo() {
 var listId = document.forms.todoForm.listId.value;
 var description = document.forms.addNewTodoForm.description.value;
 var priority = document.forms.addNewTodoForm.priority.value;
 var dueDate = document.forms.addNewTodoForm.dueDate.value;
 if (validateForm(priority, dueDate) != "ok") {
   return;
 }
 Effect.Fade('addNewTodoDiv');
 DWREngine.beginBatch();
 todos.addTodo(replyRenderTable, listId, description, priority, dueDate);
 todos.getCurrentTodoLists(replyCurrentTodoLists);
 DWREngine.endBatch();
}

//Cancel the Add Todo action
function cancelAddTodo() {
 Effect.Fade('addNewTodoDiv');
}

//Reopen a todo
function reopenTodo(todoId) {
 DWREngine.beginBatch();
 todos.reopenTodo(replyRenderTable, todoId);
 todos.getCurrentTodoLists(replyCurrentTodoLists);
 DWREngine.endBatch();
}

//Complete a todo
function completeTodo(todoId) {
 DWREngine.beginBatch();
 todos.completeTodo(replyRenderTable, todoId);
 todos.getCurrentTodoLists(replyCurrentTodoLists);
 DWREngine.endBatch();
}

//Edit a todo
function editTodo() {
 var todoId = document.forms.editTodoForm.todoId.value;
 var description = document.forms.editTodoForm.description.value;
 var priority = document.forms.editTodoForm.priority.value;
 var dueDate = document.forms.editTodoForm.dueDate.value;
 if (validateForm(priority, dueDate) != "ok") {
   return;
 }
 Effect.Fade('editTodoDiv');
 todos.editTodo(replyRenderTable, todoId, description, priority, dueDate);
}

//Cancel the Add Todo action
function cancelEditTodo() {
 Effect.Fade('editTodoDiv');
}

// Delete a todo.
function deleteTodo(todoId) {
 var sure = confirm("<fmt:message key="todos.delete.confirm"/>");
 if (sure) {
  hideTodosLayers();
  DWREngine.beginBatch();
  todos.deleteTodo(replyRenderTable, todoId);
  todos.getCurrentTodoLists(replyCurrentTodoLists);
  DWREngine.endBatch();
 }
}

//Validate the priority and the date
function validateForm(priority, dueDate) {
 if (priority != "" && !priority.match(/^\d+$/)) {
   alert("<fmt:message key="todos.error.priority"/>");
   return "nok";
 }
 if (dueDate != "" && !dueDate.match(/^\d{1,2}\/\d{1,2}\/\d{4}$/)) {
   alert("<fmt:message key="todos.error.date"/>");
   return "nok";
 }
 return "ok";
}
</script>

<table>
 <tr>
  <td style="width:200px; vertical-align: top; border: 1px solid #C0C0C0">
   <div id="menuDiv">
    <table id="menuTable">
     <thead>
      <tr>
       <td>
        [ <a href="javascript:renderMenu()"><fmt:message key="common.refresh"/></a> ]
       </td>
      </tr>
     </thead>
     <tbody style="text-align: left; display: none;" id="todoListsMenuBody"></tbody>
    </table>
   </div>
  </td>
  <td style="width:10px"></td>
  <td style="width: auto; vertical-align: top; text-align: center; border: 1px solid #C0C0C0">
   
   <div id="addNewTodoDiv" class="box" style="display: none" align="center">
    <div class="box_head"><h2><fmt:message key="todos.actions.add"/></h2></div>
    <div class="box_body">
     <form name="addNewTodoForm">
      <table>
       <tr>
        <td>
         <fmt:message key="todos.description"/>
        </td>
        <td colspan="3">
         <input type="text" name="description" size="50" maxlength="255"/>
        </td>
       </tr>
       <tr>
        <td>
         <fmt:message key="todos.priority"/>
        </td>
        <td>
         <input type="text" name="priority" size="5" maxlength="4"/>
        </td>
        <td style="text-align: right">
         <fmt:message key="todos.due.date"/>
        </td>
        <td>
         <input type="text" name="dueDate" id="addDueDateId" size="10" maxlength="10"/>
         <img src="${ctx}/images/calendar.gif" width="16" height="15" id="add_trigger_calendar" style="cursor: pointer;" alt="Calendar"/>
         <span style="font-size: 80%"><fmt:message key="todos.date.format"/></span>
        </td>
       </tr>
       <tr>
        <td colspan="4" style="text-align: center;">
         [ <a href="javascript:addTodo();"><fmt:message key="form.submit"/></a> ]
         &nbsp;&nbsp;
         [ <a href="javascript:cancelAddTodo();"><fmt:message key="form.cancel"/></a> ]
        </td>
       </tr>
      </table>
      <br/>
     </form>
     <script type='text/javascript'>
      Calendar.setup({
       weekNumbers: false,
       inputField : "addDueDateId",
       ifFormat : "%m/%d/%Y",
       button : "add_trigger_calendar"
      });
     </script>
    </div>
   </div>
   
   <div id="editTodoDiv" class="box" style="display: none" align="center">
    <div class="box_head"><h2><fmt:message key="todos.edit.title"/></h2></div>
    <div class="box_body">
     <form name="editTodoForm">
      <input type="hidden" name="todoId"/>
      <table>
       <tr>
        <td>
         <fmt:message key="todos.description"/>
        </td>
        <td colspan="3">
         <input type="text" name="description" size="50" maxlength="255"/>
        </td>
       </tr>
       <tr>
        <td>
         <fmt:message key="todos.priority"/>
        </td>
        <td>
         <input type="text" name="priority" size="5" maxlength="4"/>
        </td>
        <td style="text-align: right">
         <fmt:message key="todos.due.date"/>
        </td>
        <td>
         <input type="text" name="dueDate" id="editDueDateId" size="10" maxlength="10"/>
         <img src="${ctx}/images/calendar.gif" width="16" height="15" id="edit_trigger_calendar" style="cursor: pointer;" />
         <span style="font-size: 80%"><fmt:message key="todos.date.format"/></span>
        </td>
       </tr>
       <tr>
        <td colspan="4" style="text-align: center;">
         [ <a href="javascript:editTodo();"><fmt:message key="form.submit"/></a> ]
         &nbsp;&nbsp;
         [ <a href="javascript:cancelEditTodo();"><fmt:message key="form.cancel"/></a> ]
        </td>
       </tr>
      </table>
      <br/>
     </form>
     <script type='text/javascript'>
      Calendar.setup({
       weekNumbers: false,
       inputField : "editDueDateId",
       ifFormat : "%m/%d/%Y",
       button : "edit_trigger_calendar"
      });
     </script>
    </div>
   </div>
   
   <div id="todosTable"></div>

  </td>
 </tr>
</table>

<form name="todoForm">
 <input type="hidden" name="listId" value="${defaultList}"/>
</form>

<script type="text/javascript">
 // The menu table should be refreshed automatically every 2.4 minutes.
 function reloadingMenu() {
  renderMenu();
  setTimeout('renderMenu();', 2.4 * 60 * 1000);
 }
 reloadingMenu();
 
 // The todos table should be refreshed automatically every 2 minutes.
 function reloadingTable() {
  renderTable();
  setTimeout('reloadingTable();', 2 * 60 * 1000);
 }
 reloadingTable();
</script>

<%@ include file="/WEB-INF/jspf/footer.jsp"%>
