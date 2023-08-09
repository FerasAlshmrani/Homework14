package com.example.homework14.Employees;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employees {

    //i switch age , employmentYear and Annualleave all String to use @Pattern to make sure the user input does not enter String
    //Now im pretty sure that the user will enter number then
    //i converted throw methods to switch int and validate


    @NotEmpty(message = "ID cannot be null")
    @Size(min=2,message = "should be id more than 2")
    private String id;

    @NotEmpty(message = "Name cannot be null")
    @Size(min=4,message ="Should be name more than 4")
    private String name;

    @NotEmpty(message = "age Cannot be null")

    @Pattern(regexp = "[-]?[0-9]*",message = "you must enter number to set age not String")
    private String age;
    //check min age in the post/put methods

    @NotEmpty(message = "Position Cannot be null")
    @Pattern(regexp = "(?:coordinator|supervisor)",message = "should be coordinator or supervisor")
    private String position;

    @AssertFalse(message = "onleave it must be false ")
    private boolean onleave = false;

    @Pattern(regexp = "[-]?[0-9]*",message = "you must enter a number to set employmentYear not String")
    @NotEmpty(message = "EmploymentYear Cannot be null")
    private String employmentYear;
    // check valid year in the post/put methods

    @Pattern(regexp = "[-]?[0-9]*",message = "you must enter a number to set annualLeave not String")
    @NotEmpty(message = "AnnualLeave Cannot be null")
    private String annualLeave;

    public boolean getOnleave(){
        return onleave;
    }
}
