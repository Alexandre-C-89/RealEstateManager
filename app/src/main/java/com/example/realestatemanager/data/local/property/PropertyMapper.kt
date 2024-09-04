package com.example.realestatemanager.data.local.property

import android.graphics.Movie

fun PropertyDto.toPropertyEntity(
): PropertyEntity {
    return PropertyEntity(
        id = id,
        image = IMAGE_BASE_URL + poster_path,
        title = title
    )
}

fun MovieEntity.toMovie(
): Movie {
    return Movie(
        id = id,
        image = IMAGE_BASE_URL + image,
        title = title
    )
}