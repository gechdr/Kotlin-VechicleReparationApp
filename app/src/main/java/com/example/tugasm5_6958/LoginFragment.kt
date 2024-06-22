package com.example.tugasm5_6958

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment() {

    lateinit var tvLoginTitle: TextView
    lateinit var etUsername: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button
    lateinit var btnRegisterCustomer: Button
    lateinit var btnRegisterMontir: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvLoginTitle = view.findViewById(R.id.tvLoginTitle)
        etUsername = view.findViewById(R.id.etUsername)
        etPassword = view.findViewById(R.id.etPassword)
        btnLogin = view.findViewById(R.id.btnLogin)
        btnRegisterCustomer = view.findViewById(R.id.btnRegisterCustomer)
        btnRegisterMontir = view.findViewById(R.id.btnRegisterMontir)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            // Field Kosong
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Username terdaftar
            val user = MockDB.getUserByUsername(username)
            if (user == null) {
                Toast.makeText(context, "Username is not registered", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Password salah
            if (password != user.password) {
                Toast.makeText(context, "Password is incorrect", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Login berhasil
            Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
            Toast.makeText(context, "Welcome, ${user.name}", Toast.LENGTH_SHORT).show()

            // Reset
            etUsername.setText("")
            etPassword.setText("")

            // Redirect
            if (user.role == "Customer") {
                val action = LoginFragmentDirections.actionLoginFragmentToHomeCustomerFragment(user)
                findNavController().navigate(action)
            } else {
                val action = LoginFragmentDirections.actionLoginFragmentToHomeMontirFragment(user)
                findNavController().navigate(action)
            }
        }

        btnRegisterCustomer.setOnClickListener {
            findNavController().navigate(R.id.action_global_registerCustomerFragment)
        }

        btnRegisterMontir.setOnClickListener {
            findNavController().navigate(R.id.action_global_registerMontirFragment)
        }

    }
}