package com.stepyen.xui.widget.linearlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.stepyen.xui.R;
import com.stepyen.xui.utils.ResUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * date：2019/9/24
 * author：stepyen
 * description：流标签布局
 *
 *  * 功能
 *  * 1、指定最大行数、列数；不指定时，为不限制，子view会自动换行
 *  * 2、指定横纵向间距
 *  * 3、子view设置的margin不做处理
 *
 *
 *
 */

public class FlowTagLayout extends LinearLayout {

    /**
     * FlowLayout not support checked
     */
    public static final int FLOW_TAG_CHECKED_NONE = 0;
    /**
     * FlowLayout support single-select
     */
    public static final int FLOW_TAG_CHECKED_SINGLE = 1;
    /**
     * FlowLayout support multi-select
     */
    public static final int FLOW_TAG_CHECKED_MULTI = 2;
    /**
     * FlowLayout support display
     */

    public static final int FLOW_TAG_DISPLAY = 3;
    public static final int DEFAULT_MAX_ROW = -1; // 最大行数，默认值
    public static final int DEFAULT_MAX_COLUMN = -1; // 最大列数，默认值

    /**
     * 最大行数, 默认为 -1 时，表示不限行数
     */
    private int mMaxRow = DEFAULT_MAX_ROW;

    /**
     * 最大列数, 默认为 -1 时，表示不限列数
     */
    private int mMaxColumn = DEFAULT_MAX_COLUMN;

    /**
     * 竖直方向间距
     */
    private int mVerticalSpace = 0;
    /**
     * 水平方向间距
     */
    private int mHorizontalSpace = 0;

    /**
     * Should be used by subclasses to listen to changes in the dataset
     */
    AdapterDataSetObserver mDataSetObserver;

    /**
     * The adapter containing the data to be displayed by this view
     */
    BaseTagAdapter mAdapter;

    /**
     * the tag click event callback
     */
    OnTagClickListener mOnTagClickListener;

    /**
     * the tag select event callback
     */
    OnTagSelectListener mOnTagSelectListener;

    /**
     * 标签流式布局选中模式，默认是不支持选中的
     */
    private int mTagCheckMode = FLOW_TAG_CHECKED_NONE;

    /**
     * 存储选中的tag
     */
    private SparseBooleanArray mCheckedTagArray = new SparseBooleanArray();
    /**
     * 子View的宽度，如果为0 则为warp_content
     */
    private int mWidth;

    private List<Integer> mSelectedIndexs;


    public FlowTagLayout(Context context) {
        this(context, null);
    }

    public FlowTagLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowTagLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOrientation(HORIZONTAL);
        initAttrs(context,attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FlowTagLayout);
        mMaxRow = ta.getInteger(R.styleable.FlowTagLayout_max_row, DEFAULT_MAX_ROW);
        mMaxColumn = ta.getInteger(R.styleable.FlowTagLayout_max_column, DEFAULT_MAX_COLUMN);
        mVerticalSpace = (int) ta.getDimension(R.styleable.FlowTagLayout_vertical_space, 0);
        mHorizontalSpace = (int) ta.getDimension(R.styleable.FlowTagLayout_horizontal_space, 0);
        mTagCheckMode = ta.getInt(R.styleable.FlowTagLayout_ftl_check_mode, FLOW_TAG_CHECKED_NONE);
        int entriesID = ta.getResourceId(R.styleable.FlowTagLayout_ftl_entries, 0);
        if (entriesID != 0) {
            BaseTagAdapter tagAdapter = setItems(ResUtils.getStringArray(entriesID));
            int selectedIDs = ta.getResourceId(R.styleable.FlowTagLayout_ftl_selecteds, 0);
            if (selectedIDs != 0) {
                tagAdapter.setSelectedPositions(ResUtils.getIntArray(selectedIDs));
            }
        }
        ta.recycle();
    }


    @Override
    public LinearLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LinearLayout.LayoutParams(getContext(), attrs);
    }


    /**
     * 设置最大行数
     *
     * @param maxRow
     */
    public void setMaxRow(int maxRow) {
        if (maxRow < DEFAULT_MAX_ROW) {
            maxRow = DEFAULT_MAX_ROW;
        }
        mMaxRow = maxRow;
    }

    /**
     * 设置最大列数
     *
     * @param maxColumn
     */
    public void setMaxColumn(int maxColumn) {
        if (maxColumn < DEFAULT_MAX_ROW) {
            maxColumn = DEFAULT_MAX_ROW;
        }
        mMaxColumn = maxColumn;
    }

    /**
     * 设置纵向间距
     *
     * @param verticalSpace
     */
    public void setVerticalSpace(int verticalSpace) {
        if (verticalSpace < 0) {
            verticalSpace = 0;
        }
        mVerticalSpace = verticalSpace;
    }

    /**
     * 设置横向间距
     *
     * @param horizontalSpace
     */
    public void setHorizontalSpace(int horizontalSpace) {
        if (horizontalSpace < 0) {
            horizontalSpace = 0;
        }
        mHorizontalSpace = horizontalSpace;
    }

    /**
     * 目的在于计算出控件的宽高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = getPaddingTop() + getPaddingBottom();
        int size = getChildCount();
        int rows = 1;
        int column = 0;
        int tempWidth = 0;  // 临时宽度
        boolean isNewLine = false;

        for (int i = 0; i < size; i++) {
            View view = getChildAt(i);
            view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int viewWidth = view.getMeasuredWidth();
            int viewHeight = view.getMeasuredHeight();
            if (i == 0) {
                height += viewHeight;
            }

            tempWidth = tempWidth + viewWidth;


            column = column + 1;
            if (i != 0 && mMaxColumn != DEFAULT_MAX_COLUMN) {
                isNewLine = column % mMaxColumn == 1;
            }

            if (tempWidth > width || isNewLine) {
                // 新一行的宽
                tempWidth = viewWidth;

                // 尝试换行，发现超过了最大行数就结束循环
                if (mMaxRow != -1 && rows + 1 > mMaxRow) {
                    break;
                }
                rows++;

                height = height + viewHeight + mVerticalSpace;

                isNewLine = false;
            }

            tempWidth = tempWidth + mHorizontalSpace;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 可以绘制的宽度
        int width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        int size = getChildCount();
        int tempWidth = 0;
        int tempHeight = 0;
        int rows = 1;
        int column = 0;
        boolean isNewLine = false;

        for (int i = 0; i < size; i++) {
            View view = getChildAt(i);
            int viewWidth = view.getMeasuredWidth();
            int viewHeight = view.getMeasuredHeight();

            tempWidth = tempWidth + viewWidth;

            column = column + 1;
            if (i != 0 && mMaxColumn != DEFAULT_MAX_COLUMN) {
                isNewLine = column % mMaxColumn == 1;
            }
            if (tempWidth > width|| isNewLine) {
                tempWidth = viewWidth;
                // 尝试换行，发现超过了最大行数就结束循环
                if (mMaxRow != -1 && rows + 1 > mMaxRow) {
                    break;
                }
                rows++;
                tempHeight = tempHeight + viewHeight + mVerticalSpace;

                isNewLine = false;
            }

            int left = getPaddingLeft() + tempWidth - viewWidth;
            int top = getPaddingTop() + tempHeight;
            int right = getPaddingLeft() + tempWidth;
            int bottom = getPaddingTop() + tempHeight + viewHeight;

            view.layout(left, top, right, bottom);

            tempWidth += mHorizontalSpace;
        }
    }

    public BaseTagAdapter getAdapter() {
        return mAdapter;
    }

    /**
     * 像ListView、GridView一样使用FlowLayout
     *
     * @param adapter
     */
    public FlowTagLayout setAdapter(BaseTagAdapter adapter) {
        if (mAdapter != null && mDataSetObserver != null) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
        }

        //清除现有的数据
        removeAllViews();
        mAdapter = adapter;

        if (mAdapter != null) {
            mDataSetObserver = new AdapterDataSetObserver();
            mAdapter.registerDataSetObserver(mDataSetObserver);
        }
        return this;
    }

    /**
     * 子View个数
     *
     * @param width
     */
    public FlowTagLayout setChildWidth(int width) {
        mWidth = width;
        return this;
    }

    /**
     * 重新加载刷新数据
     */
    private void reloadData() {
        removeAllViews();

        MarginLayoutParams mMarginLayoutParams = new MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (mWidth != 0) {
            mMarginLayoutParams.width = mWidth;
        }
        boolean isSetted = false;
        for (int i = 0; i < mAdapter.getCount(); i++) {
            final int index = i;
            mCheckedTagArray.put(i, false);
            final View childView = mAdapter.getView(i, null, this);
            addView(childView, mMarginLayoutParams);

            if (mAdapter instanceof OnInitSelectedPosition) {
                boolean isSelected = mAdapter.isSelectedPosition(i);
                //判断一下模式
                if (mTagCheckMode == FLOW_TAG_CHECKED_SINGLE) {
                    //单选只有第一个起作用
                    if (isSelected && !isSetted) {
                        mCheckedTagArray.put(i, true);
                        childView.setSelected(true);
                        isSetted = true;
                    }
                } else if (mTagCheckMode == FLOW_TAG_CHECKED_MULTI) {
                    if (isSelected) {
                        mCheckedTagArray.put(i, true);
                        childView.setSelected(true);
                    }
                } else if (mTagCheckMode == FLOW_TAG_DISPLAY) { //不可点击
                    mCheckedTagArray.put(i, true);
                    childView.setSelected(true);
                    childView.setEnabled(false);
                }
            }
            mSelectedIndexs = null; //重新加载数据，点击索引清空
            setChildViewClickListener(index, childView);
        }
    }

    /**
     * 设置子控件的点击监听
     * @param index
     * @param childView
     */
    private void setChildViewClickListener(final int index, final View childView) {
        childView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTagCheckMode == FLOW_TAG_CHECKED_NONE) {
                    if (mOnTagClickListener != null) {
                        mOnTagClickListener.onItemClick(FlowTagLayout.this, childView, index);
                    }
                } else if (mTagCheckMode == FLOW_TAG_CHECKED_SINGLE) {
                    //判断状态
                    if (mCheckedTagArray.get(index)) {
                        return;
                    }
                    //更新全部状态为fasle

                    for (int k = 0; k < mAdapter.getCount(); k++) {
                        mCheckedTagArray.put(k, false);
                        getChildAt(k).setSelected(false);
                    }
                    //更新点击状态
                    mCheckedTagArray.put(index, true);
                    childView.setSelected(true);

                    setSelectedIndexs(Arrays.asList(index));
                    if (mOnTagSelectListener != null) {
                        mOnTagSelectListener.onItemSelect(FlowTagLayout.this, index, Arrays.asList(index));
                    }
                } else if (mTagCheckMode == FLOW_TAG_CHECKED_MULTI) {
                    if (mCheckedTagArray.get(index)) {
                        mCheckedTagArray.put(index, false);
                        childView.setSelected(false);
                    } else {
                        mCheckedTagArray.put(index, true);
                        childView.setSelected(true);
                    }
                    //回调
                    List<Integer> list = new ArrayList<>();
                    for (int k = 0; k < mAdapter.getCount(); k++) {
                        if (mCheckedTagArray.get(k)) {
                            list.add(k);
                        }
                    }
                    setSelectedIndexs(list);
                    if (mOnTagSelectListener != null) {
                        mOnTagSelectListener.onItemSelect(FlowTagLayout.this, index, list);
                    }
                }
            }
        });
    }

    public FlowTagLayout setOnTagClickListener(OnTagClickListener onTagClickListener) {
        mOnTagClickListener = onTagClickListener;
        return this;
    }

    public FlowTagLayout setOnTagSelectListener(OnTagSelectListener onTagSelectListener) {
        mOnTagSelectListener = onTagSelectListener;
        return this;
    }

    /**
     * 获取标签模式
     *
     * @return
     */
    public int getTagCheckMode() {
        return mTagCheckMode;
    }

    /**
     * 设置标签选中模式
     *
     * @param tagMode
     */
    public FlowTagLayout setTagCheckedMode(int tagMode) {
        mTagCheckMode = tagMode;
        return this;
    }

    private FlowTagLayout setSelectedIndexs(List<Integer> selectedIndexs) {
        mSelectedIndexs = selectedIndexs;
        return this;
    }

    /**
     * 获取选中索引的集合
     * @return
     */
    public List<Integer> getSelectedIndexs() {
        if (mSelectedIndexs != null) {
            return mSelectedIndexs;
        } else {
            return getAdapter().getInitSelectedPositions();
        }
    }

    /**
     * 设置默认的流布局内容
     *
     * @param items A list of items
     */
    public <T> BaseTagAdapter setItems(@NonNull T... items) {
        return setItems(Arrays.asList(items));
    }

    /**
     * 设置默认的流布局内容
     *
     * @param items A list of items
     */
    public <T> BaseTagAdapter setItems(@NonNull List<T> items) {
        if (mAdapter != null) {
            mAdapter.clearAndAddTags(items);
        } else {
            BaseTagAdapter tagAdapter = new DefaultFlowTagAdapter(getContext());
            setAdapter(tagAdapter);
            tagAdapter.addTags(items);
        }
        return mAdapter;
    }

    /**
     * 设置初始化选中的标签索引
     * @param ps
     * @return
     */
    public FlowTagLayout setSelectedPositions(Integer... ps) {
        if (mAdapter != null) {
            mAdapter.setSelectedPositions(ps);
        }
        return this;
    }

    /**
     * 设置初始化选中的标签索引
     * @param ps
     * @return
     */
    public FlowTagLayout setSelectedPositions(List<Integer> ps) {
        if (mAdapter != null) {
            mAdapter.setSelectedPositions(ps);
        }
        return this;
    }

    /**
     * 设置初始化选中的标签索引
     * @param ps
     * @return
     */
    public FlowTagLayout setSelectedPositions(int[] ps) {
        if (mAdapter != null) {
            mAdapter.setSelectedPositions(ps);
        }
        return this;
    }


    /**
     * 设置默认选中的内容
     * @param selectedItems 选中的内容集合
     * @return
     */
    public <T> FlowTagLayout setSelectedItems(T... selectedItems) {
        setSelectedItems(Arrays.asList(selectedItems));
        return this;
    }

    /**
     * 设置默认选中的内容
     * @param selectedItems 选中的内容集合
     * @return
     */
    public <T> FlowTagLayout setSelectedItems(List<T> selectedItems) {
        if (mTagCheckMode != FLOW_TAG_CHECKED_NONE) {
            if (mAdapter != null) {
                mAdapter.setSelectedPositions(getSelectedPositions(selectedItems, mAdapter.getItems()));
            }
        }
        return this;
    }

    /**
     * 获取选中内容在流布局中的索引位置集合
     *
     * @param selectedItems
     *            选中的内容集合
     * @param items
     *            流布局中选项的集合
     * @return
     */
    private  <T> List<Integer> getSelectedPositions(List<T> selectedItems, List<T> items) {
        List<Integer> positions = new ArrayList<>();
        if (!isListEmpty(selectedItems) && !isListEmpty(items)) {
            for (int i = 0; i < selectedItems.size(); i++) {
                for (int j = 0; j < items.size(); j++) {
                    if (items.get(j).equals(selectedItems.get(i))) {
                        positions.add(j);
                        break;
                    }
                }
            }
        }
        return positions;
    }

    /**
     * 集合是否为空
     * @param list
     * @param <T>
     * @return
     */
    private <T> boolean isListEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }


    /**
     * 初始化选择
     */
    public interface OnInitSelectedPosition {
        /**
         * @param position 位置
         * @return
         */
        boolean isSelectedPosition(int position);
    }

    /**
     * 点击的监听,模式是 FLOW_TAG_CHECKED_NONE 才被使用
     */
    public interface OnTagClickListener {
        /**
         * 当标签被点击
         *
         * @param parent   流布局
         * @param view     被点击的标签
         * @param position 被点击控件的位置
         */
        void onItemClick(FlowTagLayout parent, View view, int position);
    }

    /**
     * 选择的监听
     */
    public interface OnTagSelectListener {
        /**
         * 当标签被选中
         *
         * @param parent       流布局
         * @param position     位置
         * @param selectedList 选中内容的集合
         */
        void onItemSelect(FlowTagLayout parent, int position, List<Integer> selectedList);
    }

    class AdapterDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            super.onChanged();
            reloadData();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    }
}
