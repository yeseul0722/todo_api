package com.example.todo.controller;

import com.example.todo.dto.*;
import com.example.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    // 필드
    final TodoService todoService;

    // 메서드
    // todo 등록
    @PostMapping
    public CreateTodoResponse createTodo(@RequestBody CreateTodoRequest requestDto) {
        CreateTodoResponse response = todoService.createTodo(requestDto);

        return response;
    }

    // todo 전체 조회
    @GetMapping
    public ListResponse<ToDoDto> getAllTodo() {

        List<ToDoDto> toDoDtos = todoService.getAllTodo();
        //컨트롤러가 Service에게 모든 리스트를 가져오라고 요청한다.
//        todoService.getAllTodo();
        ListResponse<ToDoDto> toDoDtoListResponse = new ListResponse<>();
        toDoDtoListResponse.setItems(toDoDtos);

        return toDoDtoListResponse;
    }

    @GetMapping("/{id}")
    public ToDoDto getTodo(@PathVariable Integer id) {
        ToDoDto response = todoService.getTodo(id);

        return response;
    }

    @PutMapping
    public UpdateTodoResponse updateTodo(@RequestBody UpdateTodoRequest requestDto) {
        UpdateTodoResponse response = todoService.UpdateTodo(requestDto);

        return response;
    }

    // todo 삭제
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Integer id) {
        todoService.DeleteTodo(id);

    }

    // 필드 형식
    // 접근제어자 타입 필드명

    // 메서드 형식
    // 접근제어자 리턴타입 메서드명(메서드 파라미터) <-- 메서드 시그니처 { 메서드 바디 }
}
