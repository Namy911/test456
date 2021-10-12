package com.example.myapplication.ui.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.FragmentLoginBinding
import com.example.myapplication.AppNavigation
import com.example.myapplication.ui.settings.MainFragment
import com.example.myapplication.pef.AppPrefDataStore
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var hostActivity: AppNavigation
    private lateinit var appPref: AppPrefDataStore

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AppNavigation) { hostActivity = context }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentLoginBinding.inflate(inflater, container, false)
        .apply { _binding = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appPref = AppPrefDataStore(requireContext())
        hostActivity.hideBottomMenu()
        binding.apply {
            lifecycleScope.launch {
                appPref.userPass.collect {
                    txtPass.text = it.toString()
                }
            }
            btnLogin.setOnClickListener {
//                hostActivity.navigateTo(MainFragment.newInstance())
                lifecycleScope.launch {
                    appPref.setPrefUserLogin()
                }
            }
            button.setOnClickListener {
                hostActivity.navigateTo(MainFragment.newInstance(), TAG)
            }
        }
    }

    companion object {
        const val TAG = "LoginFragment"
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}
