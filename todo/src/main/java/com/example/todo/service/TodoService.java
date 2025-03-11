package com.example.todo.service;

import com.example.todo.domain.Todo;
import com.example.todo.dto.*;
import com.example.todo.repository.TodoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoJpaRepository todoRepository;

    @Transactional
    public CreateTodoResponse createTodo(CreateTodoRequest request) {
        // 도메인 객체를 만들고
        Todo todo = new Todo(request.getTitle(), request.isDone());
        // 도메인 객체를 DB에; 저장
        Integer id = todoRepository.save(todo).getId();

        CreateTodoResponse res = new CreateTodoResponse();
        res.setTodoId(id);

        return res;

        // 도메인 객체의 id를 사용해서 응답dto 생성
        // 응답 dto 반환
    }

    public List<ToDoDto> getAllTodo() {
        List<Todo> todos = todoRepository.findAll();

        List<ToDoDto> result = new ArrayList<>();
        for (Todo todo : todos) {

            ToDoDto toDoDto = new ToDoDto();
            toDoDto.setId(todo.getId());
            toDoDto.setTitle(todo.getTitle());
            toDoDto.setDone(todo.isDone());

            result.add(toDoDto);
        }
        return result;

    }

    public ToDoDto getTodo(Integer id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 Todo가 존재하지 않습니다. ID: " + id));

        ToDoDto response = new ToDoDto();
        response.setTitle(todo.getTitle());
        response.setDone(todo.isDone());
        response.setId(todo.getId());

        return response;

    }

    @Transactional
    public UpdateTodoResponse UpdateTodo(UpdateTodoRequest requestDto) {
        Todo todo = todoRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 Todo가 존재하지 않습니다." + requestDto.getId()));

        todo.setTitle(requestDto.getTitle());
        todo.setDone(requestDto.isDone());

        UpdateTodoResponse response = new UpdateTodoResponse();
        response.setTodoId(todo.getId());

        return response;

    }

    @Transactional
    public void DeleteTodo(Integer id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 Todo가 존재하지 않습니다. ID: " + id));

        todoRepository.delete(todo);


    }
}
