package com.gmail.s8521444.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gmail.s8521444.R;
import com.gmail.s8521444.model.Message;
import com.gmail.s8521444.model.User;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder> {

    private static final int TYPE_MESSAGE = 0;
    private static final int TYPE_USERS_LIST = 1;

    private final Context context;
    private final List<Message> messages;
    private final List<User> users;
    private final View.OnClickListener listener;

    public MessagesAdapter(final Context context,
                           final List<Message> messages,
                           final List<User> users,
                           final View.OnClickListener listener) {
        this.context = context;
        this.messages = messages;
        this.users = users;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < messages.size())
            return TYPE_MESSAGE;
        else
            return TYPE_USERS_LIST;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == TYPE_MESSAGE) {
            return new MessageViewHolder(
                    LayoutInflater.from(context)
                            .inflate(R.layout.view_message, viewGroup, false),
                    MessageViewHolder.ItemType.MESSAGE,
                    listener);
        } else {
            return new MessageViewHolder(
                    LayoutInflater.from(context)
                            .inflate(R.layout.view_users_list, viewGroup, false),
                    MessageViewHolder.ItemType.USERS_LIST,
                    listener);
        }
    }

    @Override
    public void onBindViewHolder(MessageViewHolder viewHolder, int i) {
        if (viewHolder.type == MessageViewHolder.ItemType.MESSAGE)
            setUpMessageItem(viewHolder, i);
        else
            setUpUsersItem(viewHolder, i);
    }

    private void setUpUsersItem(final MessageViewHolder viewHolder, final int position) {
        for (int i = 0; i < users.size(); i++) {
            final User user = users.get(i);

            final LinearLayout column = i % 2 == 0 ?
                    viewHolder.leftColumn : viewHolder.rightColumn;

            final View userView = LayoutInflater.from(context)
                    .inflate(R.layout.view_user, column, false);

            Glide.with(context).load(user.getImgLink())
                    .into((ImageView) userView.findViewById(R.id.userpic));
            ((TextView) userView.findViewById(R.id.username)).setText(user.getName());

            userView.setContentDescription(String.valueOf(i));
            userView.setOnClickListener(listener);
            column.addView(userView);
        }
    }

    private void setUpMessageItem(final MessageViewHolder viewHolder, final int i) {
        final Message message = messages.get(i);
        viewHolder.userpic.setImageDrawable(null);
        final User user = findUserById(message.getFrom());
        if (user != null) {
            Glide.with(context).load(user.getImgLink()).into(viewHolder.userpic);
            viewHolder.username.setText(user.getName());
        }
        viewHolder.message.setText(message.getText());
    }

    private User findUserById(final String from) {
        for (User user : users)
            if (user.getId().equals(from))
                return user;
        return null;
    }

    @Override
    public int getItemCount() {
        return messages.size() + 1;
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {

        public final ItemType type;

        public ImageView userpic;
        public TextView username;
        public TextView message;
        public LinearLayout leftColumn;
        public LinearLayout rightColumn;

        public enum ItemType {
            MESSAGE,
            USERS_LIST
        }

        public MessageViewHolder(final View itemView,
                                 final ItemType type,
                                 final View.OnClickListener listener) {
            super(itemView);
            this.type = type;

            if (type == ItemType.MESSAGE) {
                userpic = (ImageView) itemView.findViewById(R.id.userpic);
                username = (TextView) itemView.findViewById(R.id.username);
                message = (TextView) itemView.findViewById(R.id.message);
                itemView.setOnClickListener(listener);
            } else {
                leftColumn = (LinearLayout) itemView.findViewById(R.id.left_column);
                rightColumn = (LinearLayout) itemView.findViewById(R.id.right_column);
            }
        }
    }
}
