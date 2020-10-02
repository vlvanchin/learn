package com.example.demo;

import com.microsoft.azure.spring.autoconfigure.aad.UserGroup;
import com.microsoft.azure.spring.autoconfigure.aad.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class TodolistController {
    private final List<TodoItem> todoList = new ArrayList<>();

    @RequestMapping("/greeting")
    @PreAuthorize("hasAnyRole('ROLE_Users')")
    public Map<String, Object> home() {
        final Map<String, Object> model = new HashMap<>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "greeting");
        return model;
    }

    /**
     * HTTP GET
     */
    @RequestMapping(value = "/api/todolist/{index}",
            method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAnyRole('ROLE_Users')")
    public ResponseEntity<?> getTodoItem(@PathVariable("index") int index) {
        if (index > todoList.size() - 1) {
            return new ResponseEntity<>(new TodoItem(-1, "index out of range", null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(todoList.get(index), HttpStatus.OK);
    }

    /**
     * HTTP GET ALL
     */
    @RequestMapping(value = "/api/todolist", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<TodoItem>> getAllTodoItems() {
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }


    /**
     * HTTP POST
     * @param item
     * @return
     */
    @PreAuthorize("hasRole('ROLE_Users')")
    @RequestMapping(value = "/api/todolist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addNewTodoItem(@RequestBody TodoItem item) {
        item.setID(todoList.size() + 1);
        todoList.add(todoList.size(), item);
        return new ResponseEntity<>("Entity created", HttpStatus.CREATED);
    }


    /**
     * HTTP PUT
     */
    @PreAuthorize("hasRole('ROLE_Users')")
    @RequestMapping(value = "/api/todolist", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateTodoItem(@RequestBody TodoItem item) {
        final List<TodoItem> find =
                todoList.stream().filter(i -> i.getID() == item.getID()).collect(Collectors.toList());
        if (!find.isEmpty()) {
            todoList.set(todoList.indexOf(find.get(0)), item);
            return new ResponseEntity<>("Entity is updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Entity not found", HttpStatus.OK);
    }

    /**
     * HTTP DELETE
     */
    @PreAuthorize("hasRole('ROLE_Users')")
    @RequestMapping(value = "/api/todolist/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteTodoItem(@PathVariable("id") int id,
                                                 PreAuthenticatedAuthenticationToken authToken) {
        final UserPrincipal current = (UserPrincipal) authToken.getPrincipal();

//        if (current.isMemberOf(
//                new UserGroup("e030ce49-dc6c-4f0e-b841-61b7f0289663", "Users"))) {
//        	//                 e030ce49-dc6c-4f0e-b841-61b7f0289663
//                //new UserGroup("fa7ad436-d32b-464a-928d-43e642711c6c", "group1"))) {
            final List<TodoItem> find = todoList.stream().filter(i -> i.getID() == id).collect(Collectors.toList());
            if (!find.isEmpty()) {
                todoList.remove(todoList.indexOf(find.get(0)));
                return new ResponseEntity<>("done", HttpStatus.OK);
            }
            return new ResponseEntity<>("Entity not found", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Access is denied", HttpStatus.OK);
//        }

    }

}
