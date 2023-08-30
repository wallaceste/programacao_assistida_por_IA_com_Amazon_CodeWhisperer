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
        todoItemToUpdate.setTodoListId(todoItem.getTodoListId());
        return todoItemRepository.save(todoItemToUpdate);
    }

    public void deleteTodoItem(UUID id) {
        todoItemRepository.deleteById(id);
    }

}
