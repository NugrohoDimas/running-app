package its.nugrohodimas.runningapp.ui.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import its.nugrohodimas.runningapp.db.Run
import its.nugrohodimas.runningapp.helper.SortType
import its.nugrohodimas.runningapp.repository.MainRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val mainRepository: MainRepository) : ViewModel() {

    val runs = MediatorLiveData<List<Run>>()
    var sortType = SortType.DATE

    private val runSortedByDate = mainRepository.getAllRunByDate()
    private val runSortedByDistance = mainRepository.getAllRunByDistance()
    private val runSortedByCaloriesBurned = mainRepository.getAllRunByCaloriesBurned()
    private val runSortedByTimeInMillis = mainRepository.getAllRunByTimeInMillis()
    private val runSortedByAverageSpeed = mainRepository.getAllRunByAverageSpeed()

    init {
        runs.addSource(runSortedByDate) { result ->
            if (sortType == SortType.DATE) {
                result?.let {
                    runs.value = it
                }
            }
        }

        runs.addSource(runSortedByAverageSpeed) { result ->
            if (sortType == SortType.AVERAGE_SPEED) {
                result?.let {
                    runs.value = it
                }
            }
        }

        runs.addSource(runSortedByCaloriesBurned) { result ->
            if (sortType == SortType.CALORIES_BURNED) {
                result?.let {
                    runs.value = it
                }
            }
        }

        runs.addSource(runSortedByDistance) { result ->
            if (sortType == SortType.DISTANCE) {
                result?.let {
                    runs.value = it
                }
            }
        }

        runs.addSource(runSortedByTimeInMillis) { result ->
            if (sortType == SortType.RUNNING_TIME) {
                result?.let {
                    runs.value = it
                }
            }
        }
    }

    fun sortRuns(sortType: SortType) = when (sortType) {
        SortType.DATE -> runSortedByDate.value?.let { runs.value = it }
        SortType.RUNNING_TIME -> runSortedByTimeInMillis.value?.let { runs.value = it }
        SortType.DISTANCE -> runSortedByDistance.value?.let { runs.value = it }
        SortType.CALORIES_BURNED -> runSortedByCaloriesBurned.value?.let { runs.value = it }
        SortType.AVERAGE_SPEED -> runSortedByAverageSpeed.value?.let { runs.value = it }
    }.also {
        this.sortType = sortType
    }

    fun insertRun(run: Run) = viewModelScope.launch {
        mainRepository.insertRun(run)
    }

}