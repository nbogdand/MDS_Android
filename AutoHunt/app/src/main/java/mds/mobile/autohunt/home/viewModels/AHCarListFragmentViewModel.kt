package mds.mobile.autohunt.home.viewModels

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.operators.single.SingleDoOnSuccess
import io.reactivex.schedulers.Schedulers
import mds.mobile.autohunt.carDetails.data.dataSource.AHCarsDataSource
import mds.mobile.autohunt.carDetails.data.dataSource.AHCarsDataSourceFactory
import mds.mobile.autohunt.carDetails.data.repository.AHCarAPIRepository
import mds.mobile.autohunt.home.models.AHCar
import mds.mobile.autohunt.utils.provideInfo

class AHCarListFragmentViewModel(
//    autoDisposable: CompositeDisposable
): ViewModel() {

    //creating livedata for PagedList  and PagedKeyedDataSource
    var itemPagedList: LiveData<PagedList<AHCar>>
    var liveDataSource: LiveData<AHCarsDataSource>

    //constructor
    init {
        //getting our data source factory
        val itemDataSourceFactory = AHCarsDataSourceFactory(CompositeDisposable())

        //getting the live data source from data source factory
        liveDataSource = itemDataSourceFactory.itemLiveDataSource

        //Getting PagedList config
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(AHCarsDataSource.PAGE_SIZE).build()

        //Building the paged list
        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, pagedListConfig).build()
    }

    fun refreshList() {
        liveDataSource.value?.invalidate()
    }

    fun getAllCarByBrand(
        brand: String,
        onSuccess: (ArrayList<AHCar>) -> Unit
    ) {
        val disposable = AHCarAPIRepository.getAllCarsByBrand(
            brand = brand
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                onSuccess.invoke(response)
            }, { error ->
                "Get car by brand error:${error}".provideInfo()
            })
    }
}