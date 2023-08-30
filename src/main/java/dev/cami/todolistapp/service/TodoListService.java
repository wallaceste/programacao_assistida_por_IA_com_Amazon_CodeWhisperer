package dev.cami.todolistapp.service;

import dev.cami.todolistapp.model.TodoList;
import dev.cami.todolistapp.repository.TodoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public record TodoListService(TodoListRepository todoListRepository) {


    public TodoList addTodoList(TodoList todoList) {
       return todoListRepository.save(todoList);
    }

    public void deleteTodoList(UUID id) {
       todoListRepository.deleteById(id);
    }

    public TodoList updateTodoList(UUID id, TodoList todoList) {
        TodoList existingTodoList = todoListRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("TodoList with id " + id + " does not exist")
        );
        existingTodoList.setTitle(todoList.getTitle());
        return todoListRepository.save(existingTodoList);
    }

    public List<TodoList> getTodoLists() {
        return todoListRepository.findAll();
    }

    public TodoList getTodoList(UUID id) {
        return todoListRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("TodoList with id " + id + " does not exist")
        );
    }


}
