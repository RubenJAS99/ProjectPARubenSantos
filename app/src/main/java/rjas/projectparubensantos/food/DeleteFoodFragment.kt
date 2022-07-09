package rjas.projectparubensantos.food

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import rjas.projectparubensantos.MainActivity
import rjas.projectparubensantos.R
import rjas.projectparubensantos.databinding.FragmentDeleteFoodBinding
import rjas.projectparubensantos.fragments.food.DeleteFoodArgs

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

        food = DeleteFoodArgs.fromBundle(arguments!!).food

        binding.textViewName.text = food.foodName
        //binding.textViewType.text = food.foodTypeId.foodType
    }
    fun MenuOptions(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_delete -> {

                true
            }
            R.id.action_cancel -> {

                true
            }
            else -> false
        }
}