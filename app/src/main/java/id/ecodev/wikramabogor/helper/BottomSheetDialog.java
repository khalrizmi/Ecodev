package id.ecodev.wikramabogor.helper;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.ecodev.wikramabogor.Model.CustomModel;
import id.ecodev.wikramabogor.Model.Objek;
import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.adapter.ObjekAdapter;
import id.ecodev.wikramabogor.base.BaseFragment;
import id.ecodev.wikramabogor.ui.DetailSurveyScreen.DetailPresenter;
import id.ecodev.wikramabogor.ui.DetailSurveyScreen.DetailView;

@SuppressLint("ValidFragment")
public class BottomSheetDialog extends BottomSheetDialogFragment implements DetailView {

    public String name,email,photo,id;
    private TextView infoName,infoEmail,place_name,address,date;
    private CircleImageView circleImageView;
    private RecyclerView recyclerView;
    private ObjekAdapter objekAdapter;
    BaseFragment baseActivity = new BaseFragment();
    CustomModel customModel;
    DetailPresenter detailPresenter;

    public BottomSheetDialog(CustomModel customModel) {
        this.customModel = customModel;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View contentView = View.inflate(getContext(), R.layout.custom_marker_info,null);

        infoName  = contentView.findViewById(R.id.infoName);
        infoEmail = contentView.findViewById(R.id.emailInfo);
        circleImageView = contentView.findViewById(R.id.infoImage);
        recyclerView = contentView.findViewById(R.id.recyclerTemp);
        place_name = contentView.findViewById(R.id.citySurvey);
        address    = contentView.findViewById(R.id.addressSurvey);
        date       = contentView.findViewById(R.id.dateSurvey);

        infoName.setText(customModel.getName());
        infoEmail.setText(customModel.getEmail());
        Picasso.get().load(customModel.getPhoto()).into(circleImageView);
        place_name.setText(customModel.getPlace_name());
        address.setText(customModel.getAddress());
        date.setText(customModel.getCreated_at());

        detailPresenter = new DetailPresenter(this);

        objekAdapter = new ObjekAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(objekAdapter);

        detailPresenter.loadObjek(customModel.getId());


        dialog.setContentView(contentView);


    }

    @Override
    public void onSuccessLoadObjek(List<Objek> objeks) {
        objekAdapter.replace_data(objeks);
    }

    @Override
    public void onShow() {
        baseActivity.showLoading(getContext());
    }

    @Override
    public void onHide() {
        baseActivity.dismissLoading();
    }

    @Override
    public void onError(String error) {
        baseActivity.showError(error);
    }

    @Override
    public void getMessage(String message) {
        baseActivity.showMessage(message);
    }

    @Override
    public void getHttp(String http) {
        baseActivity.showHttp(http);
    }
}
