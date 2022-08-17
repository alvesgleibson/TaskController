package com.gstech.taskcontroller.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.gstech.taskcontroller.R
import com.gstech.taskcontroller.databinding.FragmentTodoBinding

class TodoFragment : Fragment() {

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
    }

    private fun initClicks() {
        newTask()
    }

    private fun newTask(){
        binding.fabAddTask.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_formTaskFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}