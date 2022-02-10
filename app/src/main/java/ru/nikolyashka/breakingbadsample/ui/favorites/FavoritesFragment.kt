package ru.nikolyashka.breakingbadsample.ui.favorites

import androidx.fragment.app.viewModels
import ru.nikolyashka.breakingbadsample.databinding.FragmentFavoritesBinding
import ru.nikolyashka.breakingbadsample.ui.base.BaseFragment

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>() {

    override val viewModel: FavoritesViewModel by viewModels()

    override fun getViewBinding(): FragmentFavoritesBinding = FragmentFavoritesBinding.inflate(layoutInflater)
}