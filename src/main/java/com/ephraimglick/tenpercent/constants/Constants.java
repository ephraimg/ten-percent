package com.ephraimglick.tenpercent.constants;

public interface Constants {

    interface Uri {
        String API_V1 = "/api/v1";
        String SLASH_TODOS = "/todos";
        String SLASH_TODO_ID = "/{todoId}";
        String SLASH_TODOS_SLASH_TODO_ID = SLASH_TODOS + SLASH_TODO_ID;
    }

}