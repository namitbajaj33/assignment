package com.assignment.model

data class CelebrityCar(
    val age: Int?,
    val height: String?,
    val photo: String,
    val popularity: Int?

)

data class CelebrityCarContainer(
    val name: String,
    val celebrityCar: CelebrityCar,
    val viewType: Int
) {

    companion object {

        const val HEADER = 0
        const val CELEBRITY = 1
        const val CAR = 2

    }

}