package com.example.myapplication.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.ui.city.SettingsFragment
import com.example.myapplication.ui.pef.AppPrefDataStore
import com.example.myapplication.ui.pef.dataStore
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnBack = view.findViewById<Button>(R.id.btn_back)
        val txt = view.findViewById<TextView>(R.id.txt_pass)
        lifecycleScope.launch{
            AppPrefDataStore(requireContext()).userPass.collect {
                txt.text = it.toString()
            }
        }
        btnBack.setOnClickListener {
            (activity as MainActivity).navigateToSearch(SettingsFragment.newInstance())
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}