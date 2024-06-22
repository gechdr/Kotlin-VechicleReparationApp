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

class CustomerChatFragment : Fragment() {

    lateinit var tvCCMontir: TextView
    lateinit var rvCCChat: RecyclerView
    lateinit var etCCMessage: EditText
    lateinit var btnCCRequest: Button
    lateinit var btnCCSend: Button
    lateinit var btnCCBack: Button

    lateinit var layoutManager: LayoutManager
    lateinit var chatAdapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val paramUser = CustomerChatFragmentArgs.fromBundle(requireArguments()).User
        val paramMontir = CustomerChatFragmentArgs.fromBundle(requireArguments()).Montir

        tvCCMontir = view.findViewById(R.id.tvCCMontir)
        rvCCChat = view.findViewById(R.id.rvCCChat)
        etCCMessage = view.findViewById(R.id.etCCMessage)
        btnCCRequest = view.findViewById(R.id.btnCCRequest)
        btnCCSend = view.findViewById(R.id.btnCCSend)
        btnCCBack = view.findViewById(R.id.btnCCBack)

        tvCCMontir.text = "Montir : " + paramMontir.name

        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        chatAdapter = ChatAdapter(
            paramUser,
            paramMontir,
            MockDB.getChatConvertation(paramUser.username, paramMontir.username)
        )

        rvCCChat.adapter = chatAdapter
        rvCCChat.layoutManager = layoutManager

        var order = MockDB.getOrder(paramMontir.username, paramUser.username)

        if (order != null){
            if (order.status == "Request") {
                btnCCRequest.text = "REQUESTED"
                btnCCRequest.setBackgroundColor(resources.getColor(R.color.grey))
                btnCCRequest.isEnabled = false
            }
        }


        btnCCSend.setOnClickListener {
            val message = etCCMessage.text.toString()
            if (message.isNotEmpty()) {
                MockDB.addChat(Chat(MockDB.getLastIndexChat()+1, paramUser.username, paramMontir.username, message))

                rvCCChat.adapter = ChatAdapter(
                    paramUser,
                    paramMontir,
                    MockDB.getChatConvertation(paramUser.username, paramMontir.username)
                )

                chatAdapter.notifyDataSetChanged()
                etCCMessage.text.clear()
            }
        }

        btnCCRequest.setOnClickListener {

            if (order == null) {
                MockDB.addOrder(Order(MockDB.getLastIndexOrder()+1, paramUser.username, paramMontir.username, "?"))
                order = MockDB.getOrder(paramMontir.username, paramUser.username)
            }

            if (btnCCRequest.text == "REQUEST") {
                btnCCRequest.text = "REQUESTED"
                btnCCRequest.setBackgroundColor(resources.getColor(R.color.grey))
                btnCCRequest.isEnabled = false
                order?.status = "Requested"
            }

            Toast.makeText(context, "Request has been sent", Toast.LENGTH_SHORT).show()
        }

        btnCCBack.setOnClickListener {
            activity?.onBackPressed()
        }

    }
}