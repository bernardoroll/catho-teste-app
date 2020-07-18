package br.com.bernardoroll.catho.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.bernardoroll.catho.databinding.FragmentHomeBinding
import br.com.bernardoroll.catho.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModel()

    override fun getDataBindingView(inflater: LayoutInflater, container: ViewGroup?): View =
        FragmentHomeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@HomeFragment.viewModel
        }.root
}
