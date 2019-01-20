package com.blackdreams.sumitthakur.o2clock.ui.home.chat;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.base.BaseActivity;

public class ChatActivity extends BaseActivity implements View.OnClickListener  {

    private RecyclerView rvMessages;
    private AppCompatEditText etMsgInput;
    private AppCompatButton btnSend;

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
    }

    @Override
    public void onClick(View view) {

    }


    /**
     * when open scroll to bottom
     */
    private void scrollToBottom() {
        // mMessagesView.scrollToPosition(mAdapter.getItemCount() - 1);
    }

}
