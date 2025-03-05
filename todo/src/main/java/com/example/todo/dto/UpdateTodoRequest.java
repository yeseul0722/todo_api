package com.example.todo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTodoRequest {
    private Integer id;
    private String title;
    private boolean done;

}
