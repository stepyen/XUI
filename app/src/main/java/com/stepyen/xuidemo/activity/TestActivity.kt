package com.stepyen.xuidemo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stepyen.xui.utils.ResUtils
import com.stepyen.xui.utils.TextViewCountTimeHelp
import com.stepyen.xui.widget.spinner.MaterialSpinner
import com.stepyen.xuidemo.R
import com.stepyen.xutil.tip.ToastUtils
import kotlinx.android.synthetic.main.activity_test.*

/**
 * date：2022/12/5
 * author：stepyen
 * description：
 *
 */
class TestActivity  : AppCompatActivity() {

    var textViewCountTimeHelp: TextViewCountTimeHelp?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

//        spinner1
//            .setItems<String>(*ResUtils.getStringArray(R.array.sort_mode_entry))
//        spinner1
//            .setArrowColor(ResUtils.getColor(R.color.xui_config_color_red))
//        spinner1
//            .setOnItemSelectedListener(object : MaterialSpinner.OnItemSelectedListener<Any?> {
//
//            override fun onItemSelected(
//                view: MaterialSpinner?,
//                position: Int,
//                id: Long,
//                item: Any?
//            ) {
//            }
//        })



        testTv.setOnClickListener {
            textViewCountTimeHelp = TextViewCountTimeHelp(testTv,5000,1000)
            textViewCountTimeHelp?.start()
        }

    }




}