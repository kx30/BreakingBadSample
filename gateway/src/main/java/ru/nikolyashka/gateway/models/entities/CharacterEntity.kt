package ru.nikolyashka.gateway.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.nikolyashka.domain.CharacterType

@Entity
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false) val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
) {
    fun map() = CharacterType.CharacterModel(
        id = this.id.toInt(),
        name = this.name,
        imageUrl = this.imageUrl,
        isFavorite = true
    )

    companion object {
        fun map(characterModel: CharacterType.CharacterModel) = CharacterEntity(
            id = characterModel.id.toLong(),
            name = characterModel.name,
            imageUrl = characterModel.imageUrl
        )
    }
}

