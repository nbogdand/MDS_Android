package mds.mobile.autohunt.authentication.data.models

import mds.mobile.autohunt.globalUser.AHUser
import mds.mobile.autohunt.shared.AHModelAPIResponse

data class AHLoginAPIResponse(
    val user: AHUser,
    val brandTypes: ArrayList<String>,
    val carModels: ArrayList<AHModelAPIResponse>
)