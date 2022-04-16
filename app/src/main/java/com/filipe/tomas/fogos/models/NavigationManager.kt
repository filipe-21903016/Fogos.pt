package com.filipe.tomas.fogos.models

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object NavigationManager {
    private fun placeFragment(fm: FragmentManager, fragment: Fragment){
        val transition = fm.beginTransaction()
        transition.replace(R.id.frame, fragment)
        transition.addToBackStack(null)
        transition.commit()
    }

}