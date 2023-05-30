package com.ephraimglick.tenpercent.db;

import com.ephraimglick.tenpercent.models.ToDo;
import com.ephraimglick.tenpercent.models.ToDoUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ToDoJDBCRepository implements ToDoRepository {

    ToDoRowMapper toDoRowMapper = new ToDoRowMapper();

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public ToDo get(String toDoId) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM todo WHERE id = ?", new Object[]{toDoId}, toDoRowMapper
        );
    };

    @Override
    public List<ToDo> list() {
        return jdbcTemplate.query(
                "SELECT * FROM todo", toDoRowMapper
        );
    };

    @Override
    public int create(ToDo toDo) {
        return jdbcTemplate.update(
                "INSERT INTO todo VALUES (?, ?, ?, ?)", toDo.getId(), toDo.getUserId(), toDo.getTitle(), toDo.getCompleted());
    }

    @Override
    public int delete(String toDoId) {
        return jdbcTemplate.update(
                "DELETE FROM todo WHERE id = ? ", toDoId
        );
    };

    @Override
    public int update(String toDoId, ToDoUpdate toDoUpdate) {
        return jdbcTemplate.update(
                "UPDATE todo SET title = ?, completed = ? WHERE id = ?", toDoUpdate.getTitle(), toDoUpdate.getCompleted(), toDoId);
    };
}
