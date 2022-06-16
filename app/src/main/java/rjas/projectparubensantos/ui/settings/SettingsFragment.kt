package rjas.projectparubensantos.ui.settings

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import rjas.projectparubensantos.R
import rjas.projectparubensantos.databinding.FragmentSettingsBinding

class SettingsFragment: Fragment() {
    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val settingsViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSettings
        settingsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root

    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val switchThemeMode: Switch = view.findViewById(R.id.switchThemeMode)
        switchThemeMode.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                Snackbar.make(view, "Dark mode on", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }else{
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                Snackbar.make(view, "Dark mode off", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}