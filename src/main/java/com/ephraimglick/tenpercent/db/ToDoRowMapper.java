package com.ephraimglick.tenpercent.db;

import com.ephraimglick.tenpercent.models.ToDo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToDoRowMapper implements RowMapper<ToDo> {
    @Override
    public ToDo mapRow(ResultSet rs, int rowNum) throws SQLException {

        ToDo toDo = new ToDo();
        toDo.setId(rs.getString("id"));
        toDo.setUserId(rs.getString("user_id"));
        toDo.setTitle(rs.getString("title"));
        toDo.setCompleted(rs.getBoolean("completed"));

        return toDo;
    }
}