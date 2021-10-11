package com.example.myapplication.ui.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.FragmentLoginBinding
import com.example.myapplication.ui.city.MainFragment
import com.example.myapplication.ui.pef.AppPrefDataStore
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private var  _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var hostActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity){
            hostActivity = context
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentLoginBinding.inflate(inflater, container, false)
        .apply { _binding = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hostActivity.hideBottomMenu()
        binding.apply {
            lifecycleScope.launch {
                AppPrefDataStore(requireContext()).userPass.collect {
                    txtPass.text = it.toString()
                }
            }
            btnPref.setOnClickListener {
                lifecycleScope.launch {
                    AppPrefDataStore(requireContext()).setPrefUserLogin()
                }

            }
            btnLogin.setOnClickListener {
                hostActivity.navigateTo(MainFragment.newInstance())
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}