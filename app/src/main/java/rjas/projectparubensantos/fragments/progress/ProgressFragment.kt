package rjas.projectparubensantos.fragments.progress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
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
        return binding.root    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMainMenu = R.menu.editing_menu
    }

    fun menuOptions(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                save()
                true
            }
            else -> false
        }
    }

    private fun save() {
        val currentWeight = binding.editTextWeightProgress.text.toString()
        if (currentWeight.isBlank()) {
            binding.editTextWeightProgress.error = getString(R.string.progress_weight_mandatory)
            binding.editTextWeightProgress.requestFocus()
            return
        }

        val period = binding.spinnerPeriodProgress.selectedItem
        if (period== Spinner.INVALID_ROW_ID) {
            binding.spinnerPeriodProgress.requestFocus()
            return
        }
        val date = binding.calendarViewProgress.date

        insertProgress(date.toString(),68.0,currentWeight.toDouble(), period.toString())
    }

    private fun insertProgress(date: String, lastWeight: Double, currentWeight: Double, period: String) {
        val progress = Progress(date, lastWeight, currentWeight, period)

        val insertProgressAddress = requireActivity().contentResolver.insert(ContentProvider.PROGRESS_ADDRESS, progress.toContentValues())


        if (insertProgressAddress == null) {
            Snackbar.make(binding.editTextWeightProgress, R.string.error_inserting_progress, Snackbar.LENGTH_INDEFINITE).show()
            return
        }

        Toast.makeText(requireContext(), R.string.success_insert_progress, Toast.LENGTH_LONG).show()
    }

}