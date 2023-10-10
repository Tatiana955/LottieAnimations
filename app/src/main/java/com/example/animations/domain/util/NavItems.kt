package com.example.animations.domain.util

import com.example.animations.R

enum class NavItems(val icon: Int, val route: String, val description: String) {
    Home(icon = R.drawable.baseline_home_24, route = "home", description = "Home"),
    List(icon = R.drawable.baseline_format_list_bulleted_24, route = "list", description = "List"),
    Chameleon(icon = R.drawable.ic_chameleon, route = "chameleon", description = "Chameleon"),

    LazyColumn(icon = R.drawable.baseline_table_rows_24, route = "lazy_column", description = "Lazy Column"),
    LazyRow(icon = R.drawable.baseline_view_column_24, route = "lazy_row", description = "Lazy Row"),
    LazyGrid(icon = R.drawable.baseline_grid_view_24, route = "lazy_grid", description = "Lazy Grid"),
}
