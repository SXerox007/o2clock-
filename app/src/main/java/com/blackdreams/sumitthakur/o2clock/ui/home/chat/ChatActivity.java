package com.blackdreams.sumitthakur.o2clock.ui.home.chat;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.adapter.ChatAdapter;
import com.blackdreams.sumitthakur.o2clock.base.BaseActivity;
import com.blackdreams.sumitthakur.o2clock.util.dialog.CustomAlertDialog;

import chatpb.Chat;

import static com.blackdreams.sumitthakur.o2clock.Constants.AppConstants.CHAT_ID;
import static com.blackdreams.sumitthakur.o2clock.Constants.AppConstants.IS_GROUP;
import static com.blackdreams.sumitthakur.o2clock.Constants.AppConstants.RECIVER_ID;
import static com.blackdreams.sumitthakur.o2clock.Constants.AppConstants.RECIVER_NAME;
import static com.blackdreams.sumitthakur.o2clock.Constants.AppConstants.SENDER_ID;
import static com.blackdreams.sumitthakur.o2clock.Constants.AppConstants.SENDER_NAME;

public class ChatActivity extends BaseActivity implements View.OnClickListener,ChatView  {

    private AppCompatEditText etMsgInput;
    private AppCompatButton btnSend;
    private RecyclerView rvMessages;
    private ChatAdapter adapter;
    private Dialog mDialog;
    private ChatPresenter chatPresenter;
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chatPresenter = new ChatPresenterImpl(this);
        init();
    }

    private void init() {
        bundle = getIntent().getExtras();
        rvMessages = findViewById(R.id.rvMessages);
        etMsgInput = findViewById(R.id.etMsgInput);
        btnSend = findViewById(R.id.btnSend);
        ((AppCompatTextView)findViewById(R.id.toolbar_title)).setText(bundle.getString(RECIVER_NAME, "Unknown"));
        btnSend.setOnClickListener(this);
        recyclerViewInit();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSend:
                Chat.ChatMessage message;
                if(bundle.getBoolean(IS_GROUP,false)){
                    //if group message
                    message = Chat.ChatMessage.newBuilder().build();
                }else {
                    if (!chatPresenter.msgEmptyValidation(etMsgInput)) {
                        message = Chat.ChatMessage.newBuilder()
                                .setSenderid(bundle.getString(SENDER_ID, "Unknown"))
                                .setMessage(etMsgInput.getText().toString())
                                .setSenderName(bundle.getString(SENDER_NAME, "Unknown"))
                                .setIsGroupMessage(bundle.getBoolean(IS_GROUP, false))
                                .setSingleMessage(Chat.SingleMessage.newBuilder()
                                        .setReciverName(bundle.getString(RECIVER_NAME, "Unknown"))
                                        .setReciverId(bundle.getString(RECIVER_ID, "Unknown")
                                        ).build())
                                .setChatId(bundle.getString(CHAT_ID, "Unknown"))
                                .build();
                        chatPresenter.msgSend(message);
                    }else{
                        return;
                    }
                }
                adapter.addMessage(message);
                etMsgInput.setText("");
                break;
             default:
        }
    }


    /**
     *  initilize
     */
    private void recyclerViewInit() {
        adapter = new ChatAdapter();
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        rvMessages = findViewById(R.id.rvMessages);
        rvMessages.setLayoutManager(mLinearLayoutManager);
        rvMessages.setAdapter(adapter);
        adapter.init(bundle.getString(SENDER_ID,""),bundle.getString(SENDER_NAME,"")
                ,bundle.getString(RECIVER_ID,""),bundle.getString(RECIVER_NAME,"")
                ,bundle.getString(CHAT_ID,""),bundle.getBoolean(IS_GROUP,false));
    }



    /**
     * when open scroll to bottom
     */
    private void scrollToBottom() {
         rvMessages.scrollToPosition(adapter.getItemCount() - 1);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(final String msg, final String title) {
        mDialog = new CustomAlertDialog.Builder(this)
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

    @Override
    public void onMessageRecived(final Chat.ChatMessage message) {
        adapter.addMessage(message);
    }
}
