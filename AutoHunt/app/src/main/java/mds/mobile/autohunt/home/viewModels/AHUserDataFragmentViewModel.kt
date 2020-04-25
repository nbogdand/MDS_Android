package mds.mobile.autohunt.home.viewModels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import mds.mobile.autohunt.globalUser.AHCurrentUser

class AHUserDataFragmentViewModel: ViewModel() {
    val isEditable = ObservableField(false)

    val name = ObservableField<String>(AHCurrentUser.user?.name)
    val email = ObservableField<String>(AHCurrentUser.user?.email)
    val phone = ObservableField<String>()

    fun onEditClick() {
//        isEditable.get()?.let { editable ->
//            isEditable.set(!editable)
//        }
        when (isEditable.get()) {
            false -> isEditable.set(true)
            true -> {
                AHCurrentUser.updateUser(
                    name = name.get(),
                    email = email.get(),
                    phone = phone.get()
                )
                isEditable.set(false)
            }
        }
    }
}