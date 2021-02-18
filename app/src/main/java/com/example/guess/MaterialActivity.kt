package com.example.guess

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.content_material.*
import kotlinx.android.synthetic.main.activity_material.*


class MaterialActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()
    val TAG = MaterialActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle("Replay game")
                .setMessage("Are you sure?")
                .setPositiveButton(getString(R.string.OK)) { dialog, which ->
                    secretNumber.reset()
                    counter.setText(secretNumber.count.toString())
                    number.setText("")
                }
                .setNeutralButton("Cancel",null)
                .show()
        }
        counter.setText(secretNumber.count.toString())
    }
    fun check(view : View) {
        //n:user guess num
        val n = number.text.toString().toInt()
        println("number: $n")
        Log.d(TAG,"number" + n)

        //Judge whether the number(n) is bigger / smaller / correct to the secret?
        val diff = secretNumber.validate(n)
        var msg = getString(R.string.bingo)
        if(diff < 0){
            msg = getString(R.string.bigger)
        }
        else if(diff > 0){
            msg = getString(R.string.smaller)
        }
//        Toast is not clearly seen
//        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
        counter.setText(secretNumber.count.toString()) //before msg,show the count
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(msg)
            .setPositiveButton(getString(R.string.OK),null)
            .show()
    }
}