package com.example.myapplication.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.data.model.CityItem
import com.example.myapplication.databinding.FragmentSearchBinding


class SearchFragment : Fragment(), com.example.myapplication.ui.settings.SearchView {
    private var  _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    lateinit var presenter: Presenter
    private lateinit var hostActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity){ hostActivity = context }
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
        presenter = Presenter(this, requireContext())
        binding.apply {
            toolbar.inflateMenu(R.menu.menu_search)
            (toolbar.menu.findItem(R.id.app_bar_search).actionView as SearchView).apply {
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

    override fun setAdapter(list: MutableList<CityItem>) {
        binding.apply {
            if (!cityRoster.isVisible) {
                cityRoster.visibility = View.VISIBLE
            }
            if (img404.isVisible) {
                img404.visibility = View.GONE
            }
            cityRoster.adapter = CityAdapter().also { cityAdapter ->
                cityAdapter.currentList.clear()
                cityAdapter.submitList(list)
            }
        }
    }

    override fun pageNotFound(){
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

    inner class SearchCity(private val searchView: SearchView) : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            presenter.search(query)
            searchView.clearFocus()
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    }
}