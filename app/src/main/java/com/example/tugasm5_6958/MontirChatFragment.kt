package com.example.tugasm5_6958

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class MontirChatFragment : Fragment() {

    lateinit var tvMCNama: TextView
    lateinit var rvMCChat: RecyclerView
    lateinit var etMCMessage: EditText
    lateinit var btnMCAccept: Button
    lateinit var btnMCSend: Button
    lateinit var btnMCBack: Button

    lateinit var layoutManager: LayoutManager
    lateinit var chatAdapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_montir_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val paramUser = MontirChatFragmentArgs.fromBundle(arguments as Bundle).User
        val paramCustomer = MontirChatFragmentArgs.fromBundle(arguments as Bundle).Customer

        tvMCNama = view.findViewById(R.id.tvMCNama)
        rvMCChat = view.findViewById(R.id.rvMCChat)
        etMCMessage = view.findViewById(R.id.etMCMessage)
        btnMCAccept = view.findViewById(R.id.btnMCAccept)
        btnMCSend = view.findViewById(R.id.btnMCSend)
        btnMCBack = view.findViewById(R.id.btnMCBack)

        tvMCNama.text = "Nama : " + paramCustomer.name

        val order = MockDB.getOrder(paramUser.username, paramCustomer.username)

        if (order?.status == "Requested"){
            btnMCAccept.isEnabled = true
            btnMCAccept.setBackgroundColor(resources.getColor(R.color.green))
        } else {
            btnMCAccept.isEnabled = false
            btnMCAccept.setBackgroundColor(resources.getColor(R.color.grey))
        }

        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        chatAdapter = ChatAdapter(
            paramUser,
            paramCustomer,
            MockDB.getChatConvertation(paramUser.username, paramCustomer.username)
        )

        rvMCChat.layoutManager = layoutManager
        rvMCChat.adapter = chatAdapter

        btnMCAccept.setOnClickListener {
            if (order != null){
                order.status = "Accepted"
                btnMCAccept.isEnabled = false
                btnMCAccept.text = "ACCEPTED"
                btnMCAccept.setBackgroundColor(resources.getColor(R.color.grey))

                Toast.makeText(context, "Request Accepted!", Toast.LENGTH_SHORT).show()
            }
        }

        btnMCSend.setOnClickListener {
            val message = etMCMessage.text.toString()
            if (message.isNotEmpty()){
                MockDB.addChat(Chat(MockDB.getLastIndexChat()+1, paramUser.username, paramCustomer.username, message))
                rvMCChat.adapter = ChatAdapter(
                    paramUser,
                    paramCustomer,
                    MockDB.getChatConvertation(paramUser.username, paramCustomer.username)
                )
                chatAdapter.notifyDataSetChanged()
                etMCMessage.text.clear()
            }
        }

        btnMCBack.setOnClickListener {
            activity?.onBackPressed()
        }

    }
}