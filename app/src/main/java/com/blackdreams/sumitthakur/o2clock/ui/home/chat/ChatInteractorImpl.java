package com.blackdreams.sumitthakur.o2clock.ui.home.chat;

import java.util.concurrent.CountDownLatch;

import chatpb.Chat;
import chatpb.ChatRoomGrpc;
import io.grpc.stub.StreamObserver;

import static com.blackdreams.sumitthakur.o2clock.MyApplication.getChannel;

/**
 * Developer: sumitthakur
 * Date: 22/01/19
 */

public class ChatInteractorImpl implements ChatInteractor {

    private ChatView chatView;

    ChatInteractorImpl(final ChatView chatView) {
        this.chatView = chatView;
    }

    @Override
    public void onSendButtonPress(final Chat.ChatMessage request) {
        ChatRoomGrpc.ChatRoomStub asyncClient = ChatRoomGrpc.newStub(getChannel());
        final CountDownLatch latch = new CountDownLatch(1);
        StreamObserver<Chat.ChatMessage> requestObserver = asyncClient.chat(new StreamObserver<Chat.ChatMessage>() {
            @Override
            public void onNext(Chat.ChatMessage message) {
                chatView.onMessageRecived(message);
            }

            @Override
            public void onError(Throwable t) {
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                System.out.println("Server is done sending messaage");
            }
        });
        requestObserver.onNext(request);
        requestObserver.onCompleted();
    }
}
