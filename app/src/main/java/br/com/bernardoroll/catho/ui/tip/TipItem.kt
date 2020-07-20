package br.com.bernardoroll.catho.ui.tip

import br.com.bernardoroll.catho.R
import br.com.bernardoroll.catho.databinding.ItemRecruiterTipBinding
import com.xwray.groupie.databinding.BindableItem

class TipItem(
    val description: String? = "",
    val showButton: Boolean? = false,
    val buttonLabel: String? = "",
    val url: String? = ""
) : BindableItem<ItemRecruiterTipBinding>() {

    override fun getLayout(): Int = R.layout.item_recruiter_tip

    override fun bind(viewBinding: ItemRecruiterTipBinding, position: Int) {
        viewBinding.item = this
    }
}
