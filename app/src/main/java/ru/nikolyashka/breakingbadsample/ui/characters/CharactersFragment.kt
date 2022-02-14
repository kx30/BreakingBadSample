package ru.nikolyashka.breakingbadsample.ui.characters

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.nikolyashka.breakingbadsample.databinding.FragmentCharactersBinding
import ru.nikolyashka.breakingbadsample.ui.base.BaseCharacterFragment
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.CharacterAdapter
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.CharacterListener

@AndroidEntryPoint
class CharactersFragment : BaseCharacterFragment<FragmentCharactersBinding>(), CharacterListener {

    override val adapter by lazy {
        CharacterAdapter(this)
    }
    override val viewModel: CharactersViewModel by viewModels()
    override val recyclerView: RecyclerView
        get() = binding.rvCharacter


    override fun getViewBinding(): FragmentCharactersBinding = FragmentCharactersBinding.inflate(layoutInflater)

    override fun openCharacterDetails(characterId: Int) {
        CharactersFragmentDirections.openCharacterDetailsFragment(characterId)
            .let(findNavController()::navigate)
    }
}