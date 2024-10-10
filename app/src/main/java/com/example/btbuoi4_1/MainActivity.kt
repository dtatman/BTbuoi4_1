package com.example.btbuoi4_1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: TaskDatabaseHelper
    private lateinit var listView: ListView
    private val taskList = ArrayList<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        dbHelper = TaskDatabaseHelper(this)

        try {
            dbHelper.createDatabase()

            loadTasks()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun loadTasks() {
        taskList.clear()
        taskList.addAll(dbHelper.getAllTasks())

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList.map { it.name })
        listView.adapter = adapter
    }

}
