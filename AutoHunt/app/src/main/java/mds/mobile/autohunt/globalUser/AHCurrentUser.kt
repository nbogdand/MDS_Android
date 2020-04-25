package mds.mobile.autohunt.globalUser

object AHCurrentUser {
    var user: AHUser? = null

    fun updateUser(
        name: String? = null,
        email: String? = null,
        phone: String? = null
    ) {
        user?.let {
            val newUser = AHUser(
                id = it.id,
                name = name ?: it.name,
                email = email ?: it.email
            )
            user = newUser
        }
    }
}