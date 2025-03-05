package com.example.todo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// 리스트 형식 공통 응답
@Getter
@Setter
public class ListResponse<T> {
    private List<T> items = new ArrayList<>();
}
