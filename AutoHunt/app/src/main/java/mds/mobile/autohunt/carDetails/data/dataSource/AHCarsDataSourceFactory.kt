package mds.mobile.autohunt.carDetails.data.dataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import mds.mobile.autohunt.home.models.AHCar

class AHCarsDataSourceFactory(
    val autoDisposable: CompositeDisposable
) : DataSource.Factory<Int, AHCar>() {

    //creating the mutable live data
    //getter for itemlivedatasource
    val itemLiveDataSource = MutableLiveData<AHCarsDataSource>()

    override fun create(): DataSource<Int, AHCar> {
        //getting our data source object
        val itemDataSource = AHCarsDataSource(autoDisposable)

        //posting the datasource to get the values
        itemLiveDataSource.postValue(itemDataSource)

        //returning the datasource
        return itemDataSource
    }

}