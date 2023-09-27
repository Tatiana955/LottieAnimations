package com.example.animations.domain.util

import com.example.animations.R

enum class NavItems(val icon: Int, val route: String, val description: String) {
    Home(icon = R.drawable.baseline_home_24, route = "home", description = "Home"),
    List(icon = R.drawable.baseline_format_list_bulleted_24, route = "list", description = "List"),
    Chameleon(icon = R.drawable.ic_chameleon, route = "chameleon", description = "Chameleon"),
    LazyList(icon = R.drawable.baseline_list_alt_24, route = "lazy_list", description = "Lazy List")
}
