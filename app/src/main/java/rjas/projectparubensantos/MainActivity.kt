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
import rjas.projectparubensantos.fragments.food.DeleteFoodFragment
import rjas.projectparubensantos.fragments.food.FoodFragment
import rjas.projectparubensantos.fragments.food.InsertModifyFoodFraqment


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
    private var menu: Menu? = null

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
                R.id.nav_profile,
                R.id.nav_diet,
                R.id.nav_progress,
                R.id.nav_settings,
                R.id.nav_food
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(idMainMenu, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        val processedOption : Boolean

        if (fragment is FoodFragment) {
            processedOption = (fragment as FoodFragment).menuOptions(item)
        } else if (fragment is InsertModifyFoodFraqment) {
            processedOption = (fragment as InsertModifyFoodFraqment).menuOptions(item)
        } else if (fragment is DeleteFoodFragment) {
            processedOption = (fragment as DeleteFoodFragment).menuOptions(item)
        } else {
            processedOption = false
        }


        if (processedOption) return true

        return super.onOptionsItemSelected(item)
    }

        override fun onSupportNavigateUp(): Boolean {
            val navController = findNavController(R.id.nav_host_fragment_content_main)
            return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        }

    fun updateMenuOptions(displayModifyDelete: Boolean) {
        menu!!.findItem(R.id.action_modify).setVisible(displayModifyDelete)
        menu!!.findItem(R.id.action_delete).setVisible(displayModifyDelete)
    }

    fun updatePageTitle(id_string_title: Int) {
        binding.appBarMain.toolbar.setTitle(id_string_title)
    }
}