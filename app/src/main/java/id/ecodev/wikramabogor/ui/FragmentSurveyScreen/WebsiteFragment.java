package id.ecodev.wikramabogor.ui.FragmentSurveyScreen;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ecodev.wikramabogor.Model.Survey;
import id.ecodev.wikramabogor.Model.Website;
import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.TimeOutActivity;
import id.ecodev.wikramabogor.adapter.WebsiteAdapter;
import id.ecodev.wikramabogor.base.BaseFragment;
import id.ecodev.wikramabogor.helper.PrefManager;
import id.ecodev.wikramabogor.ui.SurveyScreen.SurveyPresenter;
import id.ecodev.wikramabogor.ui.SurveyScreen.SurveyView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebsiteFragment extends BaseFragment implements SurveyView {


    public WebsiteFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.recycler_object)
    RecyclerView recyclerView;
    @BindView(R.id.hiddenLayout)
    LinearLayout linearHidden;
    @BindView(R.id.mySwipe)
    SwipeRefreshLayout swipeRefreshLayout;

    WebsiteAdapter websiteAdapter;
    SurveyPresenter surveyPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_website, container, false);
        ButterKnife.bind(this,view);
        surveyPresenter = new SurveyPresenter(this);
        websiteAdapter  = new WebsiteAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(websiteAdapter);

        PrefManager prefManager = new PrefManager(getActivity());
        surveyPresenter.loadWebsite(prefManager.getUserId());
        checkData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                surveyPresenter.loadWebsite(prefManager.getUserId());
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    public void checkData()
    {
        if (websiteAdapter.getItemCount() > 0)
        {
            linearHidden.setVisibility(View.GONE);
        }else{
            linearHidden.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSuccessLoadSurvey(List<Survey> surveys) {

    }

    @Override
    public void onSucessLoadWebsite(List<Website> websites) {
        websiteAdapter.replace_data(websites);
        swipeRefreshLayout.setRefreshing(false);
        checkData();
    }

    @Override
    public void onShow() {
        super.showLoading(getActivity());
    }

    @Override
    public void onHide() {
        super.dismissLoading();
    }

    @Override
    public void onError(String error) {
//        super.showError(error);
        Intent intent = new Intent(getActivity(), TimeOutActivity.class);
        intent.putExtra("class", "website");
        startActivity(intent);
    }

    @Override
    public void getMessage(String message) {
        super.showMessage(message);
    }

    @Override
    public void getHttp(String http) {
        super.showHttp(http);
    }
}
