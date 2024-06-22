package com.example.tugasm5_6958

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasm5_6958.CurrencyUtils.toRupiah

class ChatAdapter(
    val User: User,
    val OtherUser: User,
    var data: MutableList<Chat>,
) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    class ViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val tvCISender: TextView = row.findViewById(R.id.tvCISender)
        val tvCIChat: TextView = row.findViewById(R.id.tvCIChat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
        return ViewHolder(itemView.inflate(
            R.layout.chat_item, parent, false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chat = data[position]

        var sender: User? = null

        if (chat.sender == User.username) {
            sender = User
        } else {
            sender = OtherUser
        }

        holder.tvCISender.text = sender.name
        holder.tvCIChat.text = chat.message
    }

    override fun getItemCount(): Int {
        return data.size
    }
}