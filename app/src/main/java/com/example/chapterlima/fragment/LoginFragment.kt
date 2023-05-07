package com.example.chapterlima.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.chapterlima.R
import com.example.chapterlima.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var pref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = requireContext().getSharedPreferences("dataregistrasi", Context.MODE_PRIVATE)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSignin.setOnClickListener {
            loginAccount()
        }

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_regisFragment)
        }
    }

    private fun loginAccount() {
        var getEmail = pref.getString("email", "")
        var getPassword = pref.getString("password", "")

        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if(email == getEmail && password.equals(getPassword)) {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }else {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "Berhasil Login", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    Toast.makeText(context, "Akun Tidak Valid", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}