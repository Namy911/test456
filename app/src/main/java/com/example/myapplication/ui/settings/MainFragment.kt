package com.example.myapplication.ui.settings

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private  var _binding: FragmentMainBinding? = null
    private val binding get()  = _binding!!
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
    ) = FragmentMainBinding.inflate(inflater, container, false)
        .apply { _binding = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hostActivity.apply {
            showBottomMenu()
            showItemBottomMenu(R.id.settings)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}