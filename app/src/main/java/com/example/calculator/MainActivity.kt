package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.mariuszgromada.math.mxparser.*
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private var strNumber = StringBuilder()
    private lateinit var workingTV: TextView
    private lateinit var resultTV: TextView
    var a = 1
    private lateinit var numberButtons : Array<Button>
    private lateinit var actionButtons : Array<Button>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workingTV = findViewById(R.id.workingTV)
        resultTV = findViewById(R.id.resultTV)

        numberButtons = arrayOf(id0, id1, id2, id3, id4, id5, id6, id7, id8, id9)
        for (i in numberButtons) { i.setOnClickListener { numberButtonclick(i) } }

        actionButtons = arrayOf(idplus, idmultiply, idminus, iddivide, idbrackets)
        for (i in actionButtons) { i.setOnClickListener { actionButtonclick(i) } }
        idbrackets.setOnClickListener {
            if (a % 2 != 0) {
            strNumber.append(idbrackets.text[0])
            workingTV.text = strNumber
            }
            else  {strNumber.append(idbrackets.text[3])
            workingTV.text = strNumber}
            a++
        }
        AC.setOnClickListener {
            strNumber.delete(0,strNumber.length)
            resultTV.text = ""
            workingTV.text = strNumber
            a = 1
        }
        iddelete.setOnClickListener{
            try {
                strNumber.setLength(strNumber.length-1)
                workingTV.text = strNumber
            } catch (e: Exception){
                workingTV.text = ""
            }
        }
        idequal.setOnClickListener {
            if(strNumber.length >= 1) {
                val e = Expression(strNumber.toString())
                resultTV.text = e.calculate().toString()
                strNumber.delete(0, strNumber.length) }
            else {
                strNumber.append(resultTV.text)
                workingTV.text = strNumber
                resultTV.text = ""
            }
        }
        idpercents.setOnClickListener {
            if (strNumber.length>=1) {
                val e = Expression(strNumber.toString())
                var a = e.calculate()/100
                strNumber.delete(0, strNumber.length)
                strNumber.append(a.toString())
                resultTV.text = "$a"
            } else {
                strNumber.delete(0, strNumber.length)
                resultTV.text = "Format error"
            }
        }
    }

     private fun actionButtonclick(btn: Button) {
        strNumber.append(btn.text)
        workingTV.text = strNumber
    }
     private fun numberButtonclick(btn : Button) {
        strNumber.append(btn.text)
        workingTV.text = strNumber
    }

}

