package com.marscode.pwn.aflamk.Data;

import android.content.Context;
import android.view.View;

import com.marscode.pwn.aflamk.Models.Movies;

public interface MovieListner {
   public void onClickItem(Context context,View view, Movies movie);

}
