package fr.yguerp.yohannguerpillonandroid

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import fr.yguerp.yohannguerpillonandroid.tools.replace
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.email
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        //replace(AccueilFragment())

        fab.setOnClickListener { view ->
            email("guerpillon.yohann@gmail.com")
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val fragment = fragmentManager.findFragmentById(R.id.magiccirclefragment)
        if(fragment != null){
            fragmentManager.beginTransaction().remove(fragment).commit()
            toast("fragment not null")
        }
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_accueil -> {
                val intentAccueil = Intent(this, MainActivity::class.java)
                startActivity(intentAccueil)
            }
            R.id.nav_competences -> {
                val intentCompetences = Intent(this, CompetencesActivity::class.java)
                startActivity(intentCompetences)
            }
            R.id.nav_realisations -> {
                val intentRealisations = Intent(this, RealisationsActivity::class.java)
                startActivity(intentRealisations)
            }
            R.id.nav_magiccircle -> {
                replace(MagicCircleFragment())
            }
            R.id.nav_a_propos -> {
                //replace()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
