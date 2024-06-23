package com.example.awai

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ContactFragment : Fragment(R.layout.fragment_contact) {
    var dataList: ArrayList<HashMap<String, String>> = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listView = view.findViewById<ListView>(R.id.lv)
        getData()
        val adapter = activity?.let { MyCustomAdapter(it, dataList) }
        listView.adapter = adapter
        // Should add a return to the menu button

        val sendBtn = view.findViewById<Button>(R.id.sendBtn)

        sendBtn.setOnClickListener {
            findNavController().navigate(R.id.action_contactFragment_to_callingFragment)
        }

    }


    class MyCustomAdapter(
        private val context: Context,
        private val data: ArrayList<HashMap<String, String>>
    ) : BaseAdapter() {
        // 返回数据项数量
        override fun getCount(): Int {
            return data.size
        }

        // 返回指定位置的数据项
        override fun getItem(position: Int): HashMap<String, String> {
            return data[position]
        }

        // 返回指定位置的数据项ID
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        // 创建每个数据项的视图
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            // 使用布局填充器从布局资源文件创建视图
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val itemView = inflater.inflate(R.layout.item_contact, parent, false)
            // 获取该位置的数据项
            val itemData = getItem(position) as HashMap<String, String>
            // 在视图中找到相关的控件
            val cb = itemView.findViewById<TextView>(R.id.cb)
            // 设置控件显示数据项的内容
            cb.text = itemData.get("name").toString()
            return itemView
        }
    }

    fun getData() {
        val context: Context? = getActivity()
        val dbHelper = context?.let { DatabaseHelper(it) }
        val res = dbHelper!!.allData
        dataList.clear()
        while (res.moveToNext()) {
            // 创建一个 HashMap 对象，并填充数据
            val dataHashMap1 = HashMap<String, String>()
            dataHashMap1["id"] = res.getString(0)
            dataHashMap1["name"] = res.getString(1)
            dataHashMap1["address"] = res.getString(2)
            dataHashMap1["phone"] = res.getString(3)
            dataHashMap1["email"] = res.getString(4)
            dataHashMap1["relations"] = res.getString(5)
            dataList.add(dataHashMap1)
        }
    }
}