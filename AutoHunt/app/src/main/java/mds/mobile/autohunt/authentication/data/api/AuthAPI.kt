package mds.mobile.autohunt.authentication.data.api

import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.Single
import mds.mobile.autohunt.authentication.data.models.AHLoginAPIForm
import mds.mobile.autohunt.authentication.data.models.AHLoginAPIResponse
import mds.mobile.autohunt.authentication.data.models.AHRegisterAPIForm
import mds.mobile.autohunt.globalUser.AHUser
import mds.mobile.autohunt.utils.AHConstants
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthAPI {

    @POST("${AHConstants.BASE_API_URL}user/login")
    fun loginUser(@Body loginForm: AHLoginAPIForm): Observable<AHLoginAPIResponse?>

    @POST("${AHConstants.BASE_API_URL}user/register")
    fun registerUser(@Body registerForm: AHRegisterAPIForm): Observable<AHUser?>
}