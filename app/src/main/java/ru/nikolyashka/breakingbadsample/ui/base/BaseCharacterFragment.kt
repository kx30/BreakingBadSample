package ru.nikolyashka.breakingbadsample.ui.base

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.CharacterListener
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.PaginationScrollListener
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType
import ru.nikolyashka.gateway.Constants.DEFAULT_LIMIT

abstract class BaseCharacterFragment<VB : ViewBinding> : BaseFragment<VB>(), CharacterListener {

    abstract override val viewModel: BaseCharacterViewModel
    protected abstract val recyclerView: RecyclerView

    protected abstract val adapter: BaseCharacterAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setUpObservers()
    }

    override fun addToFavorite(character: CharacterUiType.CharacterUiModel) {
        viewModel.onAddToFavorite(character)
    }

    override fun loadCharacters() {
        viewModel.onLoadData()
    }

    private fun setUpObservers() {
        viewModel.characters.observe(this, {
            adapter.submitList(it)
        })
    }

    private fun setUpAdapter() {
        val linearLayout = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearLayout
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.addOnScrollListener(
            PaginationScrollListener(DEFAULT_LIMIT) {
                viewModel.onLoadMoreData(linearLayout.findLastVisibleItemPosition())
            }
        )
    }
}