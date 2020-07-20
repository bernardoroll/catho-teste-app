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

    val locations: LiveData<String> = Transformations.map(_suggestionModel) {
        it.locations?.joinToString(LOCATIONS_SEPARATOR)
            ?: app.getString(R.string.catho_locations_not_informed)
    }

    val jobAdTitle: LiveData<String> = Transformations.map(_suggestionModel) {
        it.jobAdTile ?: app.getString(R.string.catho_job_name_not_informed)
    }

    val company: LiveData<String> = Transformations.map(_suggestionModel) {
        it.company ?: app.getString(R.string.catho_company_not_informed)
    }

    val rangeOrSalary: LiveData<String> = Transformations.map(_suggestionModel) {
        it.salary.range ?: it.salary.real ?: app.getString(R.string.catho_range_not_informed)
    }

    val date: LiveData<String> = Transformations.map(_suggestionModel) {
        it.date ?: ""
    }

    fun sendResumeClick() {
        _sendResumeButtonLabel.value = app.getString(R.string.catho_resume_sent)
    }

    companion object {
        const val LOCATIONS_SEPARATOR = ", "
    }
}
