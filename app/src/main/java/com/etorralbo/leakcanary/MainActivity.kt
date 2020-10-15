package com.etorralbo.leakcanary

import android.content.Context
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    private var mySyncTask: MyAsyncTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        go_btn.setOnClickListener {
            if (mySyncTask != null) {
                finish()
            }

            mySyncTask = MyAsyncTask(this)
            mySyncTask?.execute()
        }
    }

    //private class MyAsyncTask(val context: WeakReference<Context>) : AsyncTask<Void, Void, Void>() {
    private class MyAsyncTask(val context: Context) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg p0: Void?): Void? {
            val bitmap =
                //BitmapFactory.decodeResource(context.get()?.resources, R.drawable.ic_launcher_background)
                BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_background)

            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            return null
        }
    }

    override fun onDestroy() {
        //mySyncTask?.cancel(true)
        super.onDestroy()
        Log.d(TAG, "onDestroy: activity is destroyed")
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}