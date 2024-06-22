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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasm5_6958.CurrencyUtils.toRupiah

class HomeMontirFragment : Fragment() {

    lateinit var tvHMWelcomeTitle: TextView
    lateinit var tvHMTotalPendapatanTitle: TextView
    lateinit var tvHMTotalPendapatanValue: TextView
    lateinit var tvHMChatTitle: TextView
    lateinit var rvHMChat: RecyclerView
    lateinit var tvHMBiayaTitle: TextView
    lateinit var etHMBiaya: EditText
    lateinit var btnHMSave: Button
    lateinit var btnHMLogout: Button

    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var chatMontirAdapter: HomeChatMontirAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_montir, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val paramUser = HomeMontirFragmentArgs.fromBundle(arguments as Bundle).User

        tvHMWelcomeTitle = view.findViewById(R.id.tvHMWelcomeTitle)
        tvHMTotalPendapatanTitle = view.findViewById(R.id.tvHMTotalPendapatanTitle)
        tvHMTotalPendapatanValue = view.findViewById(R.id.tvHMTotalPendapatanValue)
        tvHMChatTitle = view.findViewById(R.id.tvHMChatTitle)
        rvHMChat = view.findViewById(R.id.rvHMChat)
        tvHMBiayaTitle = view.findViewById(R.id.tvHMBiayaTitle)
        etHMBiaya = view.findViewById(R.id.etHMBiaya)
        btnHMSave = view.findViewById(R.id.btnHMSave)
        btnHMLogout = view.findViewById(R.id.btnHMLogout)

        tvHMWelcomeTitle.text = "Welcome, " + paramUser.name
        tvHMTotalPendapatanValue.text = paramUser.pendapatan?.toRupiah()
        etHMBiaya.setText(paramUser.price.toString())

        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        chatMontirAdapter = HomeChatMontirAdapter(paramUser,  MockDB.getChatBySenderNoDuplicate(paramUser.username))
        { customer ->
            val action = HomeMontirFragmentDirections.actionHomeMontirFragmentToMontirChatFragment(paramUser, customer)
            findNavController().navigate(action)
        }

        rvHMChat.layoutManager = layoutManager
        rvHMChat.adapter = chatMontirAdapter

        btnHMSave.setOnClickListener {
            paramUser.price = etHMBiaya.text.toString().toInt()

            Toast.makeText(context, "Biaya tersimpan!", Toast.LENGTH_SHORT).show()

            etHMBiaya.setText(paramUser.price.toString())
        }

        btnHMLogout.setOnClickListener {
            findNavController().navigate(R.id.action_global_loginFragment)
        }
    }
}