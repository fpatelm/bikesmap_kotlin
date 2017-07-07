package com.faizal.bikesmap

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ArrayAdapter
import com.faizal.bikesmap.Model.BikeInfo
import com.faizal.bikesmap.Model.StationReply
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    internal var bikeInfoList: MutableList<BikeInfo> = ArrayList()
    internal lateinit var adapter: Adapter
    internal lateinit var rv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv = findViewById(R.id.rv) as RecyclerView
    }

    fun submit(view: View) {
        val builderSingle = AlertDialog.Builder(this)
        builderSingle.setIcon(R.drawable.logo)
        builderSingle.setTitle("Select One City:-")
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice)

        val prefs = this.getSharedPreferences("bikemap", Context.MODE_PRIVATE)
        val setContract = prefs.getStringSet("ContractList", null) ?: return

        for (item in setContract) {
            arrayAdapter.add(item)
        }

        builderSingle.setNegativeButton("cancel") { dialog, which -> dialog.dismiss() }

        builderSingle.setAdapter(arrayAdapter) { dialog, which ->
            val strName = arrayAdapter.getItem(which)
            val builderInner = AlertDialog.Builder(this@MainActivity)
            builderInner.setMessage(strName)
            builderInner.setTitle("Your Selected City is")
            builderInner.setPositiveButton("Ok") { dialog, which ->
                RestApi.GetStationList(strName, object : RestApi.ICallBackStation {
                    override fun success(response: Response<List<StationReply>>) {
                        //initialise recycleview adapter
                        if (response.body().isNotEmpty()) {
                            bikeInfoList.clear()
                            for (item in response.body()) {
                                bikeInfoList.add(BikeInfo().setName(item.name!!).setNumber(item.number!!).setAddress(item.address!!))
                                adapter = Adapter(bikeInfoList)
                                rv.adapter = adapter
                                rv.layoutManager = LinearLayoutManager(this@MainActivity)
                            }
                        }
                    }

                    override fun error() {

                    }
                }, baseContext)
                dialog.dismiss()
            }
            builderInner.show()
        }
        builderSingle.show()


    }
}
