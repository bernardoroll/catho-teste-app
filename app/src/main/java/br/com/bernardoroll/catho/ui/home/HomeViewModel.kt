package br.com.bernardoroll.catho.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.bernardoroll.catho.R
import br.com.bernardoroll.catho.ui.BaseViewModel

class HomeViewModel(
    app: Application
) : BaseViewModel(app) {

    private val _title = MutableLiveData<String>(app.getString(R.string.app_name))
    val title: LiveData<String> get() = _title
}
