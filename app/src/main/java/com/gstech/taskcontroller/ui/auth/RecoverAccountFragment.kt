package com.gstech.taskcontroller.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.gstech.taskcontroller.databinding.FragmentRecoverAccountBinding
import com.gstech.taskcontroller.helper.FirebaseHelper


private var _binding: FragmentRecoverAccountBinding? = null
private val binding get() = _binding!!

private lateinit var auth: FirebaseAuth
class RecoverAccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRecoverAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        initClicks()
    }

    private fun initClicks() {

        //ir para recuperação de senha
        binding.recoverBtnSend.setOnClickListener { validateField() }
    }

    private fun validateField() {
        val email = binding.recoverEmail.text.toString().trim()

        if (email.isNotEmpty()) {
            binding.progressbarRecover.isVisible = true
            recoverAccountUser( email )
        } else {
            Toast.makeText(
                requireContext(),
                "Informe o email para recuperar senha",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun recoverAccountUser(email: String) {

        Firebase.auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        requireContext(),
                        "Pronto, Acabamos de enviar um link para seu e-mail ",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    binding.progressbarRecover.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        FirebaseHelper.validError( task.exception?.message?: "" ),
                        Toast.LENGTH_SHORT
                    ).show()
                }


            }
    }
}