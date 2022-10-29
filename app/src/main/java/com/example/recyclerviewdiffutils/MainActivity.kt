package com.example.recyclerviewdiffutils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewdiffutils.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val userAdapter by lazy { UserListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter.differ.submitList(loadData())
        binding.apply {
            rvMain.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = userAdapter
            }
        }
    }

    private fun loadData(): MutableList<UserModel>? {
        val nameList: MutableList<UserModel> = mutableListOf()

        for (i in 1..10) {
            nameList.add(UserModel(i, "User #$i"))
        }

        return nameList
    }
}