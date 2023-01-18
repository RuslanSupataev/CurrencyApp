package kg.ruslansupataev.currencyapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kg.ruslansupataev.currencyapp.core.ISearchableFragment
import kg.ruslansupataev.currencyapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var searchMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navController.addOnDestinationChangedListener { _, dest, _ ->
            when (dest.id) {
                R.id.currency_converter_fragment -> {
                    hideBottomBar()
                    searchMenuItem?.isVisible = false
                }
                else -> {
                    showBottomBar()
                    searchMenuItem?.isVisible = true
                }
            }
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_all_currencies, R.id.nav_my_currencies
            )
        )
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        searchMenuItem = menu.findItem(R.id.action_search)
        val searchView = searchMenuItem?.actionView as SearchView?
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                search(query)

                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }

                searchMenuItem?.collapseActionView()
                return false
            }

            override fun onQueryTextChange(s: String?): Boolean {
                search(s.toString())
                return false
            }
        })

        return true
    }

    private fun search(query: String) {
        val fragment = getCurrentFragment()
        if (fragment is ISearchableFragment) {
            fragment.search(query)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun hideBottomBar() {
        binding.navView.isVisible = false
    }

    private fun showBottomBar() {
        binding.navView.isVisible = true
    }

    private fun getCurrentFragment(): Fragment? {
        val navHostFragment: NavHostFragment? =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as? NavHostFragment
        return navHostFragment?.childFragmentManager?.fragments?.get(0)
    }
}