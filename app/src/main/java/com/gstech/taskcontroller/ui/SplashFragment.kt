package com.gstech.taskcontroller.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.gstech.taskcontroller.R
import com.gstech.taskcontroller.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        Handler(Looper.getMainLooper()).postDelayed(this::checkAuth, 3000)
    }


    private fun checkAuth(){
        //verificar se o usuario esta logado no cache do firebase, se sim, ele ira passar direto pela tela home
        if (auth.currentUser != null){

            findNavController().navigate( R.id.action_splashFragment_to_homeFragment2)
        }
        else
        findNavController().navigate(R.id.action_splashFragment_to_authentication_navigation)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}