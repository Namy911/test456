package com.example.myapplication.ui.settings

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.FragmentSettingsBinding
import com.example.myapplication.AppNavigation
import com.example.myapplication.R
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.pef.AppPrefDataStore
import com.example.myapplication.ui.login.LoginFragment
import com.example.myapplication.ui.search.SettingsView
import com.example.myapplication.ui.search.SearchFragment
import com.example.myapplication.utlis.AssetsManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SettingsFragment : Fragment(), SettingsView {
    private var  _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var settingsPresenter: SettingsPresenter
    private lateinit var navigation: AppNavigation
    private lateinit var appPref: AppPrefDataStore


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AppNavigation){ navigation = context }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
        settingsPresenter = SettingsPresenter( this , Repository(AssetsManager(requireContext())))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSettingsBinding.inflate(inflater, container, false)
        .apply { _binding = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appPref = AppPrefDataStore(requireContext())
        navigation.showBottomMenu()
        setUnitPreference()
        binding.apply {
            search.setOnClickListener {
                navigation.navigateTo(SearchFragment.newInstance(), SearchFragment.TAG)
            }
            btnLogout.setOnClickListener {
                lifecycleScope.launch {
                    settingsPresenter.clearData()
                    redirect()
                }
            }
            btnLoginNav.setOnClickListener {
                navigation.navigateTo(LoginFragment.newInstance(), LoginFragment.TAG)
            }
            // write in dat store chose
            rgUnitPref.setOnCheckedChangeListener { _, id ->
                lifecycleScope.launch {
                    when (id) {
                        R.id.unit_pref_c -> {
                            settingsPresenter.setPrefUnit(1)
                        }
                        R.id.unit_pref_f -> {
                            settingsPresenter.setPrefUnit(2)
                        }
                        R.id.unit_pref_k -> {
                            settingsPresenter.setPrefUnit(0)
                        }
                    }
                }
            }
        }
    }

    private fun redirect() {
        navigation.navigateTo(LoginFragment.newInstance(), LoginFragment.TAG)
    }
    // ini radio group with pref
    override fun setUnitPreference() {
        lifecycleScope.launchWhenResumed {
            AppPrefDataStore(requireContext()).unitPref.collect { unit ->
                when(unit){
                    0 -> { binding.rgUnitPref.check(R.id.unit_pref_k) }
                    1 -> { binding.rgUnitPref.check(R.id.unit_pref_c) }
                    2 -> { binding.rgUnitPref.check(R.id.unit_pref_f) }
                }
            }
        }
    }

    companion object {
        const val TAG = "SettingsFragment"
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }

}