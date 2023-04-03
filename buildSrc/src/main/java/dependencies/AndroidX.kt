package dependencies

object AndroidX {

  const val app_compat = "androidx.appcompat:appcompat:${Versions.app_compat}"
  const val ui_tooling = "androidx.ui:ui-tooling:${Versions.androidx_ui}"

  const val compose_material = "androidx.compose.material:material:${Versions.compose}"
  const val compose_icons_core = "androidx.compose.material:material-icons-core:${Versions.compose}"
  const val compose_icons_extended = "androidx.compose.material:material-icons-extended:${Versions.compose}"

  const val navigation_compose = "androidx.navigation:navigation-compose:${Versions.nav_compose}"
  const val constraint_compose = "androidx.constraintlayout:constraintlayout-compose:${Versions.compose_constraint}"
  const val navigation_hilt = "androidx.hilt:hilt-navigation:${Versions.hilt_navigation}"

  const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
}