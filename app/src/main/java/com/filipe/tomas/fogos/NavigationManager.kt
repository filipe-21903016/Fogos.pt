package com.filipe.tomas.fogos

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object NavigationManager {
    private fun placeFragment(fm: FragmentManager, fragment: Fragment){
        val transition = fm.beginTransaction()
        transition.replace(R.id.frame, fragment)
        transition.addToBackStack(null)
        transition.commit()
    }

    fun goToFireListFragment(supportFragmentManager: FragmentManager) {
        placeFragment(supportFragmentManager, FireListFragment())
    }

    fun goToDashboardFragment(supportFragmentManager: FragmentManager){
        placeFragment(supportFragmentManager, DashboardFragment())
    }

}