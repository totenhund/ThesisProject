package com.example.thesisproject.presentation.map.addDialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.example.thesisproject.R
import com.example.thesisproject.databinding.DialogAddEventBinding
import com.example.thesisproject.presentation.base.dialogs.BaseBottomDialog

class AddEventDialog(context: Context): BaseBottomDialog(context) {

    private lateinit var binding: DialogAddEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_add_event, null, false)
        setContentView(binding.root)
    }

}