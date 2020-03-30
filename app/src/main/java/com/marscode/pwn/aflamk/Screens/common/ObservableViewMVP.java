package com.marscode.pwn.aflamk.Screens.common;

public interface ObservableViewMVP<ListnerType> extends ViewMVP {

    void registerListener(ListnerType listener);

    void unregisterListener(ListnerType listener);

}
