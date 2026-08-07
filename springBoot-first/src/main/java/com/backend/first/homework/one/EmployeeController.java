package com.backend.first.homework.one;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
  Map<Integer, Employee> empDb = new HashMap<>();

  @PostMapping
  public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
    empDb.putIfAbsent(employee.getId(), employee);
    return new ResponseEntity<>(employee, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Employee> updateEmployee
      (@PathVariable int id,
      @RequestBody Employee employee
      ){
    if (!empDb.containsKey(id))
      return ResponseEntity.notFound().build();

    employee.setId(id);
    empDb.put(id, employee);
    return ResponseEntity.ok(employee);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteEmployee(@PathVariable int id){
    if (!empDb.containsKey(id))
      return ResponseEntity.notFound().build();

    empDb.remove(id);
    return ResponseEntity.noContent().build();
  }


  @GetMapping("/{id}")
  public ResponseEntity<Employee> getEmployee(@PathVariable int id){
    if (!empDb.containsKey(id))
      return ResponseEntity.notFound().build();
    return ResponseEntity.ok(empDb.get(id));
  }

}
