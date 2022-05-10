package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        val adapter =  PostAdapter({ viewModel.likeById(it.id) }, { viewModel.shareById(it.id) })

        binding.listOfPosts.adapter = adapter

        viewModel.data.observe(this) { posts ->

            adapter.submitList(posts)

        }

    }

}