package ru.nikolyashka.breakingbadsample.ui.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ru.nikolyashka.breakingbadsample.R
import ru.nikolyashka.breakingbadsample.databinding.FragmentCharacterDetailsBinding
import ru.nikolyashka.breakingbadsample.ui.base.BaseFragment
import ru.nikolyashka.breakingbadsample.ui.details.models.CharacterDetailsUiType

@AndroidEntryPoint
class CharacterDetailsFragment : BaseFragment<FragmentCharacterDetailsBinding>() {

    override val viewModel: CharacterDetailsViewModel by viewModels()
    private val args: CharacterDetailsFragmentArgs by navArgs()


    override fun getViewBinding(): FragmentCharacterDetailsBinding =
        FragmentCharacterDetailsBinding.inflate(layoutInflater)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.loadCharacterDetails(args.characterId)
        }

        setListeners()
        observeData()
    }

    private fun observeData() {
        viewModel.isLoading.observe(this, { isLoading ->
            binding.pbCharacterDetails.isVisible = isLoading
        })

        viewModel.characterDetails.observe(this, { uiType ->
            with(binding) {
                when (uiType) {
                    is CharacterDetailsUiType.CharacterDetailsUiModel -> {
                        groupCharacterDetailsError.isVisible = false
                        tvCharacterDetailsBirthday.text = getString(R.string.character_birthday, uiType.birthday)
                        tvCharacterDetailsNickname.text =
                            getString(R.string.character_nickname, uiType.name, uiType.nickname)
                        Glide.with(requireContext())
                            .load(uiType.imageUrl)
                            .into(ivCharacterDetails)
                    }
                    is CharacterDetailsUiType.CharacterDetailsUiError -> {
                        groupCharacterDetailsError.isVisible = true
                        tvCharacterDetailsErrorPlaceholder.text = getString(uiType.error)
                    }
                }
            }
        })
    }

    private fun setListeners() {
        with(binding) {
            btnCharacterDetailsTryAgain.setOnClickListener {
                viewModel.loadCharacterDetails(args.characterId)
            }
        }
    }
}