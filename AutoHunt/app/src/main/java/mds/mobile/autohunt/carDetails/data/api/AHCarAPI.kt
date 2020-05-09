package mds.mobile.autohunt.carDetails.data.api

import io.reactivex.Observable
import mds.mobile.autohunt.carDetails.data.api.models.CarsAPIResponse
import mds.mobile.autohunt.home.models.AHCar
import mds.mobile.autohunt.utils.AHConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface AHCarAPI {

    companion object{
        const val PAGE = "page"
        const val SIZE = "size"
    }

    @GET("${AHConstants.BASE_API_URL}cars")
    fun getCars(
        @Query(PAGE) page: Int,
        @Query(SIZE) size: Int
    ): Observable<CarsAPIResponse>

    @GET("${AHConstants.BASE_API_URL}getAllByBrand")
    fun getAllCarsByBrand(@Query("brand") brand: String): Observable<CarsAPIResponse>

    @GET("${AHConstants.BASE_API_URL}setRandomModelsToCarsByBrandType")
    fun getRandomModels(): Observable<AHCar>
}