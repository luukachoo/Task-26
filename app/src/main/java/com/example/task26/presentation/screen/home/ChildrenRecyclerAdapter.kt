package com.example.task26.presentation.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task26.databinding.ItemBallBinding

class ChildrenRecyclerAdapter(private val children: Int) :
    RecyclerView.Adapter<ChildrenRecyclerAdapter.ChildrenViewHolder>() {

    inner class ChildrenViewHolder(binding: ItemBallBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ChildrenViewHolder(
        ItemBallBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int {
        return minOf(children, 4)
    }

    override fun onBindViewHolder(holder: ChildrenViewHolder, position: Int) = Unit

}