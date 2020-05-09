package mds.mobile.autohunt.shared

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AHCarModelsAPIResponse (
    @SerializedName("carModels")
    @Expose val carModels: ArrayList<AHModelAPIResponse>
)

data class AHModelAPIResponse(
    val brand: String,
    val models: ArrayList<String>
)