package com.ieti.taskplanner.taskback.restcontroller;

import com.ieti.taskplanner.taskback.model.Task;
import com.ieti.taskplanner.taskback.model.User;
import com.ieti.taskplanner.taskback.services.TaskService;
import com.ieti.taskplanner.taskback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Sergio
 */
@Service
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/taskplanner")
public class TaskPlannerRestController {

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @RequestMapping(path="/addtask", method = RequestMethod.PUT)
    public ResponseEntity<?> addTask(@RequestBody Task task, String userId){
        try {
            taskService.assignTaskToUser(task,userId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (NumberFormatException ex){
            Logger.getLogger(TaskPlannerRestController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("An error has ocurred",HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/gettasks", method = RequestMethod.GET)
    public ResponseEntity<?> getTasks(String userid){
        try {
            return new ResponseEntity<>(taskService.getTasksByUserId(userid), HttpStatus.ACCEPTED);
        } catch (NumberFormatException ex){
            Logger.getLogger(TaskPlannerRestController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("An error has ocurred",HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/updatetask", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTasks(@RequestBody Task task){
        taskService.updateTask(task);
        return getResponseEntity();
    }

    private ResponseEntity<?> getResponseEntity() {
        try {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (NumberFormatException ex){
            Logger.getLogger(TaskPlannerRestController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("An error has ocurred",HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path="/updateuser", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTasks(@RequestBody User user){
        userService.updateUser(user);
        return getResponseEntity();
    }
}
