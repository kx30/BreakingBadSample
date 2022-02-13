package ru.nikolyashka.breakingbadsample.ui.base

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.nikolyashka.breakingbadsample.databinding.FragmentCharactersBinding
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.CharacterAdapter
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.CharacterListener
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.PaginationScrollListener
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType

abstract class BaseCharacterFragment : BaseFragment<FragmentCharactersBinding>(), CharacterListener {

    abstract override val viewModel: BaseCharacterViewModel

    protected val adapter: CharacterAdapter by lazy {
        CharacterAdapter(this)
    }


    override fun getViewBinding(): FragmentCharactersBinding = FragmentCharactersBinding.inflate(layoutInflater)

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
        with(binding) {
            rvCharacter.layoutManager = LinearLayoutManager(requireContext())
            rvCharacter.adapter = adapter
            rvCharacter.addOnScrollListener(PaginationScrollListener(10, viewModel::onLoadData))
        }
    }
}