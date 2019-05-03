package id.ecodev.wikramabogor.ui.AddSurveyScreen;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FourFragment extends BaseFragment implements BlockingStep {


    public FourFragment() {
        // Required empty public constructor
    }

    public TextView textObjek,textDeskripsi,textKategori;
    public ImageView imageResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_four, container, false);
            textObjek     = view.findViewById(R.id.result_objek);
            textDeskripsi = view.findViewById(R.id.result_deskripsi);
            textKategori  = view.findViewById(R.id.result_kategori);
            imageResult   = view.findViewById(R.id.imageResult);

            textObjek.setText(BaseFragment.object_data);
            textKategori.setText(BaseFragment.category);
            textDeskripsi.setText(BaseFragment.deskripsi_data);
            imageResult.setImageDrawable(Drawable.createFromPath(BaseFragment.photo_data));

        return view;
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

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
}
