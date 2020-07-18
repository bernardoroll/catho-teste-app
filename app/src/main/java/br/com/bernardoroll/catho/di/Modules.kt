package br.com.bernardoroll.catho.di

import br.com.bernardoroll.catho.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object Modules {

    val cathoModule: Module = module {
        viewModel { HomeViewModel() }
    }
}
