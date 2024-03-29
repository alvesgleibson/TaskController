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
import com.gstech.taskcontroller.databinding.FragmentRegisterBinding
import com.gstech.taskcontroller.helper.FirebaseHelper

class RegisterFragment : Fragment() {

    //viewBinding
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClicks()

    }

    private fun initClicks() {
        binding.btnCriarConta.setOnClickListener { validateData() }
    }

    private fun validateData() {
        val email = binding.edtEmail.text.toString().trim()
        val passw = binding.edtPassword.text.toString().trim()
        val passwRe = binding.edtPasswordRepiti.text.toString().trim()




        if (email.isNotEmpty()) {
            if (passw.isNotEmpty()) {
                if (passwRe.isNotEmpty()) {
                    if (passw == passwRe) {
                        binding.pbCreateAccount.isVisible = true
                        registeUser(email, passw)
                    } else Toast.makeText(
                        requireContext(),
                        "Senha não corresponde",
                        Toast.LENGTH_SHORT
                    ).show()
                } else Toast.makeText(
                    requireContext(),
                    "preencha o campo repita sua senha",
                    Toast.LENGTH_SHORT
                ).show()
            } else Toast.makeText(requireContext(), "Informe sua senha", Toast.LENGTH_SHORT).show()
        } else Toast.makeText(requireContext(), "Informe seu e-mail", Toast.LENGTH_SHORT).show()


    }

    private fun registeUser(email: String, password: String) {

        FirebaseHelper.getAuth().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_global_homeFragment)
                } else {
                    binding.pbCreateAccount.isVisible = false
                    Log.i(
                        "INFOTESTE",
                        "registerUserFirebase: ${task.exception?.message}"
                    )
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