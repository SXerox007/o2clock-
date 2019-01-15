package com.blackdreams.sumitthakur.o2clock.network.gRPC;

import java.util.concurrent.TimeUnit;

import static com.blackdreams.sumitthakur.o2clock.MyApplication.getChannel;

/**
 * Created by sumitthakur on 30/12/18.
 */

public class client {

    public static void shutdown() throws InterruptedException {
        getChannel().shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
}
