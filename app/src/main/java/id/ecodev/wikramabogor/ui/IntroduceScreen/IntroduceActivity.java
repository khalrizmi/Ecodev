package id.ecodev.wikramabogor.ui.IntroduceScreen;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ecodev.wikramabogor.Model.Slide;
import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.TimeOutActivity;
import id.ecodev.wikramabogor.adapter.IntroduceAdapter;
import id.ecodev.wikramabogor.base.BaseActivity;
import id.ecodev.wikramabogor.config.ApplicationConfig;
import id.ecodev.wikramabogor.helper.PrefManager;
import id.ecodev.wikramabogor.ui.CredentialScreen.CredentialActivity;
import id.ecodev.wikramabogor.ui.HomeScreen.HomeActivity;

public class IntroduceActivity extends BaseActivity implements IntroduceView {

    List<Slide> slides = new ArrayList<>();
    TextView[] dots;
    IntroducePresenter introducePresenter;
    @BindView(R.id.tv_previous)
    TextView tvPrevious;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.layout_dots)
    LinearLayout layoutDots;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    IntroduceAdapter adapter;

    @OnClick(R.id.tv_previous)
    public void onTvPreviousClicked() {
        int current = getCurrentItem(-1);
        viewPager.setCurrentItem(current);
    }

    @OnClick(R.id.tv_next)
    public void onTvNextClicked() {
        int current = getCurrentItem(1);
        if (current < slides.size())
            viewPager.setCurrentItem(current);
        else
            launchMain();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        ButterKnife.bind(this);
        requestPerms();
        introducePresenter = new IntroducePresenter(this);
        introducePresenter.loadSlider();

    }

    private void requestPerms() {
        String[] permission = new String[]{
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            requestPermissions(permission, ApplicationConfig.PERMS_REQUEST_CODE);
        }
    }

    private void addBottomDots(int position) {
        dots = new TextView[slides.size()];

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(24, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutDots.removeAllViews();
        for (int i = 0; i < slides.size(); i++) {
            dots[i] = new TextView(this);
            dots[i].setWidth(24);
            dots[i].setLayoutParams(layoutParams);
//            dots[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_indicator_default));
//            dots[i].setBackgroundColor(Color.BLACK);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextColor(Color.parseColor("#A9A9A9"));
            dots[i].setTextSize(35);
            layoutDots.addView(dots[i]);
        }

//        dots[position].setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_indicator_selected));
//        dots[position].setBackgroundColor(Color.BLUE);
        dots[position].setTextColor(getResources().getColor(R.color.colorAccent));
    }

    private void launchMain() {
        PrefManager prefManager = new PrefManager(this);
        Intent mainIntent;
        if (prefManager.getIsLogged())
        {
            mainIntent = new Intent(this, HomeActivity.class);
        }else{
            mainIntent = new Intent(this, CredentialActivity.class);
        }

        startActivity(mainIntent);
        finish();
    }

    private int getCurrentItem(int i) {
        return viewPager.getCurrentItem() + i;
    }



    @Override
    public void onShow() {
        super.showLoading();
    }

    @Override
    public void onHide() {
        super.dismissLoading();
    }

    @Override
    public void onError(String error) {
//        super.showError(error);
        Intent intent = new Intent(this, TimeOutActivity.class);
        intent.putExtra("class", "introduce");
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

    @Override
    public void onSuccessLoad(List<Slide> slides) {
        this.slides = slides;
        super.changeStatusBar();
        addBottomDots(0);
        adapter = new IntroduceAdapter(getApplicationContext(), this.slides);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(mOnPageChangeListener);
    }

    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            addBottomDots(position);

            if (position == (slides.size()-1))
                tvNext.setText(R.string.finish);
            else
                tvNext.setText(R.string.next);

            if (position == 0)
                tvPrevious.setText("");
            else
                tvPrevious.setText(getResources().getText(R.string.previous));
        }

        @Override
        public void onPageSelected(int i) {

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
