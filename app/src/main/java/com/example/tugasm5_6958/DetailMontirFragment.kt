package com.example.tugasm5_6958

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.tugasm5_6958.CurrencyUtils.toRupiah

class DetailMontirFragment : Fragment() {

    lateinit var tvDMNama: TextView
    lateinit var tvDMPengalamanKerja: TextView
    lateinit var tvDMDeskripsiTitle: TextView
    lateinit var tvDMDeskripsi: TextView
    lateinit var tvDMBiaya: TextView
    lateinit var btnDMChat: Button
    lateinit var btnDMBack: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_montir, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val paramUser = DetailMontirFragmentArgs.fromBundle(arguments as Bundle).User
        val paramMontir = DetailMontirFragmentArgs.fromBundle(arguments as Bundle).Montir

        tvDMNama = view.findViewById(R.id.tvDMNama)
        tvDMPengalamanKerja = view.findViewById(R.id.tvDMPengalamanKerja)
        tvDMDeskripsiTitle = view.findViewById(R.id.tvDMDeskripsiTitle)
        tvDMDeskripsi = view.findViewById(R.id.tvDMDeskripsi)
        tvDMBiaya = view.findViewById(R.id.tvDMBiaya)
        btnDMChat = view.findViewById(R.id.btnDMChat)
        btnDMBack = view.findViewById(R.id.btnDMBack)

        tvDMNama.text = paramMontir.name
        tvDMPengalamanKerja.text = "Pengalaman Kerja : " + paramMontir.lengthOfEmployment
        tvDMDeskripsi.text = paramMontir.description
        tvDMBiaya.text = "Biaya : " + paramMontir.price?.toRupiah()

        btnDMChat.setOnClickListener {



            val action = DetailMontirFragmentDirections.actionDetailMontirFragmentToCustomerChatFragment(paramUser, paramMontir)
            findNavController().navigate(action)
        }

        btnDMBack.setOnClickListener {
            activity?.onBackPressed()
        }

    }
}