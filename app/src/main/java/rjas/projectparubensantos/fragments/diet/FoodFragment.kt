package rjas.projectparubensantos.fragments.diet

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
import rjas.projectparubensantos.MainActivity
import rjas.projectparubensantos.R
import rjas.projectparubensantos.databinding.FragmentDietBinding

private const val TAG = "DietFragment"
private const val ActivityLevel = 2

class DietFragment: Fragment() {
    private var _binding: FragmentDietBinding? = null

    private val binding get() = _binding!!

    //Diet calculation variables
    private lateinit var editTextNumberDecimalWeight: EditText
    private lateinit var editTextNumberDecimalHeight: EditText
    private lateinit var editTextNumberDecimalAge: EditText
    private lateinit var seekBarActivityLevel: SeekBar
    private lateinit var textViewResult2: TextView
    private lateinit var textViewActivityDescription: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dietViewModel =
            ViewModelProvider(this).get(FoodViewModel::class.java)

        _binding = FragmentDietBinding.inflate(inflater, container, false)
      val root: View = binding.root

/*        val textView: TextView = binding.textDiet
        dietViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        (activity as MainActivity).idMainMenu = R.menu.main
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //textViewActivityDescription.text = "$ActivityLevel%"
        //Read the input and save to a variable
        editTextNumberDecimalWeight = view.findViewById(R.id.editTextNumberDecimalWeight)
        editTextNumberDecimalHeight = view.findViewById(R.id.editTextNumberDecimalHeight)
        editTextNumberDecimalAge = view.findViewById(R.id.editTextNumberDecimalAge)
        seekBarActivityLevel = view.findViewById(R.id.seekBarActivityLevel)
        textViewResult2 = view.findViewById(R.id.textViewResult2)
        textViewActivityDescription = view.findViewById(R.id.textViewActivityDescription)

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
                updateActivityDescription(progress)

            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun updateActivityDescription(ActivityDescription: Int) {
        //Description of the Activity input
        val activityLevel =  when (ActivityDescription) {
            0 -> "Sedentary"
            1 -> "1 to 3 days a week"
            2 -> "3 to 5 days a week"
            3 -> "6 to 7 days a week"
            else -> "2 times a day"
        }
        textViewActivityDescription.text = activityLevel
       /* //Color update
        val color = ArgbEvaluator().evaluate(
           ActivityDescription.toFloat() / seekBarActivityLevel.max,

            ContextCompat.getColor(this, R.color.lowExercise),
            ContextCompat.getColor(this, R.color.highExercise)

        )*/
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
        val height = editTextNumberDecimalHeight.text.toString().toDouble().toInt()
        val age = editTextNumberDecimalAge.text.toString().toDouble().toInt()
        var activityLevel = seekBarActivityLevel.progress.toString().toDouble()

        //Setting the real values of the activity level
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
        textViewResult2.text = "%.0f".format(result)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
