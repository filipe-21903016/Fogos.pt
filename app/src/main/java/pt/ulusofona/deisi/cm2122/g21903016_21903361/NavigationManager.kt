package pt.ulusofona.deisi.cm2122.g21903016_21903361

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.filipe.tomas.fogos.R

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