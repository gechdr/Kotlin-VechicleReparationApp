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

class RegisterCustomerFragment : Fragment() {

    lateinit var tvCRegisterTitle: TextView
    lateinit var tvCCustomerTitle: TextView
    lateinit var etRCName: EditText
    lateinit var etRCUsername: EditText
    lateinit var etRCPassword: EditText
    lateinit var etRCConfirmPassword: EditText
    lateinit var btnCRegister: Button
    lateinit var btnCRegisterMontir: Button
    lateinit var btnCLogin: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_customer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvCRegisterTitle = view.findViewById(R.id.tvCRegisterTitle)
        tvCCustomerTitle = view.findViewById(R.id.tvCCustomerTitle)
        etRCName = view.findViewById(R.id.etRCName)
        etRCUsername = view.findViewById(R.id.etRCUsername)
        etRCPassword = view.findViewById(R.id.etRCPassword)
        etRCConfirmPassword = view.findViewById(R.id.etRCConfirmPassword)
        btnCRegister = view.findViewById(R.id.btnCRegister)
        btnCRegisterMontir = view.findViewById(R.id.btnCRegisterMontir)
        btnCLogin = view.findViewById(R.id.btnCLogin)

        btnCRegister.setOnClickListener {
            val name = etRCName.text.toString()
            val username = etRCUsername.text.toString()
            val password = etRCPassword.text.toString()
            val confirmPassword = etRCConfirmPassword.text.toString()

            // Field Kosong
            if (name.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Username sudah terdaftar
            val user = MockDB.getUserByUsername(username)
            if (user != null) {
                Toast.makeText(context, "Username already exists", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Password tidak sama
            if (password != confirmPassword) {
                Toast.makeText(context, "Password does not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Register
            MockDB.addUser(User(name, username, password, "Customer",null,null,null,null))
            Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show()

            // Reset
            etRCName.setText("")
            etRCUsername.setText("")
            etRCPassword.setText("")
            etRCConfirmPassword.setText("")
        }

        btnCRegisterMontir.setOnClickListener {
            findNavController().navigate(R.id.action_global_registerMontirFragment)
        }

        btnCLogin.setOnClickListener {
            findNavController().navigate(R.id.action_global_loginFragment)
        }
    }
}