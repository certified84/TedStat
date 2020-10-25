package com.devloyde.healthguard.ui.walkthrough

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.devloyde.healthguard.R
import com.devloyde.healthguard.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.welcomeBtn.setOnClickListener {
            gotoHome()
        }
    }

    private fun gotoHome() {
        navController.navigate(R.id.action_welcomeFragment_to_mainActivity)
    }

}