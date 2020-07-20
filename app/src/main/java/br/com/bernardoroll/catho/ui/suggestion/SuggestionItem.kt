package br.com.bernardoroll.catho.ui.suggestion

import androidx.lifecycle.LifecycleOwner
import br.com.bernardoroll.catho.R
import br.com.bernardoroll.catho.databinding.ItemSuggestionBinding
import br.com.bernardoroll.catho.domain.model.SuggestionModel
import com.xwray.groupie.databinding.BindableItem
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class SuggestionItem(
    private val lifecycleOwner: LifecycleOwner,
    val model: SuggestionModel
) : BindableItem<ItemSuggestionBinding>(), KoinComponent {

    private val viewModel: SuggestionItemViewModel by inject { parametersOf(model) }

    override fun getLayout(): Int = R.layout.item_suggestion

    override fun bind(viewBinding: ItemSuggestionBinding, position: Int) {
        viewBinding.item = this
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = lifecycleOwner
    }
}
