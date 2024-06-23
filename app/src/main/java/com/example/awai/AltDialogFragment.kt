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

class AltDialogFragment : DialogFragment() {
    var id: String? = null
    var name: String? = null
    var address: String? = null
    var phone: String? = null
    var email: String? = null
    var relations: String? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context: Context? = getActivity()
        val dbHelper = context?.let { DatabaseHelper(it) }
        val builder = AlertDialog.Builder(context)
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_alt, null)

        val et_name = view.findViewById<EditText>(R.id.et_name)
        val et_address = view.findViewById<EditText>(R.id.et_address)
        val et_phone = view.findViewById<EditText>(R.id.et_phone)
        val et_email = view.findViewById<EditText>(R.id.et_email)
        val et_relations = view.findViewById<EditText>(R.id.et_relations)

        address = "$address"
        id = "$id"
        name = "$name"
        phone = "$phone"
        email = "$email"
        relations = "$relations"
        et_name.setText(name)
        et_address.setText(address)
        et_phone.setText(phone)
        et_email.setText(email)
        et_relations.setText(relations)

        view.findViewById<Button>(R.id.bt_submit).setOnClickListener {
            name = et_name.text.toString()
            address = et_address.text.toString()
            phone = et_phone.text.toString()
            email = et_email.text.toString()
            relations = et_relations.text.toString()
            try {
                if (name.equals("") || address.equals("") || phone.equals("") || email.equals("") || relations.equals("")) {
                    Toast.makeText(context, "信息不完整", Toast.LENGTH_LONG).show()
                } else {
                    if (dbHelper != null) {
                        dbHelper.updateRecord(id!!,name!!, address!!, phone!!, email!!, relations!!)
                    }
                    Toast.makeText(context, "保存成功", Toast.LENGTH_LONG).show()
                    dismiss()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        view.findViewById<Button>(R.id.bt_delete).setOnClickListener {
            if (dbHelper != null) {
                id?.let { it1 -> dbHelper.deleteRecord(it1) }
            }
            Toast.makeText(context, "删除成功", Toast.LENGTH_LONG).show()
            dismiss()
        }
        builder.setView(view)
        return builder.create()
    }
}