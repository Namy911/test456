package com.example.myapplication.ui.settings

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentSettingsBinding
import com.example.myapplication.AppNavigation
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.ui.login.LoginFragment
import com.example.myapplication.utlis.AssistedManager

class SettingsFragment : Fragment() {
    private var  _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: Presenter
    private lateinit var hostActivity: AppNavigation

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AppNavigation){ hostActivity = context }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
        presenter = Presenter(null, Repository(AssistedManager(requireContext())))
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
            showBottomMenu()
        }
        binding.apply {
            search.setOnClickListener {
                hostActivity.navigateTo(SearchFragment.newInstance(), SearchFragment.TAG)
            }
            btnLogout.setOnClickListener {
                presenter.clearData()
                redirect()
            }
            btnLoginNav.setOnClickListener {
                hostActivity.navigateTo(LoginFragment.newInstance(), LoginFragment.TAG)
            }
        }
    }

    private fun redirect() {
        hostActivity.navigateTo(LoginFragment.newInstance(), LoginFragment.TAG)
    }

    companion object {
        const val TAG = "SettingsFragment"
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}