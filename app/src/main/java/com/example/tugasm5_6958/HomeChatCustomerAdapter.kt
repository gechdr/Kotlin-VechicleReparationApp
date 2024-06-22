package com.example.tugasm5_6958

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasm5_6958.CurrencyUtils.toRupiah

class HomeChatCustomerAdapter(
    var user: User,
    var data: MutableList<Chat>,
    val onDetailClickListener: (User) -> Unit
) : RecyclerView.Adapter<HomeChatCustomerAdapter.ViewHolder>() {

    class ViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val tvHCCMontir: TextView = row.findViewById(R.id.tvHCCMontir)
        val tvHCCChat: TextView = row.findViewById(R.id.tvHCCChat)
        val btnHCCOpen: Button = row.findViewById(R.id.btnHCCOpen)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
        return ViewHolder(itemView.inflate(
            R.layout.home_chat_customer_item, parent, false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chat = data[position]

        var montir: User? = null

        if (chat.sender == user.username) {
            montir = MockDB.getUserByUsername(chat.receiver)
        }

        holder.tvHCCMontir.text = "Montir : " + montir!!.name
        holder.tvHCCChat.text = "Chat : " + chat.message

        holder.btnHCCOpen.setOnClickListener {
            onDetailClickListener.invoke(montir)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}