package com.example.awai

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class InputDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context: Context? = getActivity()
        val dbHelper = context?.let { DatabaseHelper(it) }
        val builder = AlertDialog.Builder(context)
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_input, null)

        val et_name = view.findViewById<EditText>(R.id.et_name)
        val et_address = view.findViewById<EditText>(R.id.et_address)
        val et_phone = view.findViewById<EditText>(R.id.et_phone)
        val et_email = view.findViewById<EditText>(R.id.et_email)
        val et_relations = view.findViewById<EditText>(R.id.et_relations)

        view.findViewById<Button>(R.id.bt_submit).setOnClickListener {
            val name = et_name.text.toString()
            val address = et_address.text.toString()
            val phone = et_phone.text.toString()
            val email = et_email.text.toString()
            val relations = et_relations.text.toString()
            try {
                if (name.equals("") || address.equals("") || phone.equals("") || email.equals("") || relations.equals("")) {
                    Toast.makeText(context, "信息不完整", Toast.LENGTH_LONG).show()
                } else {
                    if (dbHelper != null) {
                        dbHelper.insertRecord(name,address,phone,email,relations)
                    }
                    Toast.makeText(context, "保存成功", Toast.LENGTH_LONG).show()
                    dismiss()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        builder.setView(view)
        return builder.create()
    }
}