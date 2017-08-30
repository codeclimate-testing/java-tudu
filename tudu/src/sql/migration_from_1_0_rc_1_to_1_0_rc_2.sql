ALTER TABLE todo_list ADD last_update datetime NULL;

UPDATE todo_list SET last_update=now();
