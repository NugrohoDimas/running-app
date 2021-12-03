package its.nugrohodimas.runningapp.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import its.nugrohodimas.runningapp.R
import its.nugrohodimas.runningapp.helper.Constants.KEY_NAME
import its.nugrohodimas.runningapp.helper.Constants.KEY_WEIGHT
import its.nugrohodimas.runningapp.ui.viewmodel.StatisticViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val viewModel: StatisticViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFieldFromSharedPreferences()
        btnApplyChanges.setOnClickListener {
            val success = applyChangesSharedPreferences()
            if (success) {
                Snackbar.make(view, "Saved Changes", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(view, "Please fill all required field", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun applyChangesSharedPreferences(): Boolean {
        val name = etName.text.toString()
        val weight = etWeight.text.toString()
        if (name.isEmpty() || weight.isEmpty()) {
            return false
        }
        sharedPreferences.edit()
            .putString(KEY_NAME, name)
            .putFloat(KEY_WEIGHT, weight.toFloat())
            .apply()
        val toolbarText = "Lets Go $name"
        requireActivity().tvToolbarTitle.text = toolbarText
        return true
    }

    private fun loadFieldFromSharedPreferences() {
        val name = sharedPreferences.getString(KEY_NAME, "")
        val weight = sharedPreferences.getFloat(KEY_WEIGHT, 53f)
        etName.setText(name)
        etWeight.setText(weight.toString())
    }
}