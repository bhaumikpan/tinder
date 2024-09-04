package com.example.feature_ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.feature_domain.model.ChildrenResponseModel
import com.example.feature_ui.R
import com.example.feature_ui.databinding.ListItemLayoutBinding
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class ListAdapter(private val items: List<ChildrenResponseModel> , private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    // Define an interface for click listener
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ListViewHolder(private val binding: ListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ChildrenResponseModel) {
            item.data.run{

                binding.nameTxtView.text = item.data.title
                Glide.with(binding.postImage.context)
                    .load(item.data.thumbnail) // image url
                    .placeholder(R.drawable.ic_launcher_background) // any placeholder to load at start
                    .override(200, 200) // resizing
                    .centerCrop()
                    .into(binding.postImage);


                binding.time.text = item.data.created?.toLong()
                    ?.let { convertEpochToCurrentTime(it) }

            }

            // Set click listener on the root view
            binding.root.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        private  fun convertEpochToCurrentTime(epochTime: Long): String {

            // Convert epoch time to Instant
            val instant = Instant.ofEpochSecond(epochTime)

            // Convert Instant to ZonedDateTime in the system's default time zone
            val zonedDateTime = instant.atZone(ZoneId.systemDefault())

            // Format ZonedDateTime to a readable string
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            return zonedDateTime.format(formatter)
        }
    }
}
