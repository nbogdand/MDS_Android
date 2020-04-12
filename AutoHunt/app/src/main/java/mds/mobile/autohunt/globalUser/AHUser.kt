package mds.mobile.autohunt.globalUser

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AHUser (
    @SerializedName("id")
    @Expose val id: String,

    @SerializedName("name")
    @Expose val name: String,

    @SerializedName("email")
    @Expose val email: String
)