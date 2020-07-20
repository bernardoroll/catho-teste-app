package br.com.bernardoroll.catho.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import br.com.bernardoroll.catho.databinding.FragmentHomeBinding
import br.com.bernardoroll.catho.ui.BaseFragment
import br.com.bernardoroll.catho.ui.suggestion.SuggestionItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModel()

    override fun getDataBindingView(inflater: LayoutInflater, container: ViewGroup?): View =
        FragmentHomeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@HomeFragment.viewModel
        }.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLiveData()
    }

    private fun setupLiveData() {
        viewModel.suggestionItems.observe(viewLifecycleOwner, Observer {
            val groupAdapter = GroupAdapter<ViewHolder>()
            val items = it.map { suggestion ->
                SuggestionItem(
                    jobAdTitle = suggestion.jobAdTile,
                    company = suggestion.company,
                    location = suggestion.locations?.joinToString(", "),
                    range = suggestion.salary.range ?: suggestion.salary.real,
                    date = suggestion.date
                )
            }

            groupAdapter.addAll(items)
            rvJobs.adapter = groupAdapter
        })
    }
}
