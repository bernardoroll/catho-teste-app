package br.com.bernardoroll.catho.ui.suggestion

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import br.com.bernardoroll.catho.R
import br.com.bernardoroll.catho.domain.model.SuggestionModel

class SuggestionItemViewModel(
    private val app: Application,
    model: SuggestionModel
) {

    private val _suggestionModel = MutableLiveData<SuggestionModel>()

    init {
        this._suggestionModel.value = model
    }

    private val _sendResumeButtonLabel = MutableLiveData<String>(
        app.getString(R.string.catho_send_resume)
    )
    val sendResumeButtonLabel: LiveData<String> get() = _sendResumeButtonLabel

    val positionsAndLocations: LiveData<String> = Transformations.map(_suggestionModel) {
        val label = StringBuilder()
        it.totalPositions?.let { jobPositions ->
            createJobsPositionLabel(label, jobPositions)
        }
        it.locations?.let { listOfCities ->
            createCitiesLabel(label, listOfCities)
        }
        label.toString()
    }

    val jobAdTitle: LiveData<String> = Transformations.map(_suggestionModel) {
        it.jobAdTile ?: app.getString(R.string.catho_job_name_not_informed)
    }

    val company: LiveData<String> = Transformations.map(_suggestionModel) {
        it.company ?: app.getString(R.string.catho_company_not_informed)
    }

    val rangeOrSalary: LiveData<String> = Transformations.map(_suggestionModel) {
        it.salary.real?.takeIf { real -> real.isNotEmpty() }?.run {
            it.salary.real
        } ?: run {
            it.salary.range
        } ?: run {
            app.getString(R.string.catho_range_not_informed)
        }
    }

    val date: LiveData<String> = Transformations.map(_suggestionModel) {
        it.date ?: ""
    }

    fun sendResumeClick() {
        _sendResumeButtonLabel.value = app.getString(R.string.catho_resume_sent)
    }

    private fun createJobsPositionLabel(
        label: StringBuilder,
        jobPositions: Int
    ): StringBuilder = label.append(
        app.resources.getQuantityString(
            R.plurals.catho_number_of_positions,
            jobPositions,
            jobPositions
        )
    )

    private fun createCitiesLabel(
        label: StringBuilder,
        listOfCities: List<String>
    ) {
        label.append(
            app.resources.getQuantityString(
                R.plurals.catho_number_of_cities,
                listOfCities.size,
                listOfCities.first()
            )
        )
        if (listOfCities.size > 1) {
            addPlusOtherCitiesLabel(label, listOfCities)
        }
    }

    private fun addPlusOtherCitiesLabel(
        label: StringBuilder,
        listOfCities: List<String>
    ): StringBuilder =
        label.append(
            app.resources.getQuantityString(
                R.plurals.catho_number_of_plus_other_cities,
                listOfCities.size - 1,
                listOfCities.size - 1
            )
        )
}
