package com.omao.navigationdrawerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            R.string.open,
            R.string.close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // functionality for the nav drawer items
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener {
            it.isChecked = true
            when(it.itemId) {
                R.id.kick_boxing -> {
                    replaceFragment(KickBoxingFragment(), it.title.toString())
                }
                R.id.basket_ball -> {
                    replaceFragment(BasketBallFragment(), it.title.toString())
                }
                R.id.foot_ball -> {
                    replaceFragment(FootBallFragment(), it.title.toString())
                }
                R.id.login -> {
                    replaceFragment(LoginFragment(), it.title.toString())
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment, title: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout_1, fragment)
        fragmentTransaction.commit()

        drawerLayout.closeDrawers()
        setTitle(title)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}