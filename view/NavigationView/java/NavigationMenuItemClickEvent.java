package com.qiji.view.navigationview;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

/**
 * Created by waterchen on 2015/10/22.
 */
public class NavigationMenuItemClickEvent extends Event<NavigationMenuItemClickEvent>{


    public static final String EVENT_NAME = "topSelect";

    public static final String MENU_GROUP = "menu_group";
    public static final String MENU_ITEM = "menu_item";

    private int mMenuItemGroup;
    private int mMenuItemIndex;

    protected NavigationMenuItemClickEvent(int viewTag, long timestampMs, int group, int index) {
        super(viewTag, timestampMs);
        mMenuItemGroup = group;
        mMenuItemIndex = index;
    }

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }

    public int getGroupIndex(){
        return this.mMenuItemGroup;
    }

    public int getItemIndex(){
        return  this.mMenuItemIndex;
    }

    @Override
    public void dispatch(RCTEventEmitter rctEventEmitter) {
        WritableNativeMap event = new WritableNativeMap();
        event.putInt(MENU_GROUP, this.getGroupIndex());
        event.putInt(MENU_ITEM,this.getItemIndex());
        rctEventEmitter.receiveEvent(this.getViewTag(), this.getEventName(), event);
    }
}
