package com.saleem.fanz_test.ui.first

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.saleem.fanz_test.databinding.FragmentFirstBinding
import com.saleem.util.UiState
import com.saleem.util.exhaustive
import com.saleem.util.hide
import com.saleem.util.logD
import com.saleem.util.show
import com.saleem.fanz_test.R
import kotlinx.coroutines.launch


class FirstFragment : Fragment(R.layout.fragment_first) {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel: FirstViewModel by activityViewModels()

    private lateinit var layoutManager: CustomGridLayoutManager
    private val adapter by lazy {
        PlayersAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFirstBinding.bind(view)

        // remote config
        lifecycleScope.launch {
            viewModel.fetchAndActivate()
            val playersNum = viewModel.getInt(getString(R.string.players_num))
            setLayoutManager(playersNum)
            viewModel.getLineup()
        }

        // only runs once to upload data to firestore initially
        //viewModel.uploadPlayers()

        binding.lineup.recyclerView.adapter = adapter


        observer()

        adapter.onItemClickListener = { player ->

            logD( "Clicked on ${player.id}, ${player.name}")

            val action = FirstFragmentDirections.actionFirstFragmentToDetailsFragment(player.id)
            logD(player.id)
            findNavController().navigate(action)
        }

    }

    private fun observer() {
        viewModel.players.observe(viewLifecycleOwner) { state ->
            when (state) {

                is UiState.Loading -> {
                    binding.progressBar.show()

                    true
                }

                is UiState.Failure -> {
                    binding.progressBar.hide()

                    true
                }

                is UiState.Success -> {
                    binding.progressBar.hide()
                    adapter.updateList(state.data.toMutableList())
                    true
                }
            }.exhaustive
        }
    }

    private fun setLayoutManager(playersNum: Int) {
        val columnSizes = when (playersNum) {
            15 -> intArrayOf(1, 4, 3, 3, 4)
            else -> intArrayOf(1, 4, 3, 3)
        }
        layoutManager = CustomGridLayoutManager(requireContext(), columnSizes)
        binding.lineup.recyclerView.layoutManager = layoutManager

    }

}