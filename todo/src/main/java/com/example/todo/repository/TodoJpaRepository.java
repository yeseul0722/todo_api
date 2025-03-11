package com.example.todo.repository;

import com.example.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TodoJpaRepository extends JpaRepository<Todo, Integer> {
    // JPA는 인터페이스에다가 JPA 리포지토리를 상속 받아야한다! 문법임 외우셈
    List<Todo> findByTitleContaining(String keyword);
}
