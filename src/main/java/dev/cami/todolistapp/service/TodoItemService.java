package dev.cami.todolistapp.service;

import dev.cami.todolistapp.model.TodoItem;
import dev.cami.todolistapp.repository.TodoItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public record TodoItemService(
        TodoItemRepository todoItemRepository
) {
    public void deleteTodoItem(UUID id) {
        todoItemRepository.deleteById(id);
    }


    public TodoItem updateTodoItem(UUID id, TodoItem todoItem) {
        TodoItem existingTodoItem = todoItemRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("TodoItem with id " + id + " does not exist")
        );
        existingTodoItem.setDescription(todoItem.getDescription());
        return todoItemRepository.save(existingTodoItem);
    }


    public TodoItem getTodoItem(UUID id) {
        return todoItemRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("TodoItem with id " + id + " does not exist")
        );
    }

    public List<TodoItem> getTodoItems() {
        return todoItemRepository.findAll();
    }


    public TodoItem addTodoItem(TodoItem todoItem) {
        return todoItemRepository.save(todoItem);
    }

// Code snippet from TodoListService.java
//
}
