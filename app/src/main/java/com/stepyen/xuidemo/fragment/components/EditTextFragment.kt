package com.stepyen.xuidemo.fragment.components

import com.stepyen.xuidemo.R
import com.stepyen.xuidemo.base.BaseFragment
import com.xuexiang.xpage.annotation.Page

/**
 * date：2019/10/24
 * author：stepyen
 * description：
 *
 */
@Page(name = "输入框", extra = R.drawable.ic_widget_imageview)
class EditTextFragment : BaseFragment() {
    override fun initViews() {

    }

    override fun getLayoutId(): Int {
      return  R.layout.fragment_edittext
    }


}



