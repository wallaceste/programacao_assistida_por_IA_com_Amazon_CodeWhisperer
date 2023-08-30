package dev.wall.todolistapp.service;

import dev.wall.todolistapp.model.TodoItem;
import dev.wall.todolistapp.repository.TodoItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public record TodoItemService(
        TodoItemRepository todoItemRepository
) {
    public TodoItem addTodoItem(TodoItem todoItem) {
        return todoItemRepository.save(todoItem);
    }

    public List<TodoItem> getTodoItems() {
        return todoItemRepository.findAll();
    }

    public TodoItem getTodoItemById(UUID id) {
        return todoItemRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Todo item with id " + id + " does not exist")
        );
    }

    public TodoItem updateTodoItem(UUID id, TodoItem todoItem) {
        TodoItem todoItemToUpdate = todoItemRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Todo item with id " + id + " does not exist")
        );
        todoItemToUpdate.setDescription(todoItem.getDescription());
        todoItemToUpdate.setCompleted(todoItem.isCompleted());
        return todoItemRepository.save(todoItemToUpdate);
    }

    public void deleteTodoItem(UUID id) {
        todoItemRepository.deleteById(id);
    }

    public TodoItem markTodoItemAsCompleted(UUID id) {
        TodoItem existingTodoItem = getTodoItemById(id);
        existingTodoItem.setCompleted(true);
        return todoItemRepository.save(existingTodoItem);
    }
}
