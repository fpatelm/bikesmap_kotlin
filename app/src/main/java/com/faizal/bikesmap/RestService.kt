package com.faizal.bikesmap

import com.faizal.bikesmap.Model.ContractReply
import com.faizal.bikesmap.Model.StationReply

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by fpatel on 21/05/2017.
 */

interface RestService {
    @GET("/vls/v1/contracts")
    fun getContractList(@QueryMap options: Map<String, String>): Call<List<ContractReply>>

    @GET("/vls/v1/stations")
    fun getStationList(@QueryMap options: Map<String, String>): Call<List<StationReply>>
}

