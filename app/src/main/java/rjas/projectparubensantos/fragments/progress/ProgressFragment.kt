package rjas.projectparubensantos.fragments.progress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SimpleCursorAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import rjas.projectparubensantos.ContentProvider
import rjas.projectparubensantos.MainActivity
import rjas.projectparubensantos.R
import rjas.projectparubensantos.databinding.FragmentProgressBinding
import rjas.projectparubensantos.progress.Progress

class ProgressFragment: Fragment() {

    private var _binding: FragmentProgressBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgressBinding.inflate(inflater, container, false)

        (activity as MainActivity).idMainMenu = R.menu.editing_menu
        return inflater.inflate(R.layout.fragment_progress, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun menuOptions(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                //insertProgress()
                true
            }
            else -> false
        }
    }
    private fun insertProgress(lastWeight: Double = 0.0, currentWeight: Double, period: String) {
        val progress = Progress(lastWeight, currentWeight, period)

        val insertProgressAddress = requireActivity().contentResolver.insert(ContentProvider.PROGRESS_ADDRESS, progress.toContentValues())


        if (insertProgressAddress == null) {
            Snackbar.make(binding.editTextWeightProgress, R.string.error_inserting_progress, Snackbar.LENGTH_INDEFINITE).show()
            return
        }

        Toast.makeText(requireContext(), R.string.success_insert_progress, Toast.LENGTH_LONG).show()
    }

}