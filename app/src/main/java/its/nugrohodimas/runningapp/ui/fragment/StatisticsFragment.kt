package its.nugrohodimas.runningapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import its.nugrohodimas.runningapp.R
import its.nugrohodimas.runningapp.helper.TrackingUtility
import its.nugrohodimas.runningapp.ui.viewmodel.StatisticViewModel
import kotlinx.android.synthetic.main.fragment_statistics.*
import kotlin.math.round

@AndroidEntryPoint
class StatisticsFragment : Fragment(R.layout.fragment_statistics) {
    private val viewModel: StatisticViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
    }

    @SuppressLint("SetTextI18n")
    private fun subscribeToObservers() {
        viewModel.totalTimeRun.observe(viewLifecycleOwner, {
            it?.let {
                val totalTimeRun = TrackingUtility.getFormattedStopWatchTime(it)
                tvTotalTime.text = totalTimeRun
            }
        })

        viewModel.totalDistance.observe(viewLifecycleOwner, {
            it?.let {
                val totalDistance = round(it / 1000f * 10f) / 10f
                tvTotalDistance.text = "${totalDistance}km"
            }
        })

        viewModel.totalCaloriesBurned.observe(viewLifecycleOwner, {
            it?.let {
                tvTotalCalories.text = "${it}kcal"
            }
        })

        viewModel.totalAverageSpeed.observe(viewLifecycleOwner, {
            it?.let {
                val averageSpeed = round(it * 10f) / 10f
                tvAverageSpeed.text = "${averageSpeed}km/h"
            }
        })
    }
}