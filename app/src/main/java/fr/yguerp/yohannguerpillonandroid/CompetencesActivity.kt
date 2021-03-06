package fr.yguerp.yohannguerpillonandroid

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import fr.yguerp.yohannguerpillonandroid.tools.replace

import kotlinx.android.synthetic.main.activity_competences.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_realisations.*
import kotlinx.android.synthetic.main.app_bar_competences.*
import kotlinx.android.synthetic.main.content_competences.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.email
import org.jetbrains.anko.toast

class CompetencesActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, AnkoLogger {

    var items = Array(10) { Competence("En cours d'acquiqisition", 0, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_competences)
        setSupportActionBar(toolbar_competences)

        seedItems()
        recyclerViewCompetences.layoutManager = LinearLayoutManager(this)
        recyclerViewCompetences.adapter = ListAdapt(items)

        fab_competences.setOnClickListener { view ->
            email("guerpillon.yohann@gmail.com")
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout_competences, toolbar_competences, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout_competences.addDrawerListener(toggle)
        toggle.syncState()

        nav_view_competences.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val fragment = fragmentManager.findFragmentById(R.id.magiccirclefragment)
        if(fragment != null){
            fragmentManager.beginTransaction().remove(fragment).commit()
            toast("fragment not null")
        }
        if (drawer_layout_competences.isDrawerOpen(GravityCompat.START)) {
            drawer_layout_competences.closeDrawer(GravityCompat.START)
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
                //replace(AccueilFragment())
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
                replace(AProposFragment())
            }
        }

        drawer_layout_competences.closeDrawer(GravityCompat.START)
        return true
    }

    private fun seedItems(){
        val nameArray = resources.getStringArray(R.array.name)
        val imgArray = arrayOf(R.drawable.ic_html_5_logo, R.drawable.ic_css_3, R.drawable.ic_js,
                R.drawable.ic_php, R.drawable.ic_java, R.drawable.ic_c_sharp, R.drawable.ic_android_logo, R.drawable.ic_swift)
        val levelArray = arrayOf(10, 10, 7, 8, 8, 8, 7, 6)
        for (k in 0..(nameArray.size - 1)) {
            items[k] = Competence(nameArray[k], imgArray[k], levelArray[k])
        }
    }

}
