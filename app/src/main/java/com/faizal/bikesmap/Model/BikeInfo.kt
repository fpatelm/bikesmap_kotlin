package com.faizal.bikesmap.Model

/**
 * Created by fpatel on 21/05/2017.
 */

class BikeInfo {

    internal lateinit var number: String
    internal lateinit var name: String
    internal lateinit var address: String

    fun getNumber(): String {
        return number
    }

    fun setNumber(number: String): BikeInfo {
        this.number = number
        return this
    }

    fun getName(): String {
        return name
    }

    fun setName(name: String): BikeInfo {
        this.name = name
        return this
    }

    fun getAddress(): String {
        return address
    }

    fun setAddress(address: String): BikeInfo {
        this.address = address
        return this
    }
}
