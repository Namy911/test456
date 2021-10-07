package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private var  _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    lateinit var presenter: Presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = Presenter(context)
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
        val list = presenter.setListAdapter()
        binding.apply {
            toolbar.inflateMenu(R.menu.menu_search)
            cityRoster.apply {
                adapter = CityAdapter().also { it.submitList(list) }
                layoutManager = LinearLayoutManager(
                    requireActivity(), LinearLayoutManager.VERTICAL,false
                )
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.menu_search, menu)
//        val searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        val searchView = binding.toolbar.menu.findItem(R.id.app_bar_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    presenter.search(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d(TAG, "onQueryTextChange: ")
                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.test){
            Log.d(TAG, "onOptionsItemSelected:  True")
            true
        }else{
            Log.d(TAG, "onOptionsItemSelected: Error")
            false
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
        private const val TAG = "SearchFragment"
    }
}