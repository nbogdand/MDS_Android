package mds.mobile.autohunt.home.models

data class AHCar (
    val brand: String? = null,
    val price: Int? = null,
    val year: Int? = null,
    val kilometers: Int? = null,
    val fuelType: String? = null,
    val imageUrl: String? = null
) {
    companion object {
        fun getPlaceholder() =
            AHCar(
                brand = "Toyota",
                price = 10000,
                year = 2012,
                kilometers = 125000,
                fuelType = "Gasoline",
                imageUrl = "https://s1.cdn.autoevolution.com/images/gallery/TOYOTAAvensis-4185_5.jpg"
            )
    }
}