package com.zasa.simpletodo

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var items : ArrayList<String>
    lateinit var itemsAdapter : ArrayAdapter<String>
    lateinit var lvItems : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvItems = findViewById(R.id.lvItems)
        items = ArrayList()
        itemsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        lvItems.adapter = itemsAdapter
        items.add("first item")
        items.add("second item")
        setupListViewListner()
        readItem()

    }

    private fun setupListViewListner() {
        lvItems.setOnItemLongClickListener { adapterView, view, i, l ->
            items.removeAt(i)
            itemsAdapter.notifyDataSetChanged()
            return@setOnItemLongClickListener true
        }
    }


    fun onAddItem(view: View) {
        var etNewItem : EditText = findViewById(R.id.etNewItem)
        var itemText = etNewItem.text.toString()
        itemsAdapter.add(itemText)
        etNewItem.text
    }

    private fun readItem(){
        var filesDir = getFilesDir()
        var todoFile = File(filesDir, "todo.txt")
        try {
            items = ArrayList<String>(org.apache.commons.io.FileUtils.readLines(todoFile))
        }catch (ioException : IOException) {
            ioException.printStackTrace()
        }
    }

    private fun writeItems(){
        var filesDir = getFilesDir()
        var todoFile = File(filesDir, "todo.txt")
        try {
            org.apache.commons.io.FileUtils.writeLines(todoFile, items)
        }catch (ioException : IOException) {
            ioException.printStackTrace()
        }
    }


}