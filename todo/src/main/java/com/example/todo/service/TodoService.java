package com.example.todo.service;

import com.example.todo.domain.Todo;
import com.example.todo.dto.*;
import com.example.todo.repository.TodoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public ToDoDto UpdateTodo(Integer id, UpdateTodoRequest requestDto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 Todo가 존재하지 않습니다. ID: " + id));

        if (requestDto.getTitle() != null) {
            todo.setTitle(requestDto.getTitle());
        }

        ToDoDto response = new ToDoDto();

        response.setId(todo.getId());
        response.setTitle(todo.getTitle());
        response.setDone(todo.isDone());

        return response;
    }

    @Transactional
    public UpdateDoneResponse UpdateDone(Integer id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 Todo가 존재하지 않습니다. ID: " + id));
        todo.setDone(!todo.isDone());

        UpdateDoneResponse response = new UpdateDoneResponse();

        response.setDone(todo.isDone());

        return response;

    }

    @Transactional
    public void DeleteTodo(Integer id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 Todo가 존재하지 않습니다. ID: " + id));

        todoRepository.delete(todo);

    }

    public List<ToDoDto> searchTodo(String keyword) {
        List<Todo> todos = todoRepository.findByTitleContaining(keyword);

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
}
