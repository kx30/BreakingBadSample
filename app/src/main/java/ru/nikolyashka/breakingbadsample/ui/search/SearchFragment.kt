package ru.nikolyashka.breakingbadsample.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.nikolyashka.breakingbadsample.databinding.FragmentSearchBinding
import ru.nikolyashka.breakingbadsample.extensions.hideKeyboard
import ru.nikolyashka.breakingbadsample.ui.base.BaseCharacterFragment
import ru.nikolyashka.breakingbadsample.ui.search.adapter.SearchAdapter

@AndroidEntryPoint
class SearchFragment : BaseCharacterFragment<FragmentSearchBinding>() {

    override val adapter by lazy {
        SearchAdapter(this)
    }
    override val viewModel: SearchViewModel by viewModels()
    override val recyclerView: RecyclerView
        get() = binding.rvSearch


    override fun getViewBinding(): FragmentSearchBinding = FragmentSearchBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
    }

    override fun openCharacterDetails(characterId: Int) {

    }

    private fun setUpListeners() {
        binding.etSearch.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    viewModel.onSearch(binding.etSearch.text.toString())
                    requireContext().hideKeyboard(requireView())
                    return true
                }
                return false
            }
        })
    }
}