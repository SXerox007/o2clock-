package com.blackdreams.sumitthakur.o2clock.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blackdreams.sumitthakur.o2clock.R;
import com.blackdreams.sumitthakur.o2clock.fragment.home.drawer.chat.allusers.AllUsersPresenter;
import com.blackdreams.sumitthakur.o2clock.fragment.home.drawer.chat.allusers.AllUsersPresenterImpl;
import com.blackdreams.sumitthakur.o2clock.fragment.home.drawer.chat.allusers.AllUsersView;
import com.blackdreams.sumitthakur.o2clock.util.GlideUtils;
import com.blackdreams.sumitthakur.o2clock.util.dialog.CustomAlertDialog;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import chatpb.Chat;

import static com.blackdreams.sumitthakur.o2clock.MyApplication.getAppContext;

/**
 * Developer: sumitthakur
 * Date: 16/01/19
 */

public class AllUsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AllUsersView {
    private Context mContext;
    private AllUsersPresenter allUsersPresenter;
    private Dialog mDialog;
    private int position;
    private List<Chat.User> data = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        mContext = parent.getContext();
        View itemView = li.inflate(R.layout.item_all_users, parent, false);
        return new ItemViewHolder(itemView);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        itemViewHolder.tvUserName.setText(data.get(position).getUserName());
        Glide.with(getAppContext()).load(mContext.getDrawable(R.drawable.ic_placeholder)).apply(GlideUtils.setCircularImage()).into(itemViewHolder.ivUser);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void showToast(String text) {

    }

    @Override
    public void showSnackBar(final String text, final View view) {

    }

    @Override
    public void onSuccess(final List<chatpb.Chat.User> data) {
        this.data = data;
        Log.d("Data",String.valueOf(data));
        notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg, String title) {
        mDialog = new CustomAlertDialog.Builder(mContext)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(false)
                .setNegativeButton(mContext.getString(R.string.text_ok),
                        new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                            @Override
                            public void onClick() {
                                mDialog.dismiss();
                            }
                        })
                .show();
    }

    @Override
    public void onLoginUserDataSuccess(final Chat.User sender) {
        allUsersPresenter.startChat(sender,data.get(position));
    }

    @Override
    public void onP2PChatConnectionStartSuccess() {
        //start the chat activity

    }


    /**
     * View holder class
     */
    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private AppCompatImageView ivUser;
        private AppCompatTextView tvUserName;
        private LinearLayout llChatItem;

        /**
         * Instantiates a new Item view holder.
         *
         * @param itemView the item view
         */
        ItemViewHolder(final View itemView) {
            super(itemView);
            ivUser = itemView.findViewById(R.id.ivUser);
            tvUserName = itemView.findViewById(R.id.tvUserName);
             itemView.findViewById(R.id.llChatItem).setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     allUsersPresenter.getLoginUserData();
                     position = getAdapterPosition();
                 }
             });

        }
    }

    public void init(){
        allUsersPresenter = new AllUsersPresenterImpl(this);
        allUsersPresenter.getAllUsersAndGroups();
    }

}
