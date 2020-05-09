package mds.mobile.autohunt.carDetails.data.dataSource

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.reactivex.disposables.CompositeDisposable
import mds.mobile.autohunt.authentication.data.client.AHAPIClient
import mds.mobile.autohunt.carDetails.data.repository.AHCarAPIRepository
import mds.mobile.autohunt.home.models.AHCar
import mds.mobile.autohunt.utils.logErrorMessage
import mds.mobile.autohunt.utils.provideInfo
import mds.mobile.autohunt.utils.showToastMessage
import java.util.concurrent.TimeUnit

class AHCarsDataSource(
    val autoDisposable: CompositeDisposable
) :
    PageKeyedDataSource<Int, AHCar>() {

    val state = MutableLiveData<PaginatedListNetworkingState>()

    //this will be called once to load the initial data
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, AHCar>
    ) {
        val disposable = AHCarAPIRepository.getCars(
            page = FIRST_PAGE,
            size = PAGE_SIZE
        )
            .delay(AHAPIClient.ON_START_DELAY, TimeUnit.MILLISECONDS)
            .doOnSubscribe {
                updateState(PaginatedListNetworkingState.LOADING)
            }
            .subscribe(
                { response ->
                    updateState(PaginatedListNetworkingState.DONE)

                    callback.onResult(response.content, null, FIRST_PAGE + 1)
                },
                { throwable ->
                    "Get cars List loadInitial - error: ${throwable.message}".provideInfo()
                    updateState(PaginatedListNetworkingState.ERROR)
                }
            )

        updateState(PaginatedListNetworkingState.DONE)
        autoDisposable.add(disposable)
    }

    @SuppressLint("LogNotTimber")
    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, AHCar>
    ) {
        val disposable = AHCarAPIRepository.getCars(
            page = params.key,
            size = PAGE_SIZE
        )
            .doOnSubscribe {
                updateState(PaginatedListNetworkingState.LOADING)
            }
            .subscribe(
                { response ->
                    updateState(PaginatedListNetworkingState.DONE)

                    callback.onResult(response.content, params.key + 1)
                },
                { throwable ->
                    "Cars List loadAfter - error: ${throwable.message}".provideInfo()
                    updateState(PaginatedListNetworkingState.ERROR)
                }
            )

        updateState(PaginatedListNetworkingState.DONE)
        autoDisposable.add(disposable)
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, AHCar>
    ) {

    }


    private fun updateState(state: PaginatedListNetworkingState) {
        this.state.postValue(state)
    }

    companion object {
        //the size of a page that we want
        const val PAGE_SIZE = 10

        //we will start from the first page which is 0
        const val FIRST_PAGE = 0
    }
}

enum class PaginatedListNetworkingState {
    LOADING,
    DONE,
    ERROR
}