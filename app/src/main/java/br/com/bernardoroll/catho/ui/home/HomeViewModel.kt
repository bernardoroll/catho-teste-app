package br.com.bernardoroll.catho.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.bernardoroll.catho.R
import br.com.bernardoroll.catho.ui.BaseViewModel

class HomeViewModel(
    app: Application
) : BaseViewModel(app) {

    private val _suggestionsSectionTitle = MutableLiveData<String>(
        app.getString(R.string.catho_suggestions_section_title)
    )
    val suggestionsSectionTitle: LiveData<String> get() = _suggestionsSectionTitle
}
