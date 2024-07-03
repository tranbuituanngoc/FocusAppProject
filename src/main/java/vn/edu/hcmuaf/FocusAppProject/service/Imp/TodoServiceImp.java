package vn.edu.hcmuaf.FocusAppProject.service.Imp;

import vn.edu.hcmuaf.FocusAppProject.dto.TodoDTO;
import vn.edu.hcmuaf.FocusAppProject.models.Todo;

import java.util.List;

public interface TodoServiceImp {
    Todo createTodo(TodoDTO todoDTO) throws Exception;
    Todo updateTodo(boolean status,long id) throws Exception;
    List<TodoDTO> getListTodoByDateForUser(long userId, String date) throws Exception;
}
