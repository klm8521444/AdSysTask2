package com.gmail.s8521444.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.s8521444.R;
import com.gmail.s8521444.adapters.MessagesAdapter;
import com.gmail.s8521444.model.Message;
import com.gmail.s8521444.model.User;
import com.gmail.s8521444.other.AlertHelper;
import com.gmail.s8521444.view.DividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MessagesFragment extends Fragment implements View.OnClickListener {

    private ArrayList<Message> messages;
    private ArrayList<User> users;

    private RecyclerView recyclerView;

    public static MessagesFragment newInstance() {
        return new MessagesFragment();
    }

    public MessagesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_messages, container, false);

        prepareData();
        setUpUi(rootView);

        return rootView;
    }

    private void setUpUi(final View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.VERTICAL,
                false));
        recyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(new MessagesAdapter(getActivity(), messages, users, this));
    }

    @Override
    public void onClick(View v) {
        final int position;
        switch (v.getId()) {
            case R.id.message_item:
                position = recyclerView.getChildLayoutPosition(v);
                AlertHelper.showAlertDialog(getActivity(),
                        R.string.message_pressed,
                        messages.get(position).getText());
                break;
            case R.id.user_item:
                position = Integer.parseInt(String.valueOf(v.getContentDescription()));
                AlertHelper.showAlertDialog(getActivity(),
                        R.string.user_pressed,
                        users.get(position).getName());
                break;
        }
    }

    private void prepareData() {
        final String messagesJson = "[" +
                "{\"Message\":\"Hi, what's up?\",\"From\":\"UserID1\"}," +
                "{\"From\":\"UserID2\",\"Message\":\"ok, how are you?\"}" +
                "]";

        final String usersJson = "[" +
                "{\"img\":\"http://devtest.ad-sys.com/android/01.jpg\",\"name\":\"Joe\",\"id\":\"UserID1\"}," +
                "{\"name\":\"Lyla\",\"id\":\"UserID2\",\"img\":\"http://devtest.ad-sys.com/android/02.jpg\"}," +
                "{\"img\":\"http://devtest.ad-sys.com/android/03.jpg\",\"name\":\"Ronit\",\"id\":\"UserID3\"}," +
                "{\"id\":\"UserID4\",\"name\":\"Nazer\",\"img\":\"http://devtest.ad-sys.com/android/04.jpg\"}," +
                "{\"name\":\"Katia\",\"id\":\"UserID5\",\"img\":\"http://devtest.ad-sys.com/android/05.jpg\"}," +
                "{\"id\":\"UserID6\",\"name\":\"Sam\",\"img\":\"http://devtest.ad-sys.com/android/06.jpg\"}" +
                "]";

        parseMessages(messagesJson);
        parseUsers(usersJson);
    }

    private ArrayList<Message> parseMessages(final String messagesJson) {
        final String FROM = "From";
        final String MESSAGE = "Message";

        messages = new ArrayList<>();
        try {
            final JSONArray array = new JSONArray(messagesJson);
            for (int i = 0; i < array.length(); i++) {
                final JSONObject json = array.getJSONObject(i);
                messages.add(new Message(json.getString(FROM), json.getString(MESSAGE)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return messages;
    }

    private ArrayList<User> parseUsers(final String usersJson) {
        final String ID = "id";
        final String NAME = "name";
        final String IMG = "img";

        users = new ArrayList<>();
        try {
            final JSONArray array = new JSONArray(usersJson);
            for (int i = 0; i < array.length(); i++) {
                final JSONObject json = array.getJSONObject(i);
                users.add(new User(json.getString(ID), json.getString(NAME), json.getString(IMG)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return users;
    }

}
