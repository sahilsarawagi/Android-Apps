package eu.youtube.appcal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvinput : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvinput  = findViewById(R.id.tvInput)
    }
    var lastNumeric= false
    var lastDot= false

    fun onDigit(view: View) {
        Toast.makeText(this,"button clicked",Toast.LENGTH_SHORT).show()
        tvinput?.append((view as Button).text)
        lastNumeric=true
    }

    fun onClear(view: View) {
        tvinput?.text = ""
        lastDot=false

    }

    fun onDecimalPoint(view: View) {
        if(lastNumeric && !lastDot){
            tvinput?.append(".")
            lastDot= true
            lastNumeric= false
        }

    }

    fun onOperator(view: View) {
        tvinput?.text?.let{
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvinput?.append((view as Button).text)
                lastNumeric=false
                lastDot=false
            }
        }
    }
    fun isOperatorAdded(value: String):Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/") ||
            value.contains("*") ||
            value.contains("+") ||
            value.contains("-")
        }
    }

    fun onEqual(view: View) {
        if(lastNumeric){
            var tvValue = tvinput?.text.toString()
            var prefix=""
            try {
                if (tvValue.contains("-")){
                    if (tvValue.startsWith("-")){
                        prefix="-"
                        tvValue=tvValue.substring(1)
                    }
                    val splitValue = tvValue.split("-")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if (prefix.isNotEmpty()){
                        one=  prefix + one
                    }
                    var result= one.toDouble() - two.toDouble()
                    tvinput?.text= result.toString()
                } else if (tvValue.contains("+")){
                    if (tvValue.startsWith("-")){
                        prefix="-"
                        tvValue=tvValue.substring(1)
                    }
                    val splitValue = tvValue.split("+")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if (prefix.isNotEmpty()){
                        one=  prefix + one
                    }
                    var result= one.toDouble() + two.toDouble()
                    tvinput?.text= result.toString()
                } else if (tvValue.contains("*")){
                    if (tvValue.startsWith("-")){
                        prefix="-"
                        tvValue=tvValue.substring(1)
                    }
                    val splitValue = tvValue.split("*")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if (prefix.isNotEmpty()){
                        one=  prefix + one
                    }
                    var result= one.toDouble() * two.toDouble()
                    tvinput?.text= removeZeroAfterDot(result.toString())
                } else if (tvValue.contains("/")){
                    if (tvValue.startsWith("-")){
                        prefix="-"
                        tvValue=tvValue.substring(1)
                    }
                    val splitValue = tvValue.split("/")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if (prefix.isNotEmpty()){
                        one=  prefix + one
                    }
                    var result= one.toDouble() / two.toDouble()
                    tvinput?.text= result.toString()
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }

    }
    private fun removeZeroAfterDot(result: String): String{
        var value= result
        if(result.contains(".0"))
            value =result.substring(0, result.length - 2)
            return value
        }

}