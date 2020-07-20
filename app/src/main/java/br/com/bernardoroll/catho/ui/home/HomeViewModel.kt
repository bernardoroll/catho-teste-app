package br.com.bernardoroll.catho.ui.home

import android.app.Application
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.*
import br.com.bernardoroll.catho.R
import br.com.bernardoroll.catho.domain.model.ApiKeysModel
import br.com.bernardoroll.catho.domain.model.AuthModel
import br.com.bernardoroll.catho.domain.model.SuggestionModel
import br.com.bernardoroll.catho.domain.use_case.api_keys.GetApiKeysUseCase
import br.com.bernardoroll.catho.domain.use_case.auth.GetAuthUseCase
import br.com.bernardoroll.catho.domain.use_case.suggestion.GetSuggestionUseCase
import br.com.bernardoroll.catho.extension.getFileNameFrom
import br.com.bernardoroll.catho.extension.getFirstWord
import br.com.bernardoroll.catho.ui.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val app: Application,
    private val apiKeysUseCase: GetApiKeysUseCase,
    private val authUseCase: GetAuthUseCase,
    private val suggestionUseCase: GetSuggestionUseCase
) : BaseViewModel(app) {

    private val userIds = listOf(
        "ee09bd39-4ca2-47ac-9c5e-9c57ba5a26dc",
        "cc3431c3-d9c9-48e2-8a1f-c3c0260f0712"
    )

    private val _suggestionsSectionTitle = MutableLiveData<String>(
        app.getString(R.string.catho_suggestions_section_title)
    )
    val suggestionsSectionTitle: LiveData<String> get() = _suggestionsSectionTitle

    private val _recruiterTipsSectionTitle = MutableLiveData<String>(
        app.getString(R.string.catho_recruiter_tips_section_title)
    )
    val recruiterTipsSectionTitle: LiveData<String> get() = _recruiterTipsSectionTitle

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> get() = _userName

    private val _photoPath = MutableLiveData<Drawable>()
    val photoPath: LiveData<Drawable> get() = _photoPath

    private val _authModelLiveData = MutableLiveData<AuthModel>()
    val authModelLiveData: LiveData<AuthModel> get() = _authModelLiveData

    private val _apiKeysLiveData = MutableLiveData<ApiKeysModel>()
    val apiKeysLiveData: LiveData<ApiKeysModel> get() = _apiKeysLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> get() = _errorLiveData

    private lateinit var authKey: String
    private lateinit var suggestionKey: String
    private lateinit var token: String

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        viewModelScope.launch {
            apiKeysUseCase.run().either(
                ::handleApiKeysError,
                ::handleApiKeysSuccess
            )

        }
    }

    private fun handleApiKeysSuccess(model: ApiKeysModel) {
        _apiKeysLiveData.value = model
        authKey = model.auth ?: return
        suggestionKey = model.suggestion ?: return
        viewModelScope.launch {
            authUseCase.run(
                apiKey = authKey,
                userId = userIds.random()
            ).either(
                ::handleGetAuthError,
                ::handleGetAuthSuccess
            )
        }
    }

    private fun handleGetAuthError(throwable: Throwable) {
        _errorLiveData.value = throwable
    }

    private fun handleGetAuthSuccess(model: AuthModel) {
        token = model.token ?: return
        _authModelLiveData.value = model
        _userName.value =
            app.getString(
                R.string.catho_toolbar_title,
                model.name?.getFirstWord()
            )
        val drawable = app.assets.open(model.photo?.getFileNameFrom() ?: "")
        _photoPath.value = Drawable.createFromStream(drawable, null)
        viewModelScope.launch {
            model
            suggestionUseCase.run(
                apiKey = suggestionKey,
                token = token
            ).either(
                ::handleSuggestionError,
                ::handleSuggestionSuccess
            )
        }
    }

    private fun handleSuggestionError(error: Throwable) {

    }

    private fun handleSuggestionSuccess(models: List<SuggestionModel>?) {
        Log.d("sugestões = ", "sugestões = ${models.toString()}")
    }

    private fun handleApiKeysError(throwable: Throwable) {
        _errorLiveData.value = throwable
    }
}
