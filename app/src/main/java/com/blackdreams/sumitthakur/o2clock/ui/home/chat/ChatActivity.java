package com.blackdreams.sumitthakur.o2clock.ui.home.chat;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.adapter.ChatAdapter;
import com.blackdreams.sumitthakur.o2clock.base.BaseActivity;

import chatpb.Chat;

import static com.blackdreams.sumitthakur.o2clock.Constants.AppConstants.CHAT_ID;
import static com.blackdreams.sumitthakur.o2clock.Constants.AppConstants.IS_GROUP;
import static com.blackdreams.sumitthakur.o2clock.Constants.AppConstants.RECIVER_ID;
import static com.blackdreams.sumitthakur.o2clock.Constants.AppConstants.RECIVER_NAME;
import static com.blackdreams.sumitthakur.o2clock.Constants.AppConstants.SENDER_ID;
import static com.blackdreams.sumitthakur.o2clock.Constants.AppConstants.SENDER_NAME;

public class ChatActivity extends BaseActivity implements View.OnClickListener  {

    private AppCompatEditText etMsgInput;
    private AppCompatButton btnSend;
    private RecyclerView rvMessages;
    private ChatAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        init();
    }

    private void init() {
        rvMessages = findViewById(R.id.rvMessages);
        etMsgInput = findViewById(R.id.etMsgInput);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
        recyclerViewInit();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSend:
                Bundle bundle = getIntent().getExtras();
                Chat.ChatMessage message;
                if(bundle.getBoolean(IS_GROUP,false)){
                    //if group message
                    message = Chat.ChatMessage.newBuilder().build();
                }else{
                     message = Chat.ChatMessage.newBuilder()
                            .setSenderid(bundle.getString(SENDER_ID,""))
                            .setMessage(etMsgInput.getText().toString())
                            .setSenderName(bundle.getString(SENDER_NAME,"Unknown"))
                            .setIsGroupMessage(bundle.getBoolean(IS_GROUP,false))
                             .setSingleMessage(Chat.SingleMessage.newBuilder()
                                     .setChatId(bundle.getString(CHAT_ID,""))
                                     .setReciverId(bundle.getString(RECIVER_ID,"")).build())
                             .setChatId(bundle.getString(CHAT_ID,""))
                            .build();
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
        Bundle bundle = getIntent().getExtras();
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

}
