package com.blackdreams.sumitthakur.o2clock.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blackdreams.sumitthakur.o2clock.R;

import java.util.ArrayList;
import java.util.List;

import chatpb.Chat;

/**
 * Developer: sumitthakur
 * Date: 21/01/19
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Context mContext;
    private List<Chat.ChatMessage> messageList;
    private String senderId,senderName,reciverId,reciverName,chatId;
    private Boolean isGroup;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        mContext = parent.getContext();
        View itemView;
        switch (viewType) {
            case 0:
                 itemView = li.inflate(R.layout.item_my_msg, parent, false);
                break;
            case 1:
                 itemView = li.inflate(R.layout.item_all_msg, parent, false);
                break;
             default:
                 itemView = li.inflate(null, parent, false);
        }
        return new ChatAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int pos) {
        final ChatAdapter.ItemViewHolder itemViewHolder = (ChatAdapter.ItemViewHolder) viewHolder;
        itemViewHolder.message.setText(messageList.get(pos).getMessage().toString());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return messageList.get(position).getSenderid().equals(senderId) ? 0 : 1;
    }


    /**
     * View holder class
     */
    private class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView message;
        /**
         * Instantiates a new Item view holder.
         *
         * @param itemView the item view
         */
        ItemViewHolder(final View itemView) {
            super(itemView);
            message  = itemView.findViewById(R.id.message);
        }
    }


    public void addMessage(final Chat.ChatMessage message){
        this.messageList.add(message);
        notifyItemInserted(messageList.size());
    }


    public void init(final String senderId,final String senderName,
                     final String reciverId,final String reciverName,
                     final  String chatId,final  boolean isGroup){
        this.senderId = senderId;
        this.senderName = senderName;
        this.reciverId = reciverId;
        this.reciverName = reciverName;
        this.chatId = chatId;
        this.isGroup = isGroup;
        this.messageList = new ArrayList<>();

    }

}
