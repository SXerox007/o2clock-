package com.blackdreams.sumitthakur.o2clock;

/**
 * Created by sumitthakur on 25/07/18.
 */

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

import io.grpc.ManagedChannel;
import io.grpc.okhttp.OkHttpChannelBuilder;
import io.paperdb.Paper;


/**
 *
 * <p>
 * The Application class
 */

public class MyApplication extends Application {

    private static WeakReference<Context> mWeakReference;
    private static ManagedChannel channel;

    /**
     * Getter to access Singleton instance
     *
     * @return instance of MyApplication
     */
    public static Context getAppContext() {
        return mWeakReference.get();
    }


    public static ManagedChannel getChannel(){
        return channel;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Paper.init(this);
        mWeakReference = new WeakReference<Context>(this);
        channel = OkHttpChannelBuilder.forAddress("192.168.0.111", 50051)
                .usePlaintext()
                .build();
    }

}
