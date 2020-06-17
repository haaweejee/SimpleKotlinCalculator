package id.kotlin.kotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //call listener method
        listener()

    }

    private fun listener(){
        //NumberListener
        btn00.setOnClickListener{appendOnClick(true, "00")}
        btn0.setOnClickListener{appendOnClick(true, "0")}
        btn1.setOnClickListener{appendOnClick(true, "1")}
        btn2.setOnClickListener{appendOnClick(true, "2")}
        btn3.setOnClickListener{appendOnClick(true, "3")}
        btn4.setOnClickListener{appendOnClick(true, "4")}
        btn5.setOnClickListener{appendOnClick(true, "5")}
        btn6.setOnClickListener{appendOnClick(true, "6")}
        btn7.setOnClickListener{appendOnClick(true, "7")}
        btn8.setOnClickListener{appendOnClick(true, "8")}
        btn9.setOnClickListener{appendOnClick(true, "9")}
        btn_comma.setOnClickListener{appendOnClick(true, ".")}

        //OperatorListener
        btn_add.setOnClickListener{appendOnClick(false, "+")}
        btn_subtraction.setOnClickListener{appendOnClick(false, "-")}
        btn_division.setOnClickListener{appendOnClick(false, "/")}
        btn_multication.setOnClickListener{appendOnClick(false, "*")}
        btn_modulo.setOnClickListener{appendOnClick(false, "%")}

        btn_clear.setOnClickListener{
            clear()
        }
        btn_del.setOnClickListener{
            delete()
        }

        btn_equals.setOnClickListener{
            calculate()
        }

    }

    //new create method

    /*
    1.method appendOnClick method is to add button text value to textviewInput
     */
    private fun appendOnClick(clear: Boolean, string : String){

        if (clear){
            tv_output.text = ""
            tv_input.append(string)
        }else{
            tv_input.append(tv_output.text)
            tv_input.append(string)
            tv_output.text = ""
        }
    }

    /*
    2.Method Clear is to reset or clear the textViewInput and textViewOutput
     */
    private fun clear(){
        tv_input.text = ""
        tv_output.text = ""
    }

    /*
    3.Method Delete is to delete or backspace the input
     */
    private fun delete(){
        if (tv_input.text.toString() != ""){
            var string = tv_input.text.toString()
            if (string.isNotEmpty()){
                string = string.substring(0,string.length-1)
                tv_input.text = string
            }
        }
    }
    /*
    4.Method calculate is to calculate the input in textviewInput and the result show in textviewOutput.
      to create this simple calculator, i use libray Expression Builder from ObjectHunter.
     */
    private fun calculate(){
        try {

            val input = ExpressionBuilder(tv_input.text.toString()).build()
            val output = input.evaluate()
            val result = output.toLong()
            if (output == result.toDouble()){
                tv_output.text = result.toString()
            }else{
                tv_output.text = output.toString()
            }
        }catch (e:Exception){
            Toast.makeText(this@MainActivity,e.message,Toast.LENGTH_LONG).show()
        }
    }
}
