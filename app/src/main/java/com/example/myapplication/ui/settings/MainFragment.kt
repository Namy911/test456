package com.example.myapplication.ui.settings

import android.content.Context
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.ui.pef.AppPrefDataStore
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private  var _binding: FragmentMainBinding? = null
    private val binding get()  = _binding!!
    private lateinit var hostActivity: MainActivity
    private lateinit var appPref: AppPrefDataStore

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity){ hostActivity = context }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentMainBinding.inflate(inflater, container, false)
        .apply { _binding = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appPref = AppPrefDataStore(requireContext())
        hostActivity.showBottomMenu()
        lifecycleScope.launch{
            appPref.userPass.collectLatest {
                binding.textView.text = it.toString()
                Log.d(TAG, "onViewCreated: $it")
            }
        }
    }
    companion object {
        const val TAG = "MainFragment"
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}