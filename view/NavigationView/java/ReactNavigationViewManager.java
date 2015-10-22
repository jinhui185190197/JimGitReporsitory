package com.qiji.view.navigationview;

import android.content.Context;
import android.os.SystemClock;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.CatalystStylesDiffMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIProp;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.qiji.R;
import com.qiji.view.navigationview.ReactNavigationView;

import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by waterchen on 2015/10/21.
 */
public class ReactNavigationViewManager extends ViewGroupManager<ReactNavigationView> {


    public static final String CLASS_NAME = "RCTNavigationView";

    @UIProp(UIProp.Type.ARRAY)
    public static final String PROP_ITEMS_GROUP = "items_group";
    /**
     * 给定menu Item名字
     */
    @UIProp(UIProp.Type.ARRAY)
    public static final String PROP_ITEMS = "items";

//    public static final String PROP_ITEM_ICONS_GROUP = "item_icons_group";
    /**
     * 给定对应的menu Item的Icon
     */
    @UIProp(UIProp.Type.ARRAY)
    public static final String PROP_ITEM_ICONS = "item_icons";
    /**
     * 给定Header Icon
     */
    @UIProp(UIProp.Type.STRING)
    public static final String PROP_HEADER_ICON = "header_icon";
    /**
     * 给定Header text内容
     */
    @UIProp(UIProp.Type.STRING)
    public static final String PROP_HEADER_TEXT1 = "header_text1";
    @UIProp(UIProp.Type.STRING)
    public static final String PROP_HEADER_TEXT2 = "header_text2";


    @Override
    public String getName() {
        return CLASS_NAME;
    }

    @Override
    protected ReactNavigationView createViewInstance(ThemedReactContext themedReactContext) {
        //进行必要的初始化
        ReactNavigationView navigationView = new ReactNavigationView(themedReactContext);
        navigationView.inflateHeaderView(R.layout.reactnavigationview_header);

        return navigationView;
    }

    @Override
    public void updateView(ReactNavigationView navigationView, CatalystStylesDiffMap props) {
        super.updateView(navigationView, props);

        if (props.hasKey(PROP_HEADER_ICON)) {
            //设置Header图片
            String icon = props.getString(PROP_HEADER_ICON);
            View headerView = navigationView.getHeaderView();
            if (headerView != null && icon != null) {
                ImageView iconIm = (ImageView) headerView.findViewById(R.id.header_icon);
                iconIm.setImageResource(getDrawableResourceByName(navigationView.getContext(), icon));
            }
        }
        if (props.hasKey(PROP_HEADER_TEXT1)) {
            //设置Header文字内容
            String text1 = props.getString(PROP_HEADER_TEXT1);
            View headerView = navigationView.getHeaderView();
            if (headerView != null && text1 != null) {
                TextView text1Tv = (TextView) headerView.findViewById(R.id.header_text1);
                text1Tv.setText(text1);
            }
        }

        if (props.hasKey(PROP_HEADER_TEXT2)) {
            //设置Header文字内容
            String text2 = props.getString(PROP_HEADER_TEXT2);
            View headerView = navigationView.getHeaderView();
            if (headerView != null && text2 != null) {
                TextView text2Tv = (TextView) headerView.findViewById(R.id.header_text2);
                text2Tv.setText(text2);
            }
        }

        if (props.hasKey(PROP_ITEMS_GROUP)) {
            Menu menu = navigationView.getMenu();
            ReadableArray itemsGroup = props.getArray(PROP_ITEMS_GROUP);
            int itemsGroupCount = itemsGroup.size();
            int curMenuItemCount = 0;
            for (int groupIndex = 0; groupIndex < itemsGroupCount; groupIndex++) {

                //设置菜单栏
                ReadableArray menuItems = itemsGroup.getArray(groupIndex);
                if (menuItems != null && menuItems.size() > 0) {
                    int itemCount = menuItems.size();
                    for (int itemIndex = 0; itemIndex < itemCount; itemIndex++) {
                        String curItemTitle = menuItems.getString(itemIndex);
                        MenuItem curMenuItem = menu.add(groupIndex, itemIndex, 0, curItemTitle);
                        if (props.hasKey(PROP_ITEM_ICONS)) {
                            curMenuItem.setIcon(getDrawableResourceByName(navigationView.getContext(),
                                    props.getArray(PROP_ITEM_ICONS).getString(curMenuItemCount)));
                        }
                        curMenuItemCount++;
                    }
                }
            }
        }

    }

    @Override
    protected void addEventEmitters(ThemedReactContext reactContext, final ReactNavigationView view) {
        final EventDispatcher eventDispatcher = ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                eventDispatcher.dispatchEvent(new NavigationMenuItemClickEvent(view.getId(), SystemClock.uptimeMillis(), menuItem.getGroupId(), menuItem.getItemId()));
                return true;
            }
        });
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return super.getExportedCustomDirectEventTypeConstants();
    }

    private static int getDrawableResourceByName(Context context, String resName) {
        String targertName = resName.toLowerCase().replace("-", "_");
        return context.getResources().getIdentifier(targertName, "drawable", context.getPackageName());
    }
}
