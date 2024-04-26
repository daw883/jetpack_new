package com.example.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private val _counterList: MutableLiveData<ArrayList<Double>> = MutableLiveData()

    init {
        Log.e("shun","init!!!")
        _counterList.value = ArrayList()
    }

    fun addNewCounter(newCounter:Double) {
        val list = _counterList.value ?: ArrayList()
        list.add(newCounter)
        _counterList.value = list
    }

    fun getCounterList(): ArrayList<Double> {
        Log.e("shun","list.size = ${_counterList.value?.size}")
        return _counterList.value!!
    }
}