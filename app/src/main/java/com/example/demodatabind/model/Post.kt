package com.example.demodatabind.model

data class Post(
//     When call this(https://reqres.in/api/users?page=2) api
    var total: Int,
    var total_pages: Int,
    var data: List<DataFromApi>
)