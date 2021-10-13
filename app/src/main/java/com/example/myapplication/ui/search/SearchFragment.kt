package com.example.myapplication.ui.search

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.model.CityItem
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.AppNavigation
import com.example.myapplication.data.repository.Repository
import com.example.myapplication.utlis.AssetsManager


class SearchFragment : Fragment(), com.example.myapplication.ui.settings.SearchView {
    private var  _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchPresenter: SearchPresenter
    private lateinit var navigation: AppNavigation
    private lateinit var searchView: SearchView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Activity host
        if (context is AppNavigation){ navigation = context }
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
        //Setup Presenter instance
        searchPresenter = SearchPresenter(this,  Repository(AssetsManager(requireContext())))
        binding.apply {
            toolbar.inflateMenu(R.menu.menu_search)
             searchView = (toolbar.menu.findItem(R.id.app_bar_search).actionView as SearchView).apply {
                setOnQueryTextListener(queryTextListener)
            }
            //Set Adapter
            cityRoster.apply {
                adapter = CityAdapter().also { it.submitList(emptyList()) }
                layoutManager = LinearLayoutManager(
                    requireActivity(), LinearLayoutManager.VERTICAL,false
                )
            }
        }
    }
    // Listener OnQueryText
    private val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            searchPresenter.search(query)
            searchView.clearFocus()
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    }
    // Presenter set adapter
    override fun setAdapter(list: List<CityItem>) {
        binding.apply {
            if (!cityRoster.isVisible) {
                cityRoster.visibility = View.VISIBLE
            }
            if (img404.isVisible) {
                img404.visibility = View.GONE
            }
            //set adapter with funded items
            cityRoster.adapter = CityAdapter().also { cityAdapter ->
                cityAdapter.currentList.clear()
                cityAdapter.submitList(list)
            }
        }
    }
    //Presenter  404
    override fun pageNotFound() {
        binding.apply {
            if (!img404.isVisible) {
                img404.visibility = View.VISIBLE
                binding.cityRoster.visibility = View.GONE
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
        const val TAG = "SearchFragment"
    }
}