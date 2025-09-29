package com.example.cupcakeapp.data

import com.example.cupcakeapp.R

object DataSource {
    val flavors = listOf(
        R.string.vanilla,
        R.string.chocolate,
        R.string.red_velvet,
        R.string.salted_caramel,
        R.string.coffee
    )

    val quantityOptions = listOf(
        Pair(R.string.one_cupcake, 1),
        Pair(R.string.six_cupcakes, 6),
        Pair(R.string.twelve_cupcakes, 12)
    )
    val pickupOptions = listOf(
        R.string.pickup_today,
        R.string.pickup_tomorrow,
        R.string.pickup_next_day
    )
}
