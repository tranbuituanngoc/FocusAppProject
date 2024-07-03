package vn.edu.hcmuaf.FocusAppProject.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.FocusAppProject.dto.TodoDTO;
import vn.edu.hcmuaf.FocusAppProject.models.Todo;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.TodoServiceImp;

import java.util.List;

@RestController
@RequestMapping("/api/to-do")
public class TodoController {

    @Autowired
    private TodoServiceImp todoService;

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> createTodo(@RequestBody @Valid TodoDTO todoDTO) {
        try {
            Todo todo = todoService.createTodo(todoDTO);
            return ResponseEntity.ok(todo.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateTodo(@RequestParam boolean status, @PathVariable long id) {
        try {
            Todo todo = todoService.updateTodo(status, id);
            return ResponseEntity.ok(todo.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-list")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getListTodoByDateForUser(@RequestParam long userId, @RequestParam String date) {
        try {
            List<TodoDTO> todos = todoService.getListTodoByDateForUser(userId, date);
            if (todos == null || todos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(todos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
