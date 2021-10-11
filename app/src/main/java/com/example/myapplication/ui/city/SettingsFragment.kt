package com.example.myapplication.ui.city

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.BuildConfig
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.FragmentSettingsBinding
import com.example.myapplication.ui.login.AuthService
import com.example.myapplication.ui.login.LoginFragment

class SettingsFragment : Fragment() {

    private var  _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSettingsBinding.inflate(inflater, container, false)
        .apply { _binding = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            search.setOnClickListener {
                (activity as MainActivity).navigateToSearch(newInstance())
            }
            btnLogout.setOnClickListener {
                requireActivity().startService(AuthService.newInstance(requireContext()))
            }
            btnLoginNav.setOnClickListener {
                (activity as MainActivity).navigateToSearch(LoginFragment.newInstance())
            }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}