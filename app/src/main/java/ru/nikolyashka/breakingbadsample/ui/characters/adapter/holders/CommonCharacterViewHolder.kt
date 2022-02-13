package ru.nikolyashka.breakingbadsample.ui.characters.adapter.holders

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType

abstract class CommonCharacterViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    open fun onBind(type: CharacterUiType) {}
}