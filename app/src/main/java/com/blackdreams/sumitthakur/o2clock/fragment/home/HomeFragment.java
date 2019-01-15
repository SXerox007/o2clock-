package com.blackdreams.sumitthakur.o2clock.fragment.home;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.base.BaseFragment;
import com.blackdreams.sumitthakur.o2clock.util.GlideUtils;
import com.blackdreams.sumitthakur.o2clock.util.Util;
import com.blackdreams.sumitthakur.o2clock.util.dialog.CustomAlertDialog;
import com.blackdreams.sumitthakur.o2clock.util.imagepicker.ImageChooser;
import com.bumptech.glide.Glide;
import com.google.android.gms.fitness.data.Device;
import com.kbeanie.multipicker.api.entity.ChosenImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import static com.blackdreams.sumitthakur.o2clock.MyApplication.getAppContext;


/**
 * Created by sumitthakur on 03/01/19.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener
        , ImageChooser.OnImageSelectListener,HomeFragmentView{


    private AppCompatImageView ivUserPicture ;
    private ImageChooser imageChooser;
    private File imageFile;
    private Dialog mDialog;
    private HomeFragmentPresenter homeFragmentPresenter;
    private View view;



    /**
     * Creating Instance of Fragment
     *
     * @return Instance of Fragment
     */
    public static Fragment newInstance() {
        return new HomeFragment();
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        init(view);
        homeFragmentPresenter = new HomeFragmentPresenterImpl(this);
        return view;
    }

    /**
     *  initilize
     * @param view view
     */
    private void init(View view) {
        ivUserPicture = view.findViewById(R.id.ivUserPicture);
        Util.setOnClickListener(this,ivUserPicture);
        imageChooser = new ImageChooser(new ImageChooser.Builder(this));

    }


    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.ivUserPicture:
                openCamera();
                break;
        }
    }

    private void openCamera(){
        imageChooser.selectImage(this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageChooser.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void loadImage(List<ChosenImage> list) {
        imageFile = new File(list.get(0).getOriginalPath());
        showImage();
        homeFragmentPresenter.verifyUser(saveBitmapToFile(imageFile));
    }

    @Override
    public void croppedImage(File mCroppedImage) {

    }

    @Override
    public void showToast(String text) {

    }

    @Override
    public void showSnackBar(String text, View view) {

    }

    @Override
    public void onVerifyUserSuccess() {
        //success TODO
        view.findViewById(R.id.tvshift).setVisibility(View.VISIBLE);
    }

    @Override
    public void onVerifyUserError(String msg, String title) {
        mDialog = new CustomAlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(false)
                .setNegativeButton(getString(R.string.text_ok),
                        new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                            @Override
                            public void onClick() {
                                mDialog.dismiss();
                            }
                        })
                .show();
    }


    private void showImage(){
        Glide.with(getAppContext()).load(imageFile).apply(GlideUtils.setCircularImage()).into(ivUserPicture);

    }


    public File saveBitmapToFile(File file){
        try {
            // BitmapFactory options to downsize the image
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            o.inSampleSize = 6;
            // factor of downsizing the image

            FileInputStream inputStream = new FileInputStream(file);
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();

            // The new size we want to scale to
            final int REQUIRED_SIZE=200;        // x............

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            inputStream = new FileInputStream(file);

            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
            inputStream.close();

            // here i override the original image file
            File outPutFile = File.createTempFile("abc","image");
            FileOutputStream outputStream = new FileOutputStream(outPutFile);
            // y.......
            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 50 , outputStream);

            return outPutFile;
        } catch (Exception e) {
            return null;
        }
    }
}
