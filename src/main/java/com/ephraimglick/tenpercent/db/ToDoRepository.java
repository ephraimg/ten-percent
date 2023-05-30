package com.ephraimglick.tenpercent.db;

import com.ephraimglick.tenpercent.models.ToDo;
import com.ephraimglick.tenpercent.models.ToDoUpdate;

import java.util.List;

public interface ToDoRepository {
    ToDo get(String toDoId);

    List<ToDo> list();

    int create(ToDo toDo);

    int delete(String toDoId);

    int update(String toDoId, ToDoUpdate toDoUpdate);
}
