package id.ecodev.wikramabogor.ui.AddSurveyScreen;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.List;

import id.ecodev.wikramabogor.Model.Category;
import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.adapter.CategoryAdapter;
import id.ecodev.wikramabogor.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends BaseFragment implements BlockingStep,AddSurveyView {


    public FirstFragment() {

    }

    AddSurveyPresenter addSurveyPresenter;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;
    Context mContext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_first, container, false);

        addSurveyPresenter = new AddSurveyPresenter(this);
        addSurveyPresenter.getCategories();

        categoryAdapter = new CategoryAdapter(getActivity());
        recyclerView = view.findViewById(R.id.recycler_temp);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerView.setAdapter(categoryAdapter);

        mContext = view.getContext();

        return view;
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        callback.goToNextStep();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onSuccessLoadCategories(List<Category> categories) {
        categoryAdapter.replace_data(categories);
    }

    @Override
    public void onSuccessCreateObjek(String result) {

    }

    @Override
    public void onShow() {
//        progressDialog = new ProgressDialog(getActivity());
//        progressDialog.setTitle("Now Loading..");
//        Toast.makeText(getActivity(), "Loading", Toast.LENGTH_SHORT).show();
        super.showLoading(getActivity());

    }

    @Override
    public void onHide() {
//        progressDialog.dismiss();
        super.dismissLoading();
    }

    @Override
    public void onError(String error) {
        Log.d("HTTP ERROR : ",error);
    }

    @Override
    public void getMessage(String message) {

    }

    @Override
    public void getHttp(String http) {
        Log.d("HTTP STATUS : ",http);
    }
}
