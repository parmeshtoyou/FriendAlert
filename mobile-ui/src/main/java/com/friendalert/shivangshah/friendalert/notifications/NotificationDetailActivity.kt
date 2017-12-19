package com.friendalert.shivangshah.friendalert.notifications

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.WindowManager
import com.friendalert.shivangshah.friendalert.R

class NotificationDetailActivity : AppCompatActivity() {

    private var toolbar : Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = this.getWindow()
            val background = this.getResources().getDrawable(R.drawable.main_gradient)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(this.getResources().getColor(R.color.transparent))
            window.setBackgroundDrawable(background)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_detail)

        toolbar = findViewById<Toolbar>(R.id.appToolbar)
        setSupportActionBar(toolbar)

        getSupportActionBar()!!.setHomeButtonEnabled(true);
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item!!.itemId){

            android.R.id.home -> finish()

        }

        return super.onOptionsItemSelected(item)
    }
}
