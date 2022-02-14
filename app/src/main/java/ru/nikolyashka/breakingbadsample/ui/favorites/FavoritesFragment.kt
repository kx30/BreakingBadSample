package ru.nikolyashka.breakingbadsample.ui.favorites

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.nikolyashka.breakingbadsample.ui.base.BaseCharacterFragment

@AndroidEntryPoint
class FavoritesFragment : BaseCharacterFragment() {

    override val viewModel: FavoritesViewModel by viewModels()


    override fun openCharacterDetails(characterId: Int) {
        FavoritesFragmentDirections.openCharacterDetailsFragment(characterId)
            .let(findNavController()::navigate)
    }
}