package com.friendalert.shivangshah.friendalert

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.util.Log
import android.view.MenuItem
import android.widget.FrameLayout
import com.friendalert.shivangshah.friendalert.myplaces.MyPlacesFragment
import com.friendalert.shivangshah.friendalert.notifications.NotificationsFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HasSupportFragmentInjector, BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private var bottomNavigationView: BottomNavigationView? = null
    private var containerLayout : FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_home)

        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        containerLayout = findViewById<FrameLayout>(R.id.containerLayout);

        bottomNavigationView?.setOnNavigationItemSelectedListener(this)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_broadcast ->
                    Log.d("","")
            R.id.menu_myplace ->
                Log.d("","")
            R.id.menu_friends -> switchToMyPlacesFragment()

            R.id.menu_notifications -> switchToNotificationsFragment()
        }

        return true
    }

    fun switchToNotificationsFragment() {
        val manager = supportFragmentManager
        manager.beginTransaction().replace(R.id.containerLayout, NotificationsFragment().instantiate(Bundle())).commit()
    }

    fun switchToMyPlacesFragment() {
        val manager = supportFragmentManager
        manager.beginTransaction().replace(R.id.containerLayout, MyPlacesFragment().instantiate(Bundle())).commit()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }
}
