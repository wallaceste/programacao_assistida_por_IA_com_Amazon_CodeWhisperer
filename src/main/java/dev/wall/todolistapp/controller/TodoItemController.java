package dev.wall.todolistapp.controller;

import dev.wall.todolistapp.model.TodoItem;
import dev.wall.todolistapp.service.TodoItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todo-items")
public record TodoItemController(
        TodoItemService todoItemService
) {
    @GetMapping
    public List<TodoItem> getTodoItems() {
        return todoItemService.getTodoItems();
    }

    @GetMapping("/{id}")
    public TodoItem getTodoItemById(@PathVariable UUID id) {
        return todoItemService.getTodoItemById(id);
    }

    @PostMapping
    public TodoItem addTodoItem(@RequestBody TodoItem todoItem) {
        return todoItemService.addTodoItem(todoItem);
    }

    @PutMapping("update/{id}")
    public TodoItem updateTodoItem(@PathVariable UUID id, @RequestBody TodoItem todoItem) {
        return todoItemService.updateTodoItem(id, todoItem);
    }

    @DeleteMapping("delete/{id}")
    public void deleteTodoItem(@PathVariable UUID id) {
        todoItemService.deleteTodoItem(id);
    }


    @PatchMapping("/{id}/completed")
    public TodoItem markTodoItemAsCompleted(@PathVariable UUID id) {
        return todoItemService.markTodoItemAsCompleted(id);
    }
}
