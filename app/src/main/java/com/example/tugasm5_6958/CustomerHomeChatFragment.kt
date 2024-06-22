package com.example.tugasm5_6958

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CustomerHomeChatFragment : Fragment() {

    lateinit var tvCHCChatTitle: TextView
    lateinit var rvCHCChat: RecyclerView
    lateinit var btnCHCHome: Button
    lateinit var btnCHCLogout: Button

    lateinit var chatAdapter: HomeChatCustomerAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_home_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val paramUser = CustomerHomeChatFragmentArgs.fromBundle(requireArguments()).User

        tvCHCChatTitle = view.findViewById(R.id.tvCHCChatTitle)
        rvCHCChat = view.findViewById(R.id.rvCHCChat)
        btnCHCHome = view.findViewById(R.id.btnCHCHome)
        btnCHCLogout = view.findViewById(R.id.btnCHCLogout)

        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        chatAdapter = HomeChatCustomerAdapter(paramUser, MockDB.getChatBySenderNoDuplicate(paramUser.username)) { montir ->
            val action = CustomerHomeChatFragmentDirections.actionCustomerHomeChatFragmentToCustomerChatFragment(paramUser, montir)
            findNavController().navigate(action)
        }

        rvCHCChat.adapter = chatAdapter
        rvCHCChat.layoutManager = layoutManager

        btnCHCHome.setOnClickListener {
            val action = CustomerHomeChatFragmentDirections.actionGlobalHomeCustomerFragment(paramUser)
            findNavController().navigate(action)
        }

        btnCHCLogout.setOnClickListener {
            findNavController().navigate(R.id.action_global_loginFragment)
        }

    }
}