package com.gstech.taskcontroller.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    private val fragmentListExibir: MutableList<Fragment> = ArrayList()
    private val nomesListaString: MutableList<String> = ArrayList()
    fun getTitle(position: Int): String {
        return nomesListaString[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentListExibir.add(fragment)
        nomesListaString.add(title)
    }

    //retornar os fragmentos da lista
    override fun createFragment(position: Int): Fragment {
        return fragmentListExibir[position]
    }

    //Conta quantos Fragmentos tem para não entrar no number point exception
    override fun getItemCount(): Int {
        return fragmentListExibir.size
    }
}