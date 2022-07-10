package rjas.projectparubensantos.fragments.food

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import rjas.projectparubensantos.databinding.FragmentInsertModifyFoodBinding
import rjas.projectparubensantos.ContentProvider
import rjas.projectparubensantos.MainActivity
import rjas.projectparubensantos.R
import rjas.projectparubensantos.food.Food
import rjas.projectparubensantos.food.FoodTypeTableBD
import rjas.projectparubensantos.food.Type

class InsertModifyFoodFraqment: Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    private var _binding: FragmentInsertModifyFoodBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var food: Food? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInsertModifyFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMainMenu = R.menu.editing_menu

        if (arguments != null) {
            food = InsertModifyFoodFraqmentArgs.fromBundle(arguments!!).food

            if (food != null) {
                binding.editTextFoodName.setText(food!!.foodName)
                binding.editTextNumberFoodKcal.setText(food!!.kcal.toString())
                binding.editTextNumberFoodProtein.setText(food!!.protein.toString())
                binding.editTextNumberFoodFat.setText(food!!.fat.toString())
                binding.editTextNumberFoodCarbohydrate.setText(food!!.carbohydrate.toString())
            }
        }

        LoaderManager.getInstance(this).initLoader(ID_LOADER_FOOD_TYPE, null, this)
    }

    fun menuOptions(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                save()
                true
            }
            R.id.action_cancel -> {
                goToFood()
                true
            }
            else -> false
        }
    }

    private fun goToFood() {
        findNavController().navigate(R.id.action_nav_InsertModifyFood_to_nav_food)
    }

    private fun save() {
        val foodName = binding.editTextFoodName.text.toString()
        if (foodName.isBlank()) {
            binding.editTextFoodName.error = getString(R.string.food_mandatory)
            binding.editTextFoodName.requestFocus()
            return
        }

        val typeId = binding.spinnerFoodType.selectedItemId
        if (typeId == Spinner.INVALID_ROW_ID) {
            binding.spinnerFoodType.requestFocus()
            return
        }
        val kcal = binding.editTextNumberFoodKcal.text.toString()
        if (kcal.equals("")) {
            binding.editTextNumberFoodKcal.error = getString(R.string.food_kcal_mandatory)
            binding.editTextNumberFoodKcal.requestFocus()
            return
        }
        val protein = binding.editTextNumberFoodProtein.text.toString()
        if (protein.equals("")) {
            binding.editTextNumberFoodProtein.error = getString(R.string.food_protein_mandatory)
            binding.editTextNumberFoodProtein.requestFocus()
            return
        }
        val fat = binding.editTextNumberFoodFat.text.toString()
        if (fat.equals("")){
            binding.editTextNumberFoodFat.error = getString(R.string.food_fat_mandatory)
            binding.editTextNumberFoodFat.requestFocus()
            return
        }
        val carbohydrate = binding.editTextNumberFoodCarbohydrate.text.toString()
        if (carbohydrate.equals("")) {
            binding.editTextNumberFoodCarbohydrate.error = getString(R.string.food_carbohydrate_mandatory)
            binding.editTextNumberFoodCarbohydrate.requestFocus()
            return
        }

        val savedFood =
            if (food == null) {
                insertFood(
                    foodName,
                    typeId,
                    kcal.toInt(),
                    protein.toDouble(),
                    fat.toDouble(),
                    carbohydrate.toDouble()
                )
            } else {
                modifyFood(
                    foodName,
                    typeId,
                    kcal.toInt(),
                    protein.toDouble(),
                    fat.toDouble(),
                    carbohydrate.toDouble()
                )
            }

        if (savedFood) {
            Toast.makeText(requireContext(), R.string.success_insert_food, Toast.LENGTH_LONG)
                .show()
            goToFood()
        } else {
            Snackbar.make(binding.editTextFoodName, R.string.error_inserting_food, Snackbar.LENGTH_INDEFINITE).show()
            return
        }
    }
    private fun modifyFood(foodName: String, typeId: Long, kcal: Int, protein: Double, fat: Double, carbohydrate: Double) : Boolean {
        val food = Food(
            foodName,
            Type(id= typeId),
            kcal,
            protein,
            fat,
            carbohydrate
        )

        val foodAddress = Uri.withAppendedPath(ContentProvider.FOOD_ADDRESS, "${this.food!!.id}")

        val modifiedFood = requireActivity().contentResolver.update(foodAddress, food.toContentValues(), null, null)

        return modifiedFood == 1
    }

    private fun insertFood(foodName: String, typeId: Long, kcal: Int, protein: Double, fat: Double, carbohydrate: Double): Boolean {
        val food = Food(
            foodName,
            Type(id= typeId),
            kcal,
            protein,
            fat,
            carbohydrate
        )

        val foodInsertedAddress = requireActivity().contentResolver.insert(ContentProvider.FOOD_ADDRESS, food.toContentValues())

        return foodInsertedAddress != null
    }

    companion object {
        const val ID_LOADER_FOOD_TYPE = 0
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProvider.FOODTYPE_ADDRESS,
            FoodTypeTableBD.ALL_COLUMNS,
            null,
            null,
            FoodTypeTableBD.FOOD_TYPE
        )

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        binding.spinnerFoodType.adapter = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            data,
            arrayOf(FoodTypeTableBD.FOOD_TYPE),
            intArrayOf(android.R.id.text1),
            0
        )

        updateSelectedFood()
    }

    private fun updateSelectedFood() {
        if (food == null) return
        val typeId = food!!.foodTypeId.id

        val lastType = binding.spinnerFoodType.count - 1

        for (i in 0..lastType) {
            if (binding.spinnerFoodType.getItemIdAtPosition(i) == typeId) {
                binding.spinnerFoodType.setSelection(i)
                return
            }
        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        if (_binding == null) return
        binding.spinnerFoodType.adapter = null
    }
}