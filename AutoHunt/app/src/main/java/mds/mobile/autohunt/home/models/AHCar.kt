package mds.mobile.autohunt.home.models

data class AHCar (
    val id: String? = null,
    val brand: String? = null,
    val model: String? = null,
    val price: Int? = null,
    val year: Int? = null,
    val kilometers: Int? = null,
    val fuelType: String? = null,
    val imageUrl: String? = "https://s1.cdn.autoevolution.com/images/gallery/TOYOTAAvensis-4185_5.jpg"
) {
    companion object {
        fun getPlaceholder() =
            AHCar(
                id = "0001",
                brand = "Toyota",
                model = "Camry",
                price = 10000,
                year = 2012,
                kilometers = 125000,
                fuelType = "Gasoline",
                imageUrl = "https://s1.cdn.autoevolution.com/images/gallery/TOYOTAAvensis-4185_5.jpg"
            )
    }
}