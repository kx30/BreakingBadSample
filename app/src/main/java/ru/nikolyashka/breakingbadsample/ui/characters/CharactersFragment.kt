package ru.nikolyashka.breakingbadsample.ui.characters

import androidx.fragment.app.viewModels
import ru.nikolyashka.breakingbadsample.databinding.FragmentCharactersBinding
import ru.nikolyashka.breakingbadsample.ui.base.BaseFragment

class CharactersFragment : BaseFragment<FragmentCharactersBinding>() {

    override val viewModel: CharactersViewModel by viewModels()


    override fun getViewBinding(): FragmentCharactersBinding = FragmentCharactersBinding.inflate(layoutInflater)
}