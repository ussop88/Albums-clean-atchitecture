package com.albums.cleanarchitecture.presentation.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.albums.album.R
import com.albums.store.IModel
import kotlinx.android.synthetic.main.audio_item.view.*


class AlbumAdapter(var list: List<IModel>) : RecyclerView.Adapter<AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.audio_item, parent, false)
        return AlbumViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.let { hold ->
            hold.itemView.apply {
                title.text = list[position].title
            }
        }
    }

    override fun getItemCount(): Int = list.size

    fun notifyDataSetChanged(list: List<IModel>) {
        this.list = list
        notifyDataSetChanged()
    }

}
