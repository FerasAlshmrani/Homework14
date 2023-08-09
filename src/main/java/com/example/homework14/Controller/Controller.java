package com.example.homework14.Controller;


import com.example.homework14.ApiResponse.ApiResponse;
import com.example.homework14.Employees.Employees;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("api/v1/employee")
@RestController
public class Controller {

    // ======== IMPORTANT NOTE ===========
    // ======== in the postman you must put with all integers with (" ") because they are Strings

    ArrayList<Employees> employees = new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<Employees> getEmployees(){
        return employees;
    }

    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody @Valid Employees employee, Errors error){
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        //check for age
        int age = Integer.parseInt(employee.getAge());

        if(age <= 0){
            String message = "please enter positive age number";
            return ResponseEntity.status(400).body(message);
        }else if (age < 25 && age > 0){
            String message = "this employee cannot be entered because he must be more than 25 years old ";
            return ResponseEntity.status(400).body(message);
        }else if (age > 65){
            String message = "this employee cannot be entered because he is too old ";
            return ResponseEntity.status(400).body(message);
        }
        //check employment year
        int employmentYear = Integer.parseInt(employee.getEmploymentYear());
        System.out.println(employmentYear);

        if(employmentYear > 2023 || employmentYear < 1980){
            String message = "please enter number between 1980 and 2023 for employmentYear";
            return ResponseEntity.status(400).body(message);
        }
        //check annualLeave
        int annualLeave = Integer.parseInt(employee.getAnnualLeave());
        if(annualLeave < 0 ){
            String message = "You must enter positive number or 0 for annualLeave";
            return ResponseEntity.status(400).body(message);
        }
        // all true so we add new employee
        employees.add(employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee added"));

    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateEmployee(@RequestBody @Valid Employees employee,@PathVariable int index, Errors error){

        if(employees.size() == 0 ) {
            String message = "array is empty";
            return ResponseEntity.status(400).body(message);
        }
            if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
            //check for age
        int age = Integer.parseInt(employee.getAge());

        if(age <= 0){
            String message = "please enter positive age number";
            return ResponseEntity.status(400).body(message);
        }else if (age < 25 && age > 0){
            String message = "this employee cannot be entered because he must be more than 25 years old ";
            return ResponseEntity.status(400).body(message);
        }else if (age > 65){
            String message = "this employee cannot be entered because he is too old ";
            return ResponseEntity.status(400).body(message);
        }
        //check employment year
        int employmentYear = Integer.parseInt(employee.getEmploymentYear());
        System.out.println(employmentYear);

        if(employmentYear > 2023 || employmentYear < 1980){
            String message = "please enter number between 1980 and 2023 for employmentYear";
            return ResponseEntity.status(400).body(message);
        }
        //check annualLeave
        int annualLeave = Integer.parseInt(employee.getAnnualLeave());
        if(annualLeave < 0 ){
            String message = "You must enter positive number or 0 for annualLeave";
            return ResponseEntity.status(400).body(message);
        }
        employees.set(index,employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee Updated"));


    }
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEmployee(@PathVariable int index) {

    if (employees.size() == 0){
        String message = "array is empty";
        return ResponseEntity.status(400).body(message);
    }else if (employees.size() <= index || index < 0) {
        String message = "index out of bounds";
        return ResponseEntity.status(400).body(message);
    } else {
        employees.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("user deleted"));
    }
}

//    @PutMapping("/annual-leave/{index}/{days}")
    @PutMapping("/annual-leave/{index}")
    public ResponseEntity annualLeaveEmployee(@PathVariable int index){
        if (employees.size() == 0) {
            String message = "array is empty";
            return ResponseEntity.status(400).body(message);
        }else if (employees.size() <= index || index < 0) {
            String message = "index out of bounds";
            return ResponseEntity.status(400).body(message);
        }else{

            //check annualLeave
            int annualLeave = Integer.parseInt(employees.get(index).getAnnualLeave());
            if(annualLeave < 0 ){
                String message = "You must enter positive number or 0 for annualLeave";
                return ResponseEntity.status(400).body(message);
            }
            // check annualLeave is not zero or less
            if(annualLeave <= 0){
                String message = "You can't get annualLeave because your annualLeave is zero";
                return ResponseEntity.status(400).body(message);
            }
            // check if onLeave is equal true
            else if (employees.get(index).getOnleave() == true) {
                String message = "he is already 'onleave' ";
                return ResponseEntity.status(400).body(message);
            }else{
                annualLeave -=1;
                String strAnualLeave = annualLeave+"";
                employees.get(index).setAnnualLeave(strAnualLeave);
                employees.get(index).setOnleave(true);

                return ResponseEntity.status(200).body(new ApiResponse("Done : employee now is onleave"));
            }
        }
        }
}
