package com.gstech.taskcontroller.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.gstech.taskcontroller.R
import com.gstech.taskcontroller.databinding.FragmentSplashBinding
import com.gstech.taskcontroller.helper.FirebaseHelper

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed(this::checkAuth, 3000)
    }


    private fun checkAuth(){
        //verificar se o usuario esta logado no cache do firebase, se sim, ele ira passar direto pela tela home
        if (FirebaseHelper.isAuthentication()){

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