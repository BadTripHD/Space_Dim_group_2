package fr.test200.spacedim

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import fr.test200.spacedim.Utils.Companion.createDialog


class EndActivity : AppCompatActivity() {
    private val tag = "End page"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_layout)

        val btnRetry = findViewById<Button>(R.id.end_btn_retry)
        btnRetry.setOnClickListener {
            val intent = Intent(this, WaitingRoomActivity::class.java)
            startActivity(intent)
        }

        Log.i(tag, "onCreate")
    }

    override fun onBackPressed() {
        createDialog(
                this,
                "Quitter",
                "Une derniere partie ?",
                true,
                fun () {
                    Log.i(tag, "close")
                }
        )
    }

    override fun onStart() {
        super.onStart()
        Log.i(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(tag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(tag, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(tag, "restart")
    }
}