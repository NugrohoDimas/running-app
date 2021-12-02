package its.nugrohodimas.runningapp.ui.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import its.nugrohodimas.runningapp.R
import its.nugrohodimas.runningapp.ui.viewmodel.StatisticViewModel

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val viewModel: StatisticViewModel by viewModels()
}