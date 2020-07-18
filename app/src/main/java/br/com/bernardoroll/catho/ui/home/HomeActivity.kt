package br.com.bernardoroll.catho.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.bernardoroll.catho.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
