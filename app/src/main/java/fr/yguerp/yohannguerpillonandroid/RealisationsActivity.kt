package fr.yguerp.yohannguerpillonandroid

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import fr.yguerp.yohannguerpillonandroid.tools.replace
import kotlinx.android.synthetic.main.activity_realisations.*
import kotlinx.android.synthetic.main.app_bar_realisations.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.email
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

class RealisationsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realisations)
        setSupportActionBar(toolbar_realisations)

        fab_realisations.setOnClickListener { view ->
            email("guerpillon.yohann@gmail.com")
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout_realisations, toolbar_realisations, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout_realisations.addDrawerListener(toggle)
        toggle.syncState()

        nav_view_realisations.setNavigationItemSelectedListener(this)
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
                /*val intentRealisations = Intent(this, RealisationsActivity::class.java)
                startActivity(intentRealisations)*/
            }
            R.id.nav_magiccircle -> {
                replace(MagicCircleFragment())
            }
            R.id.nav_a_propos -> {
                //replace()
            }
        }
        drawer_layout_realisations.closeDrawer(GravityCompat.START)
        return true
    }

    fun onCardViewClick(v: View) {
        var url = ""
        when(v.id){
            R.id.aromaniceCardView -> {
                url = "https://aromanice.fr"
            }
            R.id.zultaniaCardView -> {
                url = "https://zultania-mc.fr/"
            }
        }
        val uris = Uri.parse(url)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        startActivity(intents)
    }
}
