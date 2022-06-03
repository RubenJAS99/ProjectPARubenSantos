package rjas.projectparubensantos.ui.diet

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

        //When the value of seekbar and editTexts change, read and save on a variable

        editTextNumberDecimalWeight.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "afterTextChangedWeight $s")
                kcalResult()
            }
        })
        editTextNumberDecimalHeight.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "afterTextChangedHeight $s")
                kcalResult()
            }
        })
        editTextNumberDecimalAge.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "afterTextChangedAge $s")
                kcalResult()
            }
        })
        seekBarActivityLevel.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.i(TAG, "onProgressChangedActivityLevel $progress")
                kcalResult()
                //seekBarActivityLevel.text = progress
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun kcalResult() {

        //Handling the empty string exceptions
        if (editTextNumberDecimalWeight.text.isEmpty()
            or editTextNumberDecimalHeight.text.isEmpty()
            or editTextNumberDecimalAge.text.isEmpty()){
            return
            }

        // Getting the values
        val weight = editTextNumberDecimalWeight.text.toString().toDouble()
        val height = editTextNumberDecimalHeight.text.toString().toDouble()
        val age = editTextNumberDecimalAge.text.toString().toDouble()
        var activityLevel = seekBarActivityLevel.progress.toString().toDouble()

        when (activityLevel) {
            0.0 -> activityLevel = 1.2
            1.0 -> activityLevel = 1.375
            2.0 -> activityLevel = 1.55
            3.0 -> activityLevel = 1.725
            else -> activityLevel = 1.9
        }

        //Calculating the result
        val result = (((10 * weight) + (6.25 * height) - (5 * age)) + 5 )* activityLevel

        //Updating the result text view
        Log.i(TAG, "activityLevel $activityLevel")
        textViewResult2.text = result.toString()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
