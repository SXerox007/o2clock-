package com.blackdreams.sumitthakur.o2clock;

/**
 * Created by sumitthakur on 25/07/18.
 */

import android.app.Application;
import android.content.Context;

import com.blackdreams.sumitthakur.o2clock.util.Config;

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
        setChannel();
    }

    public static void setChannel(){
        channel = OkHttpChannelBuilder.forAddress(Config.getCurrentAppHost(getAppContext()), Config.getCurrentAppPort(getAppContext()))
                .usePlaintext()
                .build();
    }

}
