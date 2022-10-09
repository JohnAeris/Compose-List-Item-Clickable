package com.example.clickablelazygrid

sealed class Screen(val route: String) {
    object HeroesGridLayout: Screen("Hhome")
    object HeroDetails: Screen("hero_detail")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
