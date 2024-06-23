package com.example.awai

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment

class ContactManagementFragment : Fragment(R.layout.fragment_contact_management) {

    var dataList: ArrayList<HashMap<String, String>> = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listView = view.findViewById<ListView>(R.id.lv)
        val dialog = InputDialogFragment()
        val context: Context? = getActivity()
        val altDialog = AltDialogFragment()
        val dbHelper = context?.let { DatabaseHelper(it) }
        getData()
        val adapter = activity?.let { MyCustomAdapter(it, dataList) }
        listView.adapter = adapter
        listView.setOnItemClickListener { adapterView, view, i, l ->
            altDialog.id = dataList.get(i).get("id")
            altDialog.name = dataList.get(i).get("name")
            altDialog.address = dataList.get(i).get("address")
            altDialog.email = dataList.get(i).get("email")
            altDialog.phone = dataList.get(i).get("phone")
            altDialog.relations = dataList.get(i).get("relations")
            fragmentManager
                ?.let { altDialog.show(it,"DialogFragment") }
            getData()
            val adapter = activity?.let { MyCustomAdapter(it, dataList) }
            listView.adapter = adapter
        }
        val addBtn = view.findViewById<Button>(R.id.addBtn)
        addBtn.setOnClickListener{
            fragmentManager?.let { it1 -> dialog.show(it1,"DialogFragment") }
        }
    }


    class MyCustomAdapter(
        private val context: Context,
        private val data: ArrayList<HashMap<String, String>>
    ) : BaseAdapter() {
        // return data count
        override fun getCount(): Int {
            return data.size
        }

        // return item data
        override fun getItem(position: Int): HashMap<String, String> {
            return data[position]
        }

        // return item id
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        // 创建每个数据项的视图
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            // 使用布局填充器从布局资源文件创建视图
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val itemView = inflater.inflate(R.layout.item, parent, false)
            // 获取该位置的数据项
            val itemData = getItem(position) as HashMap<String, String>
            // 在视图中找到相关的控件
            val tv_name = itemView.findViewById<TextView>(R.id.tv_name)
            // 设置控件显示数据项的内容
            tv_name.text = itemData.get("name").toString()
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
            // Create a hashmap instance
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