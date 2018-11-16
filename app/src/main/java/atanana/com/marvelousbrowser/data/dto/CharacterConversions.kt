package atanana.com.marvelousbrowser.data.dto

import atanana.com.marvelousbrowser.data.web.CharacterResponse

fun CharacterResponse.toCharacter(): Character =
    Character(
        id = id,
        name = name,
        thumbnailUrl = thumbnail.fullPath
    )

fun List<CharacterResponse>.toCharacters(): List<Character> = map(CharacterResponse::toCharacter)