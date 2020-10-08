package com.example.demodatabind.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.demodatabind.model.Data
import com.example.demodatabind.databinding.ActivityMainBinding
import com.example.demodatabind.model.DataFromApi
import com.example.demodatabind.model.Post
import com.example.demodatabind.model.Post1
import com.example.demodatabind.modelForJsonPlaceHolderApi.PostData
import com.example.demodatabind.repository.Repository

class GetDataViewModel(application: Application) : BaseViewModel(application)  {
    lateinit var binder:ActivityMainBinding
    lateinit var context: Context
    lateinit var data: Data
    lateinit var apiData: DataFromApi
    lateinit var apidata1: DataFromApi
    var enterText: String? = null
    var myResponse: MutableLiveData<Post> = MutableLiveData<Post>()
    var myResponse1: MutableLiveData<Post1> = MutableLiveData<Post1>()
    var myResponse2: MutableLiveData<Post> = MutableLiveData<Post>()
    var myResponse3: MutableLiveData<List<PostData>> = MutableLiveData<List<PostData>>()
    var myResponse4: MutableLiveData<PostData> = MutableLiveData<PostData>()

    fun setbinder(binder: ActivityMainBinding){
        data = Data()
        apiData = DataFromApi()
        this.binder = binder
        this.context =binder.root.context
        binder.viewmodel = this
        binder.viewClickHandler = ViewClickHandler()
        binder.executePendingBindings()
        init()
    }

    private fun init(){

    }

    inner class ViewClickHandler{
        lateinit var  dataM: Data
        fun onBtnClick(view: View){
            dataM = Data()
            if(enterText.isNullOrEmpty()){
                dataM.enterText = "Empty"
            } else{
                dataM.enterText = enterText
            }
            data = dataM
            binder.data = data
            getPost()
            var pageNo = Integer.parseInt(enterText.toString())
            getPost1(pageNo)
            getCustomPage(pageNo)
            getCustomPage2(pageNo,"id","desc")
            var options: HashMap<String,String> = HashMap()
            options.put("_sort","id")
            options.put("_order","desc")
            getCustomPage1(pageNo,options)
            var postdata: PostData = PostData(2,150,"kartum","kartum@gmail.com","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
            pushPost(postdata)
        }
    }

    fun getPost(){
        var dataTemp = DataFromApi()
        myResponse = Repository().getPost()
        myResponse.observeForever {
            var data: MutableList<DataFromApi> = it.data as MutableList<DataFromApi>
            dataTemp = data[0]
            apiData = dataTemp
            binder.apidata = apiData
        }
    }

    fun getPost1(number: Int){
        myResponse1 = Repository().getPost1(number)
        myResponse1.observeForever {
            var data1 = it.data
            apidata1 = data1
            binder.apidata1 = apidata1
        }
    }

    fun getCustomPage(pageNo: Int){
//        var dataTemp = DataFromApi()
        var dataT: Post
        myResponse2 = Repository().getCustompage(pageNo)
        myResponse2.observeForever {
            dataT = it as Post
//            var data: MutableList<DataFromApi> = it.data as MutableList<DataFromApi>
//            if(data.size > 0) {
//                dataTemp = data[0]
//            }
            binder.custompage = dataT
        }
    }

    fun getCustomPage2(postId: Int,sort: String,order: String){
        var postData: List<PostData>
        myResponse3 = Repository().getCustomPage2(postId,sort,order)
        myResponse3.observeForever {
            postData = it as List<PostData>
            binder.custompage2 = postData[0]
            Log.d("Post Data: ",postData.toString())
        }
    }

    fun getCustomPage1(postId: Int,options: Map<String, String>){
        var postData: List<PostData>
        myResponse3 = Repository().getCustomPage1(postId,options)
        myResponse3.observeForever {
            postData = it as List<PostData>
            binder.custompage1 = postData[0]
            Log.d("Post Data: ",postData.toString())
        }
    }

    fun pushPost(post: PostData){
        myResponse4 = Repository().pushPost(post)
        myResponse4.observeForever {
            Log.d("IT",it.toString())
            Toast.makeText(context,it.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    
}