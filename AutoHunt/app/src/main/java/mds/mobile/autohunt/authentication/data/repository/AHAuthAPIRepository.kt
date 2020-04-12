package mds.mobile.autohunt.authentication.data.repository

import mds.mobile.autohunt.authentication.data.api.AuthAPI
import mds.mobile.autohunt.authentication.data.client.AHAuthClient
import mds.mobile.autohunt.authentication.data.models.AHLoginAPIForm
import mds.mobile.autohunt.authentication.data.models.AHRegisterAPIForm

object AHAuthAPIRepository {
    private val authAPI by lazy {
        AHAuthClient.retrofitClient.create(AuthAPI::class.java)
    }

    fun loginUser(email: String, password: String) =
        authAPI.loginUser(AHLoginAPIForm(
            email = email,
            password = password
        ))

    fun registerUser(email: String, name: String, password: String) =
        authAPI.registerUser(AHRegisterAPIForm(
            email = email,
            name = name,
            password = password
        ))
}