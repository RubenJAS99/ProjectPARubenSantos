package rjas.projectparubensantos.fragments.food

import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import rjas.projectparubensantos.ContentProvider
import rjas.projectparubensantos.MainActivity
import rjas.projectparubensantos.R
import rjas.projectparubensantos.databinding.FragmentDeleteFoodBinding
import rjas.projectparubensantos.food.Food

class DeleteFoodFragment : Fragment() {
    private var _binding: FragmentDeleteFoodBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var food: Food

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeleteFoodBinding.inflate(inflater, container, false)
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
        activity.idMainMenu = R.menu.deleting_menu

        food = DeleteFoodFragmentArgs.fromBundle(arguments!!).food

        binding.textViewFoodNameDelete.text = food.foodName
        binding.textViewFoodTypeDelete.text = food.foodTypeId.foodType
        binding.textViewFoodKcalDelete.text = food.kcal.toString()
        binding.textViewFoodProteinDelete.text = food.protein.toString()
        binding.textViewFoodFatDelete.text = food.fat.toString()
        binding.textViewCarbohydrateDelete.text = food.carbohydrate.toString()
    }
    private fun deleteFoodDialog() {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setTitle(R.string.delete_food_label)
            setMessage(R.string.delete_food_confirmation)
            setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialogInterface, i ->  })
            setPositiveButton(R.string.delete, DialogInterface.OnClickListener { dialogInterface, i -> deleteFood() })
            show()
        }
    }
    private fun deleteFood() {
        val foodAddress = Uri.withAppendedPath(ContentProvider.FOOD_ADDRESS, "${food.id}")
        val deletedFoods = requireActivity().contentResolver.delete(foodAddress, null, null)

        if (deletedFoods != 1) {
            Snackbar.make(
                binding.textViewFoodNameDelete,
                R.string.error_delete_food,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.success_delete_food, Toast.LENGTH_LONG).show()
        goToFood()
    }

    private fun goToFood() {
        val action = DeleteFoodFragmentDirections.actionNavDeleteFoodToNavFood()
        findNavController().navigate(action)

    }

    fun menuOptions(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                deleteFoodDialog()
                true
            }
            R.id.action_cancel -> {
                goToFood()
                true
            }
            else -> false
        }
    }

}