package com.faizal.bikesmap

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        //do rest call to get contract list and save in Preferences
        RestApi.GetContractList(object : RestApi.ICallBack {
            override fun success() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

            override fun error() {
                //show a toast to the suer
            }
        }, baseContext)

        /*      new Handler().postDelayed(new Runnable() {
                *//*
                 * Showing splash screen with a timer. This will be useful when you
                 * want to show case your app logo / company
                 *//*
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }
            }, SPLASH_TIME_OUT);
        */

    }

    companion object {

        private val SPLASH_TIME_OUT: Long = 1000
    }
}
