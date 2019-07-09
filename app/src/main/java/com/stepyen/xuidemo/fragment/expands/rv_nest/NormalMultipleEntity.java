package com.stepyen.xuidemo.fragment.expands.rv_nest;

/**
 * date：2019/7/9
 * author：stepyen
 * description：
 */
public class NormalMultipleEntity {
    public static final int TYPE_BANNER = 1;
    public static final int TYPE_VIEWPAGER = 2;
    public static final int TYPE_RECYCLEVIEW= 3;

    private int type;

    public NormalMultipleEntity(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
