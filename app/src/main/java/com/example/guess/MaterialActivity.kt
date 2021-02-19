package com.example.guess

import android.content.Context
import android.content.Intent
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
        Log.d(TAG, "onCreate: "+secretNumber.secret)

        val count = getSharedPreferences("guess",Context.MODE_PRIVATE)
            .getInt("REC_COUNTER", -1)
        val nick = getSharedPreferences("guess",Context.MODE_PRIVATE)
            .getString("REC_NICKNAME", null)
        Log.d(TAG,"data" + count + "/" + nick)
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
            //Here we judge when Bingo(if difff = 0),then we move to RecordActivity, change the listener to add an intent
            .setPositiveButton(getString(R.string.OK),{dialog, which ->
                if(diff==0){
                    val intent = Intent(this,RecordActivity::class.java)
                    intent.putExtra("COUNTER",secretNumber.count)
                    startActivity(intent)
                }
            })
            .show()
    }
}