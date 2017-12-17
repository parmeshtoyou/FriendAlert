package com.friendalert.shivangshah.friendalert

import android.content.Intent
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.friendalert.shivangshah.friendalert.broadcast.BroadcastFragment
import com.friendalert.shivangshah.friendalert.friends.FriendsFragment
import com.friendalert.shivangshah.friendalert.myplaces.MyPlacesFragment
import com.friendalert.shivangshah.friendalert.notifications.NotificationsFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import android.view.WindowManager
import android.R.color.transparent
import android.graphics.drawable.Drawable





class HomeActivity : AppCompatActivity(), HasSupportFragmentInjector, BottomNavigationView.OnNavigationItemSelectedListener, DataLoadingListener {

    @Inject lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private val PLAY_SERVICES_REQUEST = 1000
    private val REQUEST_CHECK_SETTINGS = 2000
    private val READ_CONTACTS_REQUEST = 3000
    private val PLACE_AUTOCOMPLETE_REQUEST = 4000

    private var bottomNavigationView: BottomNavigationView? = null
    private var containerLayout : FrameLayout? = null
    private var toolbar : Toolbar? = null
    private var progressBar : ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = this.getWindow()
            val background = this.getResources().getDrawable(R.drawable.main_gradient)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(this.getResources().getColor(R.color.transparent))
            window.setBackgroundDrawable(background)
        }

        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_home)

        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        containerLayout = findViewById<FrameLayout>(R.id.containerLayout);

        toolbar = findViewById<Toolbar>(R.id.appToolbar)
        setSupportActionBar(toolbar)

        progressBar = findViewById<ProgressBar>(R.id.progress_bar)

        bottomNavigationView?.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_broadcast -> switchToBroadcastFragment()
            R.id.menu_myplace -> switchToMyPlacesFragment()
            R.id.menu_friends -> switchToFriendsFragment()
            R.id.menu_notifications -> switchToNotificationsFragment()
        }

        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CHECK_SETTINGS) {
            val fragment = supportFragmentManager.findFragmentByTag("BroadcastFragment")
            fragment.onActivityResult(requestCode, resultCode, data)
        }
      else if(requestCode == PLACE_AUTOCOMPLETE_REQUEST){
            val fragment = supportFragmentManager.findFragmentByTag("MyPlacesFragment")
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        if(requestCode == PLAY_SERVICES_REQUEST){
            val fragment : Fragment = supportFragmentManager.findFragmentByTag("BroadcastFragment")
            fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }else if(requestCode == READ_CONTACTS_REQUEST){
            val fragment : Fragment = supportFragmentManager.findFragmentByTag("FriendsFragment")
            fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    override fun dataLoadingStart() {
        showLoading("")
    }

    override fun dataLoadingStop() {
        hideLoading()
    }

    fun showLoading(title: String){
        progressBar!!.visibility = View.VISIBLE
    }

    fun hideLoading(){
        progressBar!!.visibility = View.GONE
    }

    fun switchToBroadcastFragment() {
        supportActionBar!!.title = "Broadcast"
        val manager = supportFragmentManager
        manager.beginTransaction().add(R.id.containerLayout, BroadcastFragment().instantiate(Bundle()), "BroadcastFragment").commit()
    }

    fun switchToNotificationsFragment() {
        supportActionBar!!.title = "Notifications"
        val manager = supportFragmentManager
        manager.beginTransaction().replace(R.id.containerLayout, NotificationsFragment().instantiate(Bundle()), "NotificationsFragment").commit()
    }

    fun switchToMyPlacesFragment() {
        supportActionBar!!.title = "My Places"
        val manager = supportFragmentManager
        manager.beginTransaction().replace(R.id.containerLayout, MyPlacesFragment().instantiate(Bundle()), "MyPlacesFragment").commit()
    }

    fun switchToFriendsFragment() {
        supportActionBar!!.title = "Friends"
        val manager = supportFragmentManager
        manager.beginTransaction().replace(R.id.containerLayout, FriendsFragment().instantiate(Bundle()), "FriendsFragment").commit()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }
}
