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

class RegisterMontirFragment : Fragment() {

    lateinit var tvMRegisterTitle: TextView
    lateinit var tvMMontirTitle: TextView
    lateinit var etRMName: EditText
    lateinit var etRMUsername: EditText
    lateinit var etRMPassword: EditText
    lateinit var etRMConfirmPassword: EditText
    lateinit var etRMDescription: EditText
    lateinit var etRMLengthOfEmployment: EditText
    lateinit var etRMPrice: EditText
    lateinit var btnMRegister: Button
    lateinit var btnMRegisterCustomer: Button
    lateinit var btnMLogin: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_montir, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvMRegisterTitle = view.findViewById(R.id.tvMRegisterTitle)
        tvMMontirTitle = view.findViewById(R.id.tvMMontirTitle)
        etRMName = view.findViewById(R.id.etRMName)
        etRMUsername = view.findViewById(R.id.etRMUsername)
        etRMPassword = view.findViewById(R.id.etRMPassword)
        etRMConfirmPassword = view.findViewById(R.id.etRMConfirmPassword)
        etRMDescription = view.findViewById(R.id.etRMDescription)
        etRMLengthOfEmployment = view.findViewById(R.id.etRMLengthOfEmployment)
        etRMPrice = view.findViewById(R.id.etRMPrice)
        btnMRegister = view.findViewById(R.id.btnMRegister)
        btnMRegisterCustomer = view.findViewById(R.id.btnMRegisterCustomer)
        btnMLogin = view.findViewById(R.id.btnMLogin)

        btnMRegister.setOnClickListener {
            val name = etRMName.text.toString()
            val username = etRMUsername.text.toString()
            val password = etRMPassword.text.toString()
            val confirmPassword = etRMConfirmPassword.text.toString()
            val description = etRMDescription.text.toString()
            val lengthOfEmployment = etRMLengthOfEmployment.text.toString()
            val price = etRMPrice.text.toString()

            // Field Kosong
            if (name.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || description.isEmpty() || lengthOfEmployment.isEmpty() || price.isEmpty()) {
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

            // Price tidak valid
            try {
                price.toInt()
            } catch (e: NumberFormatException) {
                Toast.makeText(context, "Price must be a number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (price.toInt() < 0) {
                Toast.makeText(context, "Price must be greater than 0", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Register
            MockDB.addUser(User(name, username, password, "Montir", description, lengthOfEmployment, price.toInt(),  0))
            Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show()

            // Reset
            etRMName.setText("")
            etRMUsername.setText("")
            etRMPassword.setText("")
            etRMConfirmPassword.setText("")
            etRMDescription.setText("")
            etRMLengthOfEmployment.setText("")
            etRMPrice.setText("")
        }

        btnMRegisterCustomer.setOnClickListener {
            findNavController().navigate(R.id.action_global_registerCustomerFragment)
        }

        btnMLogin.setOnClickListener {
            findNavController().navigate(R.id.action_global_loginFragment)
        }
    }
}