package rjas.projectparubensantos.fragments.progress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import rjas.projectparubensantos.MainActivity
import rjas.projectparubensantos.R
import rjas.projectparubensantos.databinding.FragmentProgressBinding

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
        val progressViewModel =
            ViewModelProvider(this).get(ProgressViewModel::class.java)

        _binding = FragmentProgressBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textProgress
        progressViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        (activity as MainActivity).idMainMenu = R.menu.main
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}