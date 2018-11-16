package atanana.com.marvelousbrowser.data.dto

import atanana.com.marvelousbrowser.data.room.CharacterEntity
import atanana.com.marvelousbrowser.data.web.CharacterResponse

fun CharacterResponse.toCharacter(): Character =
    Character(
        id = id,
        name = name,
        thumbnailUrl = thumbnail.fullPath
    )

fun List<CharacterResponse>.toCharacters(): List<Character> = map(CharacterResponse::toCharacter)

fun Character.toEntity(): CharacterEntity =
    CharacterEntity(
        id = id,
        name = name,
        thumbnailUrl = thumbnailUrl
    )

fun List<Character>.toEntities(): List<CharacterEntity> = map(Character::toEntity)