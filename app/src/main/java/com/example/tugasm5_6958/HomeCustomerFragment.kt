package com.example.tugasm5_6958

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class HomeCustomerFragment : Fragment() {

    lateinit var tvHCWelcomeTitle: TextView
    lateinit var tvHCCariMontir: TextView
    lateinit var etHCNamaMontir: EditText
    lateinit var tvHCDaftarMontir: TextView
    lateinit var rvHCDaftarMontir: RecyclerView
    lateinit var btnHCChat: Button
    lateinit var btnHCLogout: Button

    lateinit var layoutManager: LayoutManager
    lateinit var montirAdapter: MontirAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_customer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val paramUser = HomeCustomerFragmentArgs.fromBundle(arguments as Bundle).User

        tvHCWelcomeTitle = view.findViewById(R.id.tvHCWelcomeTitle)
        tvHCCariMontir = view.findViewById(R.id.tvHCCariMontir)
        etHCNamaMontir = view.findViewById(R.id.etHCNamaMontir)
        tvHCDaftarMontir = view.findViewById(R.id.tvHCDaftarMontir)
        rvHCDaftarMontir = view.findViewById(R.id.rvHCDaftarMontir)
        btnHCChat = view.findViewById(R.id.btnHCChat)
        btnHCLogout = view.findViewById(R.id.btnHCLogout)

        tvHCWelcomeTitle.text = "Welcome, " + paramUser.name

        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        montirAdapter = MontirAdapter(MockDB.getUserByRole("Montir")) {montir ->
            val action = HomeCustomerFragmentDirections.actionHomeCustomerFragmentToDetailMontirFragment(montir, paramUser)
            findNavController().navigate(action)
        }

        rvHCDaftarMontir.adapter = montirAdapter
        rvHCDaftarMontir.layoutManager = layoutManager

        etHCNamaMontir.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val curString = s.toString()

                val newListMontir = MockDB.getUserByRole("Montir").filter { montir ->
                    montir.name.contains(curString, ignoreCase = true)
                }

                montirAdapter.data = newListMontir.toMutableList()
                montirAdapter.notifyDataSetChanged()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        btnHCChat.setOnClickListener {
            val action = HomeCustomerFragmentDirections.actionHomeCustomerFragmentToCustomerHomeChatFragment(paramUser)
            findNavController().navigate(action)
        }

        btnHCLogout.setOnClickListener {
            findNavController().navigate(R.id.action_global_loginFragment)
        }
    }
}