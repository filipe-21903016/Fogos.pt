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

    fun placeRiskZoneFragment(fm: FragmentManager) {
        val transition = fm.beginTransaction()
        transition.replace(R.id.frame_risk, RiskZoneFragment())
        transition.addToBackStack(null)
        transition.commit()
    }

    fun goToFireListFragment(supportFragmentManager: FragmentManager) {
        placeFragment(supportFragmentManager, FireListFragment())
    }

    fun goToDashboardFragment(supportFragmentManager: FragmentManager){
        placeFragment(supportFragmentManager, DashboardFragment())
    }

    fun goToFireDetails(supportFragmentManager: FragmentManager, fireUi: FireUi) {
        placeFragment(supportFragmentManager, FireDetailsFragment.newInstance(fireUi))
    }

    fun goToFireMapFragment(supportFragmentManager: FragmentManager){
        placeFragment(supportFragmentManager, FireMapFragment())
    }

    fun goToFireRegistrationFragment(supportFragmentManager: FragmentManager) {
        placeFragment(supportFragmentManager, FireRegistrationFragment())
    }

}