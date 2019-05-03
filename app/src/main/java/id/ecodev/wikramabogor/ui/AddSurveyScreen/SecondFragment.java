package id.ecodev.wikramabogor.ui.AddSurveyScreen;


import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.io.IOException;
import java.util.List;

import id.ecodev.wikramabogor.Model.Category;
import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.TimeOutActivity;
import id.ecodev.wikramabogor.base.BaseFragment;
import id.ecodev.wikramabogor.helper.PrefManager;
import id.ecodev.wikramabogor.ui.PrepareSurveyScreen.PrepareActivity;
import id.zelory.compressor.Compressor;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends BaseFragment implements BlockingStep,AddSurveyView {


    public SecondFragment() {
        // Required empty public constructor
    }

    View view;
    MaterialEditText object_data,specification_data;
    ImageView imageObject,imageBlank;
    Button btnNext;
    TextView tvTake;
    File file,compressedImage;
    String myFile,mediaPath;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    TextView textObjek,textDeskripsi,textKategori;
    ImageView imageResult;
    Button btnTrue,btnFalse;
    private static int REQUEST_CODE = 1;
    AddSurveyPresenter addSurveyPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_second, container, false);
        init();
        objectClicked();

        addSurveyPresenter = new AddSurveyPresenter(this);

        return view;

    }


    private void init()
    {
        object_data        = view.findViewById(R.id.object_data);
        specification_data = view.findViewById(R.id.specification_data);
        imageObject        = view.findViewById(R.id.image_object);
        imageBlank         = view.findViewById(R.id.noimage_profile);
        tvTake             = view.findViewById(R.id.tv_take);
        btnNext            = view.findViewById(R.id.btnNext);

    }

    private void objectClicked() {
        imageBlank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture(REQUEST_CODE);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate())
                {
                    BaseFragment.object_data = object_data.getText().toString();
                    BaseFragment.deskripsi_data = specification_data.getText().toString();
                    BaseFragment.photo_data = mediaPath;
                    dialog = new AlertDialog.Builder(getActivity());
                    inflater = getLayoutInflater();
                    dialogView = inflater.inflate(R.layout.fragment_four, null);

                    textObjek     = dialogView.findViewById(R.id.result_objek);
                    textDeskripsi = dialogView.findViewById(R.id.result_deskripsi);
                    textKategori  = dialogView.findViewById(R.id.result_kategori);
                    imageResult   = dialogView.findViewById(R.id.imageResult);
                    btnTrue       = dialogView.findViewById(R.id.btnTrue);
                    btnFalse      = dialogView.findViewById(R.id.btnFalse);

                    textObjek.setText(object_data.getText().toString());
                    textKategori.setText(BaseFragment.category);
                    textDeskripsi.setText(specification_data.getText().toString());
                    imageResult.setImageDrawable(Drawable.createFromPath(mediaPath));

                    dialog.setView(dialogView);
                    dialog.setCancelable(true);
                    final AlertDialog alertDialog = dialog.show();

                    btnTrue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            PrefManager prefManager = new PrefManager(getActivity());
                            addSurveyPresenter.createObjek(BaseFragment.category_id,String.valueOf(prefManager.getUserId()),BaseFragment.object_data,BaseFragment.deskripsi_data,BaseFragment.photo_data);
                        }
                    });

                    btnFalse.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });
                }
            }
        });
    }


    private boolean validate() {
        if (TextUtils.isEmpty(object_data.getText().toString().trim())) {
            object_data.setError("Field harus diisi!");
        } else if (TextUtils.isEmpty(specification_data.getText().toString().trim())) {
            specification_data.setError("Field harus diisi");
        } else if (mediaPath == null || mediaPath.equals("")) {
            Toast.makeText(getContext(), "Silahkan ambil gambar terlebih dahulu!", Toast.LENGTH_SHORT).show();
        } else {
            return true;
        }

        return false;
    }

    private void takePicture(int req) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            Uri photoURI = null;
            file = getFile();
            photoURI = FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".id.ecodev.wikramabogor", file);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                takePictureIntent.setClipData(ClipData.newRawUri("", photoURI));
                takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            startActivityForResult(takePictureIntent, req);
        }
    }

    private File getFile() {
        File folder = new File(Environment.getExternalStorageDirectory() + "/EcoDev/");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File imageFile = new File(folder, "picture_.jpg");
        myFile = imageFile.getName();
        return imageFile;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String folder = Environment.getExternalStorageDirectory() + "/EcoDev/";
                try {
                    compressedImage = new Compressor(getContext())
                            .setMaxWidth(600)
                            .setMaxHeight(1200)
                            .setQuality(75)
                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                            .setDestinationDirectoryPath(Environment.getExternalStorageDirectory() + "/EcoDev/")
                            .compressToFile(file, "picture.jpg");
                    file.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPath = folder + compressedImage.getName();

                imageObject.setImageDrawable(Drawable.createFromPath(mediaPath));
                imageObject.setVisibility(View.VISIBLE);
                imageBlank.setVisibility(View.GONE);
                tvTake.setVisibility(View.GONE);
            }
        }
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

    @Override
    public void onSuccessLoadCategories(List<Category> categories) {

    }

    @Override
    public void onSuccessCreateObjek(String result) {
        if (result.equals("success"))
        {
            startActivity(new Intent(getActivity(), PrepareActivity.class));
//            new SurveyActivity().onBackPressed();
        }else if(result.equals("bad_name")){
            Toast.makeText(getActivity(), "Nama Objek mengandung kata terlarang", Toast.LENGTH_SHORT).show();
        }else if (result.equals("bad_desc")){
            Toast.makeText(getActivity(), "Deskripsi Objek mengandung kata terlarang", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
        }
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
        super.showError(error);
        Intent intent = new Intent(getActivity(), TimeOutActivity.class);
        intent.putExtra("class", "secondFragment");
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
