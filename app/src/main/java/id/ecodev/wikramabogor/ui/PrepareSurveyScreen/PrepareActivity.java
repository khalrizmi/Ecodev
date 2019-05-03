package id.ecodev.wikramabogor.ui.PrepareSurveyScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ecodev.wikramabogor.Model.Objek;
import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.TimeOutActivity;
import id.ecodev.wikramabogor.adapter.ObjekAdapter;
import id.ecodev.wikramabogor.base.BaseActivity;
import id.ecodev.wikramabogor.helper.PrefManager;
import id.ecodev.wikramabogor.ui.SurveyScreen.DoneActivity;
import id.ecodev.wikramabogor.ui.SurveyScreen.SurveyActivity;

public class PrepareActivity extends BaseActivity implements PrepareView {

    @BindView(R.id.recycler_object)
    RecyclerView recyclerObject;
    @BindView(R.id.hiddenLayout)
    LinearLayout linearHidden;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.btnDone)
    Button btnDone;
    @BindView(R.id.mySwipe)
    SwipeRefreshLayout swipeRefreshLayout;

    PreparePresenter preparePresenter;
    ObjekAdapter objekAdapter;
    PrefManager prefManager;
    List<Objek> mListObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare);
        ButterKnife.bind(this);
        super.setUpActionBar("Daftar Objek");

        prefManager = new PrefManager(this);
        preparePresenter = new PreparePresenter(this);

        initRecyclerView();
        initSwipeRefreshLayout();

    }

    @Override
    protected void onStart() {
        String id = String.valueOf(prefManager.getUserId());
        preparePresenter.loadObjek(id);
        super.onStart();
    }

    @Override
    protected void onResume() {
        String id = String.valueOf(prefManager.getUserId());
        preparePresenter.loadObjek(id);
        super.onResume();
    }

    private void initRecyclerView() {
        mListObject = new ArrayList<>();
        objekAdapter = new ObjekAdapter(this);
        recyclerObject.setAdapter(objekAdapter);
        recyclerObject.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String id = String.valueOf(prefManager.getUserId());
                preparePresenter.loadObjek(id);
            }
        });
    }


    @Override
    public void onSuccessLoadObject(List<Objek> objeks) {
        mListObject = objeks;
        objekAdapter.replace_data(objeks);
        if (objeks.size() > 0)
            linearHidden.setVisibility(View.GONE);
        else
            linearHidden.setVisibility(View.VISIBLE);
    }

    @Override
    public void onShow() {
//        super.showLoading();
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                }
            });
        }
    }

    @Override
    public void onHide() {
//        super.dismissLoading();
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onError(String error) {
        super.showError(error);
        Intent intent = new Intent(this, TimeOutActivity.class);
        intent.putExtra("class", "prepare");
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

    @OnClick(R.id.btnAdd)
    public void onBtnAddClicked() {
        startActivity(new Intent(getApplicationContext(), SurveyActivity.class));
    }

    @OnClick(R.id.btnDone)
    public void onBtnDoneClicked() {
        if (objekAdapter.getItemCount() <= 0) {
            Toast.makeText(PrepareActivity.this, "Tambahkan Objek Sebelum menyelesaikan Survey!", Toast.LENGTH_SHORT).show();
        } else {
            if (validation()) {
                startActivity(new Intent(getApplicationContext(), DoneActivity.class));
            } else {
                Toast.makeText(PrepareActivity.this, "Verifikasi semua potensi objek sebelum mengahiri survey", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validation() {
        for (int i = 0; i < mListObject.size(); i++) {
            if (mListObject.get(i).getVerified().equals("0"))
                return false;
        }
        return true;
    }
}
