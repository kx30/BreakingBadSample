package ru.nikolyashka.breakingbadsample.ui.characters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.nikolyashka.breakingbadsample.databinding.FragmentCharactersBinding
import ru.nikolyashka.breakingbadsample.ui.base.BaseFragment
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.CharacterAdapter
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.CharacterListener
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.PaginationScrollListener
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType

@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding>(), CharacterListener {

    override val viewModel: CharactersViewModel by viewModels()
    private val adapter = CharacterAdapter(this)


    override fun getViewBinding(): FragmentCharactersBinding = FragmentCharactersBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCharacter.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCharacter.adapter = adapter
        binding.rvCharacter.addOnScrollListener(PaginationScrollListener(10, viewModel::onLoadData))

        viewModel.characters.observe(this, {
            adapter.submitList(it)
        })
    }

    override fun addToFavorite(character: CharacterUiType.CharacterUiModel) {

    }

    override fun loadCharacters() {
        viewModel.onLoadData()
    }
}