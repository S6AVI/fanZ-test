package com.saleem.fanz_test.ui.first

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.saleem.data.model.Player
import com.saleem.fanz_test.databinding.ItemPlayerCardBinding

class PlayersAdapter : RecyclerView.Adapter<PlayersAdapter.DriverRideViewHolder>() {

    private var playerList: MutableList<Player> = mutableListOf()
    var onItemClickListener: ((Player) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverRideViewHolder {
        val binding =
            ItemPlayerCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DriverRideViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: DriverRideViewHolder, position: Int) {
        val player = playerList[position]
        holder.bind(player)
    }

    fun updateList(newPlayerList: MutableList<Player>) {
        playerList.clear()
        playerList.addAll(newPlayerList)
        notifyDataSetChanged()
    }

    inner class DriverRideViewHolder(private val binding: ItemPlayerCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(playerList[adapterPosition])
            }
        }

        fun bind(player: Player) {
            binding.apply {

                playerName.text = player.name
                playerNumber.text = player.number
                playerPosition.text = player.position
                playerStatus.text = player.cardType


                Glide.with(itemView.context)
                    .load(player.photoUrl)
                    .into(playerImage)


            }
        }
    }
}
