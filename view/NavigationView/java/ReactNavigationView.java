package com.qiji.view.navigationview;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by waterchen on 2015/10/21.
 */

/**
 * 定义原生NavigationView
 */
public class ReactNavigationView extends NavigationView{

    private View mHeaderView;

    public ReactNavigationView(Context context){
        this(context, null);
    }

    public ReactNavigationView(Context context, AttributeSet atts){
        this(context, atts, 0);
    }

    public ReactNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public View inflateHeaderView(int res) {
        mHeaderView = super.inflateHeaderView(res);
        return mHeaderView;
    }

    public View getHeaderView(){
        return  mHeaderView;
    }
}
