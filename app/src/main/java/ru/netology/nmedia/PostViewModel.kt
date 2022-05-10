package ru.netology.nmedia

import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel() {

    private val repository : PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun likeById(id:Int) = repository.likeById(id)
    fun shareById(id:Int) = repository.shareById(id)


}