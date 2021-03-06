package rjas.projectparubensantos.fragments.food

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import rjas.projectparubensantos.ContentProvider
import rjas.projectparubensantos.food.FoodTableBD
import androidx.recyclerview.widget.LinearLayoutManager
import rjas.projectparubensantos.food.AdapterFoods
import rjas.projectparubensantos.MainActivity
import rjas.projectparubensantos.R
import rjas.projectparubensantos.databinding.FragmentFoodBinding
import rjas.projectparubensantos.food.Food

class FoodFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    var foodSelected: Food? = null
        get() = field
        set(value) {
                field = value
                (requireActivity() as MainActivity).updateMenuOptions(field != null)
        }


    private var _binding: FragmentFoodBinding? = null
    private var adapterFoods : AdapterFoods? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterFoods = AdapterFoods(this)
        binding.recyclerViewFood.adapter = adapterFoods
        binding.recyclerViewFood.layoutManager = LinearLayoutManager(requireContext())

        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMainMenu = R.menu.food_menu

        LoaderManager.getInstance(this).initLoader(ID_LOADER_FOODS, null, this)

    }

    fun menuOptions(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_insert -> {
                val action = FoodFragmentDirections.actionNavFoodToNavInsertModifyFood()
                (activity as MainActivity).updatePageTitle(R.string.insert_food_label)
                findNavController().navigate(action)
                true
            }
            R.id.action_modify -> {
                val action = FoodFragmentDirections.actionNavFoodToNavInsertModifyFood(foodSelected!!)
                (activity as MainActivity).updatePageTitle(R.string.modify_food_label)
                findNavController().navigate(action)
                true
            }
            R.id.action_delete -> {
                val action = FoodFragmentDirections.actionNavFoodToNavDeleteFood(foodSelected!!)
                findNavController().navigate(action)
                true
            }
            else -> false
        }
    }

    /**
     * Instantiate and return a new Loader for the given ID.
     *
     *
     * This will always be called from the process's main thread.
     *
     * @param id The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProvider.FOOD_ADDRESS,
            FoodTableBD.ALL_COLUMNS,
            null,
            null,
            FoodTableBD.FOOD_NAME
        )

    /**
     * Called when a previously created loader has finished its load.  Note
     * that normally an application is *not* allowed to commit fragment
     * transactions while in this call, since it can happen after an
     * activity's state is saved.  See [ FragmentManager.openTransaction()][androidx.fragment.app.FragmentManager.beginTransaction] for further discussion on this.
     *
     *
     * This function is guaranteed to be called prior to the release of
     * the last data that was supplied for this Loader.  At this point
     * you should remove all use of the old data (since it will be released
     * soon), but should not do your own release of the data since its Loader
     * owns it and will take care of that.  The Loader will take care of
     * management of its data so you don't have to.  In particular:
     *
     *
     *  *
     *
     *The Loader will monitor for changes to the data, and report
     * them to you through new calls here.  You should not monitor the
     * data yourself.  For example, if the data is a [android.database.Cursor]
     * and you place it in a [android.widget.CursorAdapter], use
     * the [android.widget.CursorAdapter.CursorAdapter] constructor *without* passing
     * in either [android.widget.CursorAdapter.FLAG_AUTO_REQUERY]
     * or [android.widget.CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER]
     * (that is, use 0 for the flags argument).  This prevents the CursorAdapter
     * from doing its own observing of the Cursor, which is not needed since
     * when a change happens you will get a new Cursor throw another call
     * here.
     *  *  The Loader will release the data once it knows the application
     * is no longer using it.  For example, if the data is
     * a [android.database.Cursor] from a [android.content.CursorLoader],
     * you should not call close() on it yourself.  If the Cursor is being placed in a
     * [android.widget.CursorAdapter], you should use the
     * [android.widget.CursorAdapter.swapCursor]
     * method so that the old Cursor is not closed.
     *
     *
     *
     * This will always be called from the process's main thread.
     *
     * @param loader The Loader that has finished.
     * @param data The data generated by the Loader.
     */
    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterFoods!!.cursor = data
    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     *
     *
     * This will always be called from the process's main thread.
     *
     * @param loader The Loader that is being reset.
     */
    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapterFoods!!.cursor = null
    }

    companion object {
        const val ID_LOADER_FOODS = 0
    }
}