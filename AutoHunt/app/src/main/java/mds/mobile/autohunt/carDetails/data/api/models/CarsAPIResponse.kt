package mds.mobile.autohunt.carDetails.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import mds.mobile.autohunt.home.models.AHCar

data class CarsAPIResponse (
    @SerializedName("content")
    @Expose val content: ArrayList<AHCar>
)