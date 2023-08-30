package dev.wall.todolistapp.controller;

import dev.wall.todolistapp.model.TodoList;
import dev.wall.todolistapp.service.TodoListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todo-lists")
public record TodoListController(
        TodoListService todoListService
) {
    @GetMapping
    public List<TodoList> getTodoLists() {
        return todoListService.getTodoLists();
    }

    @GetMapping("/{id}")
    public TodoList getTodoList(@PathVariable UUID id) {
        return todoListService.getTodoList(id);
    }

    @PostMapping
    public TodoList addTodoList(@RequestBody TodoList todoList) {
        return todoListService.addTodoList(todoList);
    }

    @DeleteMapping("delete/{id}")
    public void deleteTodoList(@PathVariable UUID id) {
        todoListService.deleteTodoList(id);
    }

    @PutMapping("update/{id}")
    public TodoList updateTodoList(@PathVariable UUID id, @RequestBody TodoList todoList) {
        return todoListService.updateTodoList(id, todoList);
    }

}
