package rjas.projectparubensantos

import android.os.Bundle

import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import rjas.projectparubensantos.databinding.ActivityMainBinding
import rjas.projectparubensantos.fragments.diet.DietFragment
import rjas.projectparubensantos.fragments.food.FoodFragment
import rjas.projectparubensantos.fragments.home.HomeFragment


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    var fragment: Fragment? = null

    var idMainMenu = R.menu.main
        get() = field
        set(value) {
            if (value != field) {
                field = value
                invalidateOptionsMenu()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_profile,
                R.id.nav_diet,
                R.id.nav_progress,
                R.id.nav_settings,
                R.id.nav_food,
                R.id.nav_InsertModifyFood
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(idMainMenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }

/*        val opcaoProcessada : Boolean

        if (fragment is HomeFragment) {
            opcaoProcessada = (fragment as HomeFragment).MenuOptions(item)
        } else if (fragment is DietFragment) {
            opcaoProcessada = (fragment as DietFragment).MenuOptions(item)
        } else if (fragment is FoodFragment) {
            opcaoProcessada = (fragment as FoodFragment).MenuOptions(item)
        } else if (fragment is FoodFragment) {
            opcaoProcessada = (fragment as FoodFragment).MenuOptions(item)
        } else if (fragment is FoodFragment) {
            opcaoProcessada = (fragment as FoodFragment).MenuOptions(item)
        } else if (fragment is FoodFragment) {
            opcaoProcessada = (fragment as FoodFragment).MenuOptions(item)
        } else if (fragment is FoodFragment) {
            opcaoProcessada = (fragment as FoodFragment).MenuOptions(item)
        } else {
            opcaoProcessada = false
        }

        return if (opcaoProcessada) {
            true
        } else {
            super.onOptionsItemSelected(item)
        }*/
    }

/*        val ProcessedOption : Boolean

        if (fragment is FoodFragment) {
            ProcessedOption = (fragment as FoodFragment).MenuOptions(item)
        } else {
            ProcessedOption = false
        }

        return if (ProcessedOption) {
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }*/

        override fun onSupportNavigateUp(): Boolean {
            val navController = findNavController(R.id.nav_host_fragment_content_main)
            return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        }
}