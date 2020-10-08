package com.example.demodatabind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.demodatabind.databinding.ActivityMainBinding
import com.example.demodatabind.viewmodel.GetDataViewModel

class MainActivity : AppCompatActivity() {

    var viewmodel: GetDataViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewmodel = ViewModelProvider(this).get(GetDataViewModel::class.java)
        viewmodel!!.setbinder(binding)
    }

}