package com.saleem.fanz_test.ui.details

import android.os.Bundle
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.saleem.data.model.Player
import com.saleem.fanz_test.R
import com.saleem.fanz_test.databinding.FragmentDetailsBinding
import com.saleem.util.UiState
import com.saleem.util.exhaustive
import com.saleem.util.logD

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val playerId = arguments?.getString("playerId")
        viewModel.getPlayer(playerId)
        observer()

        binding = FragmentDetailsBinding.bind(view)

        binding.btnExit.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.nestedScrollView.setOnScrollChangeListener { _, _, _, _, _ ->
            val card = binding.playerCard.cardParent
            val rotationAngle =
                calculateRotationAngle(binding.nestedScrollView)
            card.rotation = rotationAngle
        }


    }

    private fun calculateRotationAngle(
        nestedScrollView: NestedScrollView
    ): Float {
        val scrollY = nestedScrollView.scrollY
        val maxScroll = nestedScrollView.getChildAt(0).height - nestedScrollView.height
        val rotationFactor = 0.5f
        return (scrollY.toFloat() / maxScroll) * 360f * rotationFactor
    }

    private fun observer() {
        viewModel.player.observe(viewLifecycleOwner) { state ->
            when (state) {

                is UiState.Loading -> {
                    true
                }

                is UiState.Failure -> {
                    logD(state.error.toString())
                    true
                }

                is UiState.Success -> {
                    logD("success: ${state.data.name}")
                    bindData(state.data)
                    true
                }
            }.exhaustive
        }
    }

    private fun bindData(player: Player) {
        binding.playerCard.apply {
            playerName.text = player.name
            playerPosition.text = player.position
            playerStatus.text = player.cardType
            playerNumber.text = player.number

            Glide.with(requireContext())
                .load(player.photoUrl)
                .into(playerImage)

            Glide.with(requireContext())
                .load(player.photoUrl)
                .into(binding.infoBar.playerImageView)
        }

        binding.playerNameTF.text = player.name

        binding.cardInfo.apply {
            price.text = player.transferPrice.toString()
            type.text = player.cardType
            position.text = player.position
            rating.text = player.rating.toString()
            team.text = player.teamId
            country.text = player.country
            rewards.text = player.rewards.toString()
        }


    }


}