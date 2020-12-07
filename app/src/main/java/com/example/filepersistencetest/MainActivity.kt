package com.example.filepersistencetest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //点击按钮实现数据提交保存
        submit.setOnClickListener {
            val inputText = editText.text.toString()
            save(inputText)
            update()
        }
    }

    private fun update() {
        if(load() != null){
            textView.text = null
            textView.text = load()
        }
    }
//数据本地化保存
    private fun save(inputText : String){
        try {
            val outPut = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(outPut))
            writer.use {
                it.write(inputText)
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    private fun load() : String?{
        val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        }catch (e : IOException){
            e.printStackTrace()
        }
        return content.toString()
    }
}