package fr.yguerp.yohannguerpillonandroid

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_competences.*

class CompetencesFragment : Fragment() {

    var items = Array(10) { Competence("En cours d'acquiqisition", 0, 0) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_competences, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        seedItems()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = ListAdapt(items)
    }

    private fun seedItems(){
        val nameArray = resources.getStringArray(R.array.name)
        val imgArray = arrayOf(R.drawable.ic_html_5_logo, R.drawable.ic_css_3, R.drawable.ic_js,
                R.drawable.ic_php, R.drawable.ic_java, R.drawable.ic_c_sharp)
        val levelArray = arrayOf(10, 10, 7, 8, 8, 8)
        for (k in 0..(nameArray.size - 1)) {
                items[k] = Competence(nameArray[k], imgArray[k], levelArray[k])
        }
    }


}
