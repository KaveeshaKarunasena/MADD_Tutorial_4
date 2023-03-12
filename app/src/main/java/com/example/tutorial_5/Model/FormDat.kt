package com.example.tutorial_5.Model

import com.example.tutorial_5.Model.Validation.ValidationResult

class FormDat(
    private var studentID:String,
    private var year:String,
    private var semester:String,
    private var agreement:Boolean
    ){

    fun validationStudentID(): ValidationResult{
        return if(studentID.isEmpty())
        {
            ValidationResult.Empty("Student ID is Empty")

        }
        else if(studentID.length != 10)
        {
            ValidationResult.Invalid("ID should have 10 digits")
        }
        else if (!studentID.startsWith("IT",true))
        {
            ValidationResult.Invalid("ID should start with IT")
        }
        else{
            ValidationResult.Valid
        }
    }
    fun validationYear(): ValidationResult{
        return if(year.isEmpty()) {
            ValidationResult.Empty(" Year is Empty")

        }
        else{
            ValidationResult.Valid
        }
    }
    fun validationSemester(): ValidationResult{
        return if(semester.isEmpty()) {
            ValidationResult.Empty(" Semester is Empty")

        }
        else{
            ValidationResult.Valid
        }
    }
    fun validationAgreement(): ValidationResult{
        return if(!agreement) {
            ValidationResult.Empty(" You Must agree to the term & conditions")

        }
        else{
            ValidationResult.Valid
        }
    }
}
