package com.stepyen.xui.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StyleRes;
import android.widget.TextView;
import com.stepyen.xui.R;
import com.stepyen.xui.widget.progress.IMessageLoader;
import com.stepyen.xui.widget.progress.LoadingCancelListener;
import com.stepyen.xui.widget.progress.loading.MiniLoadingView;

/**
 * 迷你loading加载
 */
public class MiniLoadingDialog extends BaseDialog implements IMessageLoader {

    private MiniLoadingView mLoadingView;
    private TextView mTvTipMessage;

    private LoadingCancelListener mLoadingCancelListener;

    public MiniLoadingDialog(Context context) {
        super(context, R.style.XUIDialog_Custom_MiniLoading, R.layout.xui_dialog_loading_mini);
        initView(getString(R.string.xui_tip_loading_message));
    }

    public MiniLoadingDialog(Context context, String tipMessage) {
        super(context, R.style.XUIDialog_Custom_MiniLoading, R.layout.xui_dialog_loading_mini);
        initView(tipMessage);
    }

    public MiniLoadingDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId, R.layout.xui_dialog_loading_mini);
        initView(getString(R.string.xui_tip_loading_message));
    }

    public MiniLoadingDialog(Context context, @StyleRes int themeResId, String tipMessage) {
        super(context, themeResId, R.layout.xui_dialog_loading_mini);
        initView(tipMessage);
    }

    private void initView(String tipMessage) {
        mLoadingView = (MiniLoadingView) findViewById(R.id.mini_loading_view);
        mTvTipMessage = (TextView) findViewById(R.id.tv_tip_message);

        updateMessage(tipMessage);

        setCancelable(false);
        setCanceledOnTouchOutside(false);

    }


    /**
     * 更新提示信息
     *
     * @param tipMessage
     * @return
     */
    @Override
    public void updateMessage(String tipMessage) {
        if (mTvTipMessage != null) {
            mTvTipMessage.setText(tipMessage);
        }
    }

    /**
     * 更新提示信息
     *
     * @param tipMessageId
     * @return
     */
    @Override
    public void updateMessage(int tipMessageId) {
        updateMessage(getString(tipMessageId));
    }


    /**
     * 显示加载
     */
    @Override
    public void show() {
        super.show();
        if (mLoadingView != null) {
            mLoadingView.start();
        }
    }

    /**
     * 隐藏加载
     */
    @Override
    public void dismiss() {
        if (mLoadingView != null) {
            mLoadingView.stop();
        }
        super.dismiss();
    }

    /**
     * 资源释放
     */
    @Override
    public void recycle() {

    }

    /**
     * 是否在加载
     *
     * @return
     */
    @Override
    public boolean isLoading() {
        return isShowing();
    }

    /**
     * 设置是否可取消
     *
     * @param flag
     */
    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
        if (flag) {
            setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    if (mLoadingCancelListener != null) {
                        mLoadingCancelListener.onCancelLoading();
                    }
                }
            });
        }
    }

    /**
     * 设置取消的回掉监听
     *
     * @param listener
     */
    @Override
    public void setLoadingCancelListener(LoadingCancelListener listener) {
        mLoadingCancelListener = listener;
    }
}
