package com.concretesolutions.desafio.raphael.desafioconcreteselutions.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.concretesolutions.desafio.raphael.desafioconcreteselutions.R;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.fragments.RepositoriesFragment;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.fragments.RepositoryPullRequestsFragment;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.helpers.RetrofitErrorMessageExtractor;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.interfaces.OnFragmentActionListener;
import com.orhanobut.hawk.Hawk;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit.RetrofitError;

public class MainActivity extends AppCompatActivity implements OnFragmentActionListener {

    private SweetAlertDialog mProgressDialog;
    private Fragment dataFragment;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicia o gerenciador de cache
        Hawk.init(this).build();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // find the retained fragment on activity restarts
        fm = getSupportFragmentManager();
        dataFragment = fm.findFragmentByTag(RepositoryPullRequestsFragment.TAG);

        if (dataFragment != null) {
            // Temos um fragmento retido, trocar para ele sel quardar o último
            recoverSavedFragment();
        } else {
            // Nenhum fragmento retido, seguir normalmente
            replaceForRepositoriesFragment(RepositoriesFragment.newInstance(null, null));
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        super.onBackPressed();
        return true;
    }

    private void replaceForRepositoriesFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main, fragment, RepositoriesFragment.TAG)
                .commit();
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        if (fragment instanceof RepositoryPullRequestsFragment) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main, fragment, RepositoryPullRequestsFragment.TAG)
                    .addToBackStack(RepositoryPullRequestsFragment.TAG)
                    .commit();
        }
    }

    private void recoverSavedFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main, dataFragment, RepositoryPullRequestsFragment.TAG)
                .commit();
    }

    @Override
    public void showProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            dismissProgressDialog();
        } else {
            mProgressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
            mProgressDialog.setTitleText("Carregando...");
            try {
                mProgressDialog.show();
            } catch (Exception e) {
                Log.e("SweetAlert", e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void showProgressDialog(String loading) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            dismissProgressDialog();
        } else {
            mProgressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
            mProgressDialog.setTitleText(loading);
            try {
                mProgressDialog.show();
            } catch (Exception e) {
                Log.e("SweetAlert", e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog.setTitleText("Carregando...");
        }
    }

    @Override
    public void showRestErrorMessage(final RetrofitError retrofitError) {
        SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
        dialog.setTitleText("Erro");
        dialog.setContentText(RetrofitErrorMessageExtractor.buildErrorMessage(retrofitError));

        try {
            dialog.show();
        } catch (Exception e) {
            Log.e("SweetAlert", e.getLocalizedMessage());
        }
    }
}
