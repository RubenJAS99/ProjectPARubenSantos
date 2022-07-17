package rjas.projectparubensantos.fragments.nutrition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import rjas.projectparubensantos.MainActivity
import rjas.projectparubensantos.R
import rjas.projectparubensantos.databinding.FragmentNutritionBinding

class NutritionFragment : Fragment() {

    private var _binding: FragmentNutritionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNutritionBinding.inflate(inflater, container, false)
        (activity as MainActivity).idMainMenu = R.menu.main
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}