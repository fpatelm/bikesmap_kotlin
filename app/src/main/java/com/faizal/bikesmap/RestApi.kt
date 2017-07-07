package com.faizal.bikesmap

import android.content.Context
import android.util.Log
import com.faizal.bikesmap.Model.ContractReply
import com.faizal.bikesmap.Model.StationReply
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * Created by fpatel on 21/05/2017.
 */

object RestApi {

    fun GetContractList(oCallBack: ICallBack, context: Context) {
        Log.d("onResponse: ", "GetContractList")
        //Now use restadapter to create an instance of your interface
        val restService = GetRetrofitService()
        //populate the request parameters
        val queryMap = HashMap<String, String>()
        queryMap.put("apiKey", "38440d36615d46c21c37e5e4cfb487f6a17c9e3c")

        val call = restService.getContractList(queryMap)

        val setContract = HashSet<String>()
        val keyValues = context.getSharedPreferences("bikemap", Context.MODE_PRIVATE)
        val keyValuesEditor = keyValues.edit()


        call.enqueue(object : Callback<List<ContractReply>> {
            override fun onResponse(call: Call<List<ContractReply>>, response: Response<List<ContractReply>>) {

                if (response.body().isNotEmpty()) {
                    for (item in response.body()) {
                        setContract.add(item.name!!)
                    }
                }
                keyValuesEditor.putStringSet("ContractList", setContract)
                keyValuesEditor.commit()
                Log.d("onResponse: ", response.body()[0].name)
                oCallBack.success()
            }

            override fun onFailure(call: Call<List<ContractReply>>, t: Throwable) {
                Log.d("onResponse: ", t.localizedMessage)
                oCallBack.error()
            }
        })

    }

    fun GetStationList(contract: String, oCallBack: ICallBackStation, context: Context) {
        val restService = GetRetrofitService()
        val queryMap = HashMap<String, String>()
        queryMap.put("apiKey", "38440d36615d46c21c37e5e4cfb487f6a17c9e3c")
        queryMap.put("contract", contract)

        val keyValues = context.getSharedPreferences("bikemap", Context.MODE_PRIVATE)
        val keyValuesEditor = keyValues.edit()

        val call = restService.getStationList(queryMap)

        call.enqueue(object : Callback<List<StationReply>> {
            override fun onResponse(call: Call<List<StationReply>>, response: Response<List<StationReply>>) {
                oCallBack.success(response)
            }

            override fun onFailure(call: Call<List<StationReply>>, t: Throwable) {
                oCallBack.error()
            }
        })

    }

    interface ICallBack {
        fun success()
        fun error()

    }

    interface ICallBackStation {
        fun success(response: Response<List<StationReply>>)
        fun error()

    }

    private fun GetRetrofitService(): RestService {
        return Retrofit.Builder()
                .baseUrl("https://api.jcdecaux.com")
                .client(Utils.GetRequestHeader())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RestService::class.java)
    }
}
