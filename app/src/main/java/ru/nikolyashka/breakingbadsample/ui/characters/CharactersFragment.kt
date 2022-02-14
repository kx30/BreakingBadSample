package ru.nikolyashka.breakingbadsample.ui.characters

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.nikolyashka.breakingbadsample.ui.base.BaseCharacterFragment
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.CharacterListener

@AndroidEntryPoint
class CharactersFragment : BaseCharacterFragment(), CharacterListener {

    override val viewModel: CharactersViewModel by viewModels()

    override fun openCharacterDetails(characterId: Int) {
        CharactersFragmentDirections.openCharacterDetailsFragment(characterId)
            .let(findNavController()::navigate)
    }
}