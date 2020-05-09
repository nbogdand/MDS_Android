package mds.mobile.autohunt.carDetails.data.repository

import mds.mobile.autohunt.authentication.data.client.AHAPIClient
import mds.mobile.autohunt.carDetails.data.api.AHCarAPI

object AHCarAPIRepository {
    private val carApi: AHCarAPI by lazy {
        AHAPIClient.retrofitClient.create(AHCarAPI::class.java)
    }

    fun getCars(page: Int, size: Int) =
        carApi.getCars(page, size)

    fun getAllCarsByBrand(brand: String) =
        carApi.getAllCarsByBrand(brand)

    fun getCarDetails(id: Int) =
        carApi.getCarDetails(id)
}