package vn.edu.hcmuaf.FocusAppProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.dto.TodoDTO;
import vn.edu.hcmuaf.FocusAppProject.models.Todo;
import vn.edu.hcmuaf.FocusAppProject.models.User;
import vn.edu.hcmuaf.FocusAppProject.repository.TodoRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.UserRepository;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.TodoServiceImp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
public class TodoService implements TodoServiceImp {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoRepository todoRepository;
    @Override
    public Todo createTodo(TodoDTO todoDTO) throws Exception {
        User user = userRepository.findById(todoDTO.getUserId()).orElseThrow(()-> new Exception("User not found"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate deadline = LocalDate.parse(todoDTO.getDeadline(), formatter);
        Todo todo =  Todo.builder()
                .title(todoDTO.getTitle())
                .content(todoDTO.getContent())
                .deadline(deadline)
                .user(user)
                .isComplete(false)
                .build();
        return  todoRepository.save(todo);
    }

    @Override
    public Todo updateTodo(boolean status, long id) throws Exception {
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new Exception("Todo not found"));
        todo.setComplete(status);
        return todoRepository.save(todo);
    }

    @Override
    public List<TodoDTO> getListTodoByDateForUser(long userId, String date) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(()-> new Exception("User not found"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        List<Todo> todos = todoRepository.findTodosByUserIdAndDate(user.getId(), localDate);
        if(todos != null){
            return todos.stream().map(todo -> TodoDTO.builder()
                    .id(todo.getId())
                    .title(todo.getTitle())
                    .content(todo.getContent())
                    .deadline(todo.getDeadline().format(formatter))
                    .isDone(todo.isComplete())
                    .build()).toList();
        }
        return null;
    }
}
