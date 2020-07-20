package br.com.bernardoroll.catho.di

import br.com.bernardoroll.catho.domain.repository.CathoRepository
import br.com.bernardoroll.catho.domain.use_case.api_keys.GetApiKeysUseCase
import br.com.bernardoroll.catho.domain.use_case.auth.GetAuthUseCase
import br.com.bernardoroll.catho.domain.use_case.suggestion.GetSuggestionUseCase
import br.com.bernardoroll.catho.networking.service.CathoService
import br.com.bernardoroll.catho.service.CathoRepositoryImpl
import br.com.bernardoroll.catho.ui.home.HomeViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Modules {

    val cathoModules by lazy {
        listOf(
            cathoModule,
            cathoUseCaseModule,
            cathoServiceModule
        )
    }

    private val cathoModule: Module = module {
        viewModel { HomeViewModel(get(), get(), get(), get()) }
    }

    private val cathoUseCaseModule: Module = module {
        single { GetApiKeysUseCase(get()) }
        single { GetAuthUseCase(get()) }
        single { GetSuggestionUseCase(get()) }
    }

    private val cathoServiceModule: Module = module {
        single<CathoService> { getServiceApi() }
//        single<CathoNetworking> { CathoNetworkingImpl(get()) }
        single<CathoRepository> { CathoRepositoryImpl(get()) }
    }
}

private const val BASE_URL = "http://10.0.2.2:4040"

private fun getServiceApi(): CathoService =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(provideOkHttp())
        .build()
        .create(CathoService::class.java)

private fun provideOkHttp() =
    OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()

private class CathoInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        return chain.proceed(req)
    }
}
