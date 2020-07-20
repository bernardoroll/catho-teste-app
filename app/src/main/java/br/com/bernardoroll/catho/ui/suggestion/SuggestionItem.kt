package br.com.bernardoroll.catho.ui.suggestion

import br.com.bernardoroll.catho.R
import br.com.bernardoroll.catho.databinding.ItemSuggestionBinding
import com.xwray.groupie.databinding.BindableItem

class SuggestionItem(
    val jobAdTitle: String? = "",
    val company: String? = "",
    val location: String? = "",
    val range: String? = "",
    val date: String? =""
) : BindableItem<ItemSuggestionBinding>() {

    override fun getLayout(): Int = R.layout.item_suggestion

    override fun bind(viewBinding: ItemSuggestionBinding, position: Int) {
        viewBinding.item = this
    }
}
