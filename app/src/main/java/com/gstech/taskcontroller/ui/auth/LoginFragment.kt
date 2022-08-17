package com.gstech.taskcontroller.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.gstech.taskcontroller.R
import com.gstech.taskcontroller.databinding.FragmentLoginBinding
import com.gstech.taskcontroller.helper.FirebaseHelper

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()

    }


    private fun initClicks() {
        binding.txtCriarConta.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.txtRecuperarSenha.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_recoverAccountFragment)
        }

        binding.btnLogin.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {

        val email = binding.txtEmail.text.toString().trim()
        val password = binding.txtSenha.text.toString().trim()

        if (email.isNotEmpty()) {
            if (password.isNotEmpty()) {
                binding.progressbarLogin.isVisible = true
                loginUserFirebase(email, password)
            } else Toast.makeText(requireContext(), "Informe uma Senha", Toast.LENGTH_SHORT).show()
        } else Toast.makeText(requireContext(), "Informe um E-mail", Toast.LENGTH_SHORT).show()
    }

    private fun loginUserFirebase(email: String, password: String) {

        FirebaseHelper.getAuth().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_global_homeFragment)
                } else {
                    Log.i(
                        "INFOTESTE",
                        "loginUserFirebase: ${task.exception?.message}${
                            FirebaseHelper.validErrorFirebase(task.exception?.message ?: "")
                        }"
                    )
                    binding.progressbarLogin.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        FirebaseHelper.validErrorFirebase(task.exception?.message ?: ""),
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}