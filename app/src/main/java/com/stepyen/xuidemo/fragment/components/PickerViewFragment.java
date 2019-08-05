package com.stepyen.xuidemo.fragment.components;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stepyen.xui.utils.DensityUtils;
import com.stepyen.xui.utils.shape.ShapeBuilder;
import com.stepyen.xui.widget.dialog.BottomMenuDialog;
import com.stepyen.xui.widget.dialog.ListDialog;
import com.stepyen.xui.widget.dialog.MiniLoadingDialog;
import com.stepyen.xui.widget.picker.NumberPicker;
import com.stepyen.xui.widget.picker.widget.OptionsPickerView;
import com.stepyen.xui.widget.picker.widget.TimePickerView;
import com.stepyen.xui.widget.picker.widget.builder.OptionsPickerBuilder;
import com.stepyen.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.stepyen.xui.widget.picker.widget.configure.TimePickerType;
import com.stepyen.xui.widget.picker.widget.listener.OnOptionsSelectListener;
import com.stepyen.xui.widget.picker.widget.listener.OnTimeSelectChangeListener;
import com.stepyen.xui.widget.picker.widget.listener.OnTimeSelectListener;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseTestFragment;
import com.stepyen.xutil.data.DateUtils;
import com.stepyen.xutil.resource.ResUtils;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xpage.annotation.Page;

import java.util.Calendar;
import java.util.Date;

/**
 * date：2019/6/26
 * author：stepyen
 * description：
 */
@Page(name = "选择器", extra = R.drawable.ic_widget_imageview)
public class PickerViewFragment extends BaseTestFragment {

    private NumberPicker mNumberPicker;;
    private TextView mSexTv;
    private TextView mClassTv;
    private TextView mDateTv;
    private TextView mTimeTv;
    private TimePickerView mDatePicker;
    private TimePickerView mTimePicker;


    private String[] mSexOption;
    private int sexSelectOption = 0;

    private String[] mGradeOption;
    private String[] mClassOption;
    private int gradeSelectOption = 0;
    private int classSelectOption = 0;

    @Override
    protected void initViews() {
        super.initViews();
        View view = addView(R.layout.fragment_picker);
        mNumberPicker = view.findViewById(R.id.number_picker);
        initNumberPicker();

        initData();
        initDatePicker();
        initTimePickerDialog();

        addTextView("选项选择",R.attr.TextView_hint_tag,10);

        mSexTv = addView("性别选择", v -> {
            showSexPickerView();
        });

        mClassTv = addView("班级选择", v -> {
            showClassPickerView();
        });

        addTextView("时间选择",R.attr.TextView_hint_tag,10);

        mDateTv = addView("日期选择", v -> {
            mDatePicker.show();
        });
        mTimeTv = addView("时间选择", v -> {
            mTimePicker.show();
        });
    }
    private void initNumberPicker() {
        mNumberPicker.setNumberLimitListen(new NumberPicker.OnNumberLimitListen() {
            @Override
            public void isMinimum() {
                ToastUtils.toast("已经是最小了");
            }

            @Override
            public void isMaximum() {
                ToastUtils.toast("已经是最大了");
            }
        });


        ShapeBuilder.create(getContext())
                .solid(R.color.white)
                .radius(22.5f)
                .stroke(0.5f, R.color.xui_gray_7)
                .build(mNumberPicker);

        Drawable drawable = ShapeBuilder.create(getContext())
                .solid(R.color.white)
                .stroke(0.5f, R.color.xui_gray_7)
                .build();


        mNumberPicker.setValueViewBackground(drawable);
    }


    private void initData() {
        mSexOption = ResUtils.getStringArray(R.array.sex_option);
        mGradeOption = ResUtils.getStringArray(R.array.grade_option);
        mClassOption = new String[30];
        for (int i = 0; i < mClassOption.length; i++) {
            mClassOption[i] = (i + 1) + "班";
        }


    }

    private void showSexPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mSexTv.setText(mSexOption[options1]);
                sexSelectOption = options1;
            }
        })
                .setTitleText("性别选择")
                .setSelectOptions(sexSelectOption)
                .build();
        pvOptions.setPicker(mSexOption);
        pvOptions.show();
    }

    private void showClassPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mClassTv.setText(String.format("%s%s", mGradeOption[options1], mClassOption[options2]));
                gradeSelectOption = options1;
                classSelectOption = options2;
            }
        })
                .setTitleText("班级选择")
                .setSelectOptions(gradeSelectOption, classSelectOption)
                .build();
        pvOptions.setNPicker(mGradeOption, mClassOption);
        pvOptions.show();
    }

    private void initDatePicker() {
        mDatePicker = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelected(Date date, View v) {
                ToastUtils.toast(DateUtils.date2String(date, DateUtils.yyyyMMdd.get()));
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setTitleText("日期选择")
                .build();
    }

    private void initTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.string2Date("2013-07-08 12:32:46", DateUtils.yyyyMMddHHmmss.get()));
        mTimePicker = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelected(Date date, View v) {
                ToastUtils.toast(DateUtils.date2String(date, DateUtils.yyyyMMddHHmmss.get()));
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setType(TimePickerType.ALL)
                .setTitleText("时间选择")
                .isDialog(true)
                .setOutSideCancelable(false)
                .setDate(calendar)
                .build();
    }

}
