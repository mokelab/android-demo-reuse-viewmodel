package com.mokelab.demo.feature.mokera

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mokelab.demo.feature.mokera.databinding.ActivityViewMokeraListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ViewMokeraListActivity : AppCompatActivity() {
    private val viewModel: MokeraListViewModel by viewModels()
    private lateinit var binding: ActivityViewMokeraListBinding
    private val adapter = MokeraListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewMokeraListBinding.inflate(layoutInflater)
        binding.recycler.adapter = adapter
        
        setContentView(binding.root)


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is MokeraListViewModel.UiState.Initial -> {
                            binding.progress.visibility = View.VISIBLE
                            viewModel.load()
                        }

                        is MokeraListViewModel.UiState.Loading -> {
                            // show loading state
                        }

                        is MokeraListViewModel.UiState.Success -> {
                            // show success state
                            binding.progress.visibility = View.GONE
                            adapter.submitList(uiState.items)
                        }

                        is MokeraListViewModel.UiState.Error -> {
                            // show error state
                        }
                    }
                }
            }
        }
    }
}