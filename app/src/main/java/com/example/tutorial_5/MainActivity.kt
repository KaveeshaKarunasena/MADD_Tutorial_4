package com.example.tutorial_5

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.tutorial_5.Model.FormDat
import com.example.tutorial_5.Model.FormData
import com.example.tutorial_5.Model.Validation.ValidationResult
import java.time.Year

class MainActivity : AppCompatActivity() {

    lateinit var editStudentID:EditText
    lateinit var spnYear:Spinner
    lateinit var spnSemester:Spinner
    lateinit var cbAgree:CheckBox
    lateinit var btnSubmit:Button
    private var count=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editStudentID=findViewById(R.id.edtStudentID)
        spnYear=findViewById(R.id.spnYear)
        spnSemester = findViewById(R.id.spnSem)
        cbAgree=findViewById(R.id.agree)
        btnSubmit=findViewById(R.id.Submitbtn)
    }

    fun displayAlert(title:String, message:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, which ->
            // Do something when the "OK" button is clicked
        }
        val dialog = builder.create()
        dialog.show()
    }

    override fun onResume() {
        super.onResume()
        btnSubmit.setOnClickListener(View.OnClickListener{
            count=0
            val myForm = FormDat(editStudentID.text.toString(),
            spnYear.selectedItem.toString(),
            spnSemester.selectedItem.toString(),
            cbAgree.isChecked
            )

            val studentIDValidation =myForm.validationStudentID()
            val yearValidation=myForm.validationYear()
            val semesterValidation=myForm.validationSemester()
            val agreementValidation=myForm.validationAgreement()

            when(studentIDValidation)
            {
                is ValidationResult.Empty->
                    editStudentID.error= studentIDValidation.errorMessage
                is ValidationResult.Invalid->
                    editStudentID.error= studentIDValidation.errorMessage
                is ValidationResult.Valid->
                    count++
            }

            when(yearValidation)
            {
                is ValidationResult.Empty-> {
                    val tv:TextView = spnYear.selectedView as TextView
                    tv.error
                    tv.text=yearValidation.errorMessage
                }
                is ValidationResult.Invalid-> {
                    val tv:TextView = spnYear.selectedView as TextView
                    tv.error
                    tv.text=yearValidation.errorMessage
                }
                is ValidationResult.Valid->
                    count++
            }

            when(semesterValidation)
            {
                is ValidationResult.Empty-> {
                    val tv:TextView = spnYear.selectedView as TextView
                    tv.error
                    tv.text=semesterValidation.errorMessage
                }
                is ValidationResult.Invalid-> {
                    val tv:TextView = spnYear.selectedView as TextView
                    tv.error
                    tv.text=semesterValidation.errorMessage
                }
                is ValidationResult.Valid->
                    count++
            }
            when(agreementValidation)
            {
                is ValidationResult.Valid ->{
                    count ++
                }
                is ValidationResult.Invalid ->{
                    displayAlert("Error", agreementValidation.errorMessage)
                }
                is ValidationResult.Empty ->{
                }

            }
            if(count == 4) {
                displayAlert("Success", "You have Successfully registered")
                val dataObject = FormData(
                    editStudentID.text.toString(),
                    Integer.parseInt(spnYear.selectedItem.toString()),
                    spnSemester.selectedItem.toString(),
                    cbAgree.isChecked
                )
            }


        })
    }
}