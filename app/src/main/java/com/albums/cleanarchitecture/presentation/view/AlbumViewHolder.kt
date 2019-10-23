package com.albums.cleanarchitecture.presentation.view
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.albums.album.R


class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val audioTile: TextView? = itemView.findViewById(R.id.title)
    val audioImage: ImageView? = itemView.findViewById(R.id.image)
}
