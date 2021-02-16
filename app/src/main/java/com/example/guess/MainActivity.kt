package com.example.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun check(view : View) {
        //n:user guess num
        val n = number.text.toString().toInt()
        println("number: $n")
        Log.d("MainActivity","number" + n)

        //Judge whether the number(n) is bigger / smaller / correct to the secret?
        val diff = secretNumber.validate(n)
        var msg = "Bingo!"
        if(diff < 0){
            msg = "Bigger!"
        }
        else if(diff > 0){
            msg = "Smaller!"
        }
//        Toast is not clearly seen
//        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()

        AlertDialog.Builder(this)
            .setTitle("Message")
            .setMessage(msg)
            .setPositiveButton("OK",null)
            .show()
    }
}