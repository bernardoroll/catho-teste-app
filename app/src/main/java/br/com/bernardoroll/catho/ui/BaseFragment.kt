package br.com.bernardoroll.catho.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment<T: BaseViewModel> : Fragment() {

    abstract val viewModel: T

    abstract fun getDataBindingView(inflater: LayoutInflater, container: ViewGroup?): View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = getDataBindingView(inflater, container)
}
