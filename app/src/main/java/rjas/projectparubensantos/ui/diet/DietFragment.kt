package rjas.projectparubensantos.ui.diet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import rjas.projectparubensantos.R
import rjas.projectparubensantos.databinding.FragmentDietBinding

private const val TAG = "DietFragment"

class DietFragment: Fragment() {
    private var _binding: FragmentDietBinding? = null

    private val binding get() = _binding!!

    //Diet calculation variables
    private lateinit var editTextNumberDecimalWeight: EditText
    private lateinit var editTextNumberDecimalHeight: EditText
    private lateinit var editTextNumberDecimalAge: EditText
    private lateinit var seekBarActivityLevel: SeekBar
    private lateinit var textViewResult2: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dietViewModel =
            ViewModelProvider(this).get(DietViewModel::class.java)

        _binding = FragmentDietBinding.inflate(inflater, container, false)
      val root: View = binding.root

/*        val textView: TextView = binding.textDiet
        dietViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Read the input and save to a variable
        editTextNumberDecimalWeight = view.findViewById(R.id.editTextNumberDecimalWeight)
        editTextNumberDecimalHeight = view.findViewById(R.id.editTextNumberDecimalHeight)
        editTextNumberDecimalAge = view.findViewById(R.id.editTextNumberDecimalAge)
        seekBarActivityLevel = view.findViewById(R.id.seekBarActivityLevel)
        textViewResult2 = view.findViewById(R.id.textViewResult2)

        //When the value of seekbar change, shows the activity level
        seekBarActivityLevel.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.i(TAG, "onProgressChanged $progress")
                //seekBarActivityLevel.text = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
