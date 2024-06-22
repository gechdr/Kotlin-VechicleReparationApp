package com.example.tugasm5_6958

import android.content.res.ColorStateList
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class HomeChatMontirAdapter(
    var user: User,
    var data: MutableList<Chat>,
    val onDetailClickListener: (User) -> Unit
) : RecyclerView.Adapter<HomeChatMontirAdapter.ViewHolder>() {

    class ViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val tvHCMNama: TextView = row.findViewById(R.id.tvHCMNama)
        val tvHCMChat: TextView = row.findViewById(R.id.tvHCMChat)
        val btnHCMDone: Button = row.findViewById(R.id.btnHCMDone)
        val btnHCMOpen: Button = row.findViewById(R.id.btnHCMOpen)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
        return ViewHolder(itemView.inflate(
            R.layout.home_chat_montir_item, parent, false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chat = data[position]

        var customer: User? = null

        if (chat.sender == user.username) {
            customer = MockDB.getUserByUsername(chat.receiver)
        } else {
            customer = MockDB.getUserByUsername(chat.sender)
        }

        holder.tvHCMNama.text = "Nama : " + customer!!.name
        holder.tvHCMChat.text = "Chat : " + chat.message

        val order = MockDB.getOrder(user.username, customer.username)

        if (order?.status != "Accepted"){
            holder.btnHCMDone.isEnabled = false
            holder.btnHCMDone.setBackgroundColor(holder.row.resources.getColor(R.color.grey))
        }

        holder.btnHCMDone.setOnClickListener {

            if (order != null) {
                order.status = "Done"
                user.pendapatan = user.pendapatan?.plus(user.price!!)
            }

            // Delete Chat
            MockDB.deleteChatConvertation(user.username, customer.username)

            // Delete Order
            MockDB.deleteOrder(user.username, customer.username)

            Toast.makeText(holder.row.context, "Order selesai!", Toast.LENGTH_SHORT).show()

            val action = HomeMontirFragmentDirections.actionGlobalHomeMontirFragment(user)
            holder.row.findNavController().navigate(action)
        }

        holder.btnHCMOpen.setOnClickListener {
            onDetailClickListener.invoke(customer)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}