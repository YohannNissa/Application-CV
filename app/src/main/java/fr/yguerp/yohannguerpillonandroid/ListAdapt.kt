package fr.yguerp.yohannguerpillonandroid

import android.support.annotation.LayoutRes
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_competence.view.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import org.jetbrains.anko.AnkoLogger


class ListAdapt(var items: Array<Competence>) : RecyclerView.Adapter<ListAdapt.ViewHolder>(), AnkoLogger {

    override fun getItemCount(): Int = items.size

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapt.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_competence))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMyObject(items[position])
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindMyObject(myObject: Competence) {
            with(myObject) {
                if("$name" != "Item 1" && "$name" != "null"){
                    itemView.objectName.text = name
                    itemView.imageViewObject.setImageResource(img)
                }
            }
        }
    }
}