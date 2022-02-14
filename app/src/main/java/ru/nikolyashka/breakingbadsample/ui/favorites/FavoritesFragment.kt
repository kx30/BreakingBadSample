package ru.nikolyashka.breakingbadsample.ui.favorites

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.nikolyashka.breakingbadsample.databinding.FragmentFavoritesBinding
import ru.nikolyashka.breakingbadsample.ui.base.BaseCharacterFragment
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.CharacterAdapter
import ru.nikolyashka.breakingbadsample.ui.favorites.adapter.FavoritesAdapter

@AndroidEntryPoint
class FavoritesFragment : BaseCharacterFragment<FragmentFavoritesBinding>() {

    override val adapter by lazy {
        FavoritesAdapter(this)
    }
    override val viewModel: FavoritesViewModel by viewModels()
    override val recyclerView: RecyclerView
        get() = binding.rvFavorites


    override fun getViewBinding(): FragmentFavoritesBinding = FragmentFavoritesBinding.inflate(layoutInflater)

    override fun openCharacterDetails(characterId: Int) {
        FavoritesFragmentDirections.openCharacterDetailsFragment(characterId)
            .let(findNavController()::navigate)
    }
}