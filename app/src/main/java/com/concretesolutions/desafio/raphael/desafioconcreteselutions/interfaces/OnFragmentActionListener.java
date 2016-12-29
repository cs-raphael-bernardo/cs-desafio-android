package com.concretesolutions.desafio.raphael.desafioconcreteselutions.interfaces;

import android.content.Intent;
import android.support.v4.app.Fragment;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit.RetrofitError;

/**
 * Created by Raphael on 11/10/2016.
 */
public interface OnFragmentActionListener {
    void replaceFragment(Fragment fragment);
    void showProgressDialog();
    void showProgressDialog(String loading);
    void dismissProgressDialog();
    void showRestErrorMessage(RetrofitError retrofitError);
}
