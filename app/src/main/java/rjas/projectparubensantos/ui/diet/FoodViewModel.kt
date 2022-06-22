package rjas.projectparubensantos.ui.diet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FoodViewModel: ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        //value = "This is Diet Fragment"
    }
    val text: LiveData<String> = _text

}
