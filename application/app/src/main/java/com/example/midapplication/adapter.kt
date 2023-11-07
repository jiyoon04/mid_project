package com.example.midapplication

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.midapplication.domain.Repository
import com.example.midapplication.databinding.ItemRecyclerBinding
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.example.midapplication.domain.intruder

class adapter : RecyclerView.Adapter<Holder>() {

    var userlist : Repository? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = userlist?.get(position)
        holder.setUser(user)
    }

    override fun getItemCount(): Int {
        return userlist?.size?:0
    }
}

class Holder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root){
    fun setUser(user: intruder?) {
        user?.let { user ->
            with(binding) {
                texttitle.text = user.title
                texttext.text = user.text
                Glide.with(imageAvatar).load(user.image).into(imageAvatar)
            }
        }
    }
}