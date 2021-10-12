package com.example.myapplication.ui.settings

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSettingsBinding
import com.example.myapplication.ui.login.LoginFragment

class SettingsFragment : Fragment() {

    private var  _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: Presenter
    private lateinit var hostActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity){
            hostActivity = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = Presenter(null, requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSettingsBinding.inflate(inflater, container, false)
        .apply { _binding = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hostActivity.apply {
            hideBottomMenu()
//            showBottomMenu()
//            hideItemBottomMenu(R.id.settings)
        }
        binding.apply {
            search.setOnClickListener {
                hostActivity.navigateTo(SearchFragment.newInstance())
            }
            btnLogout.setOnClickListener {
                presenter.clearData()
                redirect()
            }
            btnLoginNav.setOnClickListener {
                hostActivity.navigateTo(LoginFragment.newInstance())
            }
        }
    }

    private fun redirect() {
        hostActivity.navigateTo(LoginFragment.newInstance())
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}