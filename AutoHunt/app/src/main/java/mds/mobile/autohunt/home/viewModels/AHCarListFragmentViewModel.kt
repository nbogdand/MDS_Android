package mds.mobile.autohunt.home.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import mds.mobile.autohunt.carDetails.data.dataSource.AHCarsDataSource
import mds.mobile.autohunt.carDetails.data.dataSource.AHCarsDataSourceFactory
import mds.mobile.autohunt.home.models.AHCar

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
}