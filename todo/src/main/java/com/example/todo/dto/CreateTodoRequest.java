package com.example.todo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTodoRequest {
    private String title;
    private boolean done;
}
