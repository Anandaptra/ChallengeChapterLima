package com.example.chapterlima.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.chapterlima.R
import com.example.chapterlima.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    lateinit var sharedpref : SharedPreferences
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedpref = requireActivity().getSharedPreferences("dataregistrasi", Context.MODE_PRIVATE)


        binding.btnUpdate.setOnClickListener {
            val username = binding.updateUsername.text.toString()

            var upusername = sharedpref.edit()
            upusername.putString("username", username)
            upusername.apply()
            firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.signOut()
            Toast.makeText(context, "Update Data Berhasil", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(binding.root).navigate(R.id.action_profileFragment5_to_homeFragment)
        }

        binding.btnLogout.setOnClickListener {
            firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.signOut()
            Toast.makeText(context, "Berhasil Logout", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(binding.root).navigate(R.id.action_profileFragment5_to_loginFragment)
        }




    }

}