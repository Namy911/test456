package com.example.myapplication.ui.city

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    private var  _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    lateinit var presenter: Presenter
    private lateinit var hostActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = Presenter(context)
        if (context is MainActivity){
            hostActivity = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSearchBinding.inflate(layoutInflater, container, false)
            .apply { _binding = this }
            .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hostActivity.showItemBottomMenu(R.id.settings)
        binding.apply {
            toolbar.inflateMenu(R.menu.menu_search)
            (binding.toolbar.menu.findItem(R.id.app_bar_search).actionView as SearchView).apply {
                setOnQueryTextListener(SearchCity(this))
            }
            cityRoster.apply {
                adapter = CityAdapter().also { it.submitList(emptyList()) }
                layoutManager = LinearLayoutManager(
                    requireActivity(), LinearLayoutManager.VERTICAL,false
                )
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
        private const val TAG = "SearchFragment"
    }

    inner class SearchCity(private val searchView: SearchView) : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            binding.cityRoster.adapter = CityAdapter().also { cityAdapter ->
                (presenter.search(query).toMutableList()).also { list ->
                    if (list.isNotEmpty()) {
                        cityAdapter.submitList(list)
                    } else {
                        Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            searchView.clearFocus()
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    }
}