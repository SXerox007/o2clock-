package com.blackdreams.sumitthakur.o2clock.fragment.home;

import android.util.Log;

import com.blackdreams.sumitthakur.o2clock.R;
import com.google.protobuf.ByteString;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import homepb.Home;
import homepb.VerifyServiceGrpc;
import io.grpc.stub.StreamObserver;

import static com.blackdreams.sumitthakur.o2clock.MyApplication.getAppContext;
import static com.blackdreams.sumitthakur.o2clock.MyApplication.getChannel;

/**
 * Created by sumitthakur on 03/01/19.
 */

public class HomeFragmentInteractorImpl implements HomeFragmentInteractor {
    private HomeFragmentView homeFragmentView;

    HomeFragmentInteractorImpl(HomeFragmentView homeFragmentView) {
        this.homeFragmentView = homeFragmentView;
    }

    @Override
    public void onCameraTap(final File file, final String accessToken) {
        VerifyServiceGrpc.VerifyServiceStub asyncClient = VerifyServiceGrpc.newStub(getChannel());
        StreamObserver<Home.VerifyUserRequest> req = streamResponse(asyncClient);
        try {
            BufferedInputStream bInputStream = new BufferedInputStream(new FileInputStream(file));
            int bufferSize = 1024 * 1024; // 1MB
            byte[] buffer = new byte[bufferSize];
            int tmp = 0;
            int size = 0;
            try {
                while ((tmp = bInputStream.read(buffer)) > 0) {
                    size += tmp;
                    ByteString byteString = ByteString.copyFrom(buffer,0,tmp);
                    req.onNext(Home.VerifyUserRequest
                            .newBuilder()
                            .setFileChunk(byteString)
                            .build());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        req.onCompleted();
    }


    private StreamObserver<Home.VerifyUserRequest> streamResponse(final VerifyServiceGrpc.VerifyServiceStub asyncClient){
        return asyncClient.userVerifyService(new StreamObserver<Home.VerifyUserResponse>() {
            @Override
            public void onNext(Home.VerifyUserResponse value) {
                Log.d("Final Data:",value.getMessage());

            }

            @Override
            public void onError(Throwable t) {
                Log.e("Error",t.getMessage());
                homeFragmentView.onVerifyUserError(t.getMessage(),getAppContext().getString(R.string.error_title));

            }

            @Override
            public void onCompleted() {
                Log.d("Final Data:","Success");
                //homeFragmentView.onVerifyUserSuccess();
                homeFragmentView.onVerifyUserSuccess();
            }
        });

    }
}
