package its.nugrohodimas.runningapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import its.nugrohodimas.runningapp.repository.MainRepository
import javax.inject.Inject

@HiltViewModel
class StatisticViewModel @Inject constructor(val mainRepository: MainRepository) : ViewModel() {

}