package com.yury.goal.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yury.goal.R;
import com.yury.goal.adapters.TasksAdapter;

/**
 * Created by deyvidyury on 6/09/16.
 */
public class TasksFragment extends Fragment {
    private ListView listView;
    private TasksAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tasks_fragment,container,false);

        // List view
//        listView = (ListView)view.findViewById(R.id.listViewTasks);
//        adapter = new TasksAdapter(getActivity());
//        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }
}
