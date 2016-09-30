package com.yury.goal.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.yury.goal.NewProject;
import com.yury.goal.R;
import com.yury.goal.adapters.ProjectAdapter;

/**
 * Created by deyvidyury on 6/09/16.
 */
public class ProjectFragment extends Fragment {
    private ProjectAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project,container,false);
        Log.d("projectFramgment","onCreateView");
//        if(getArguments() != null){
//            String project = getArguments().getString("project");
//            setProject(project);
//        }
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        if(getArguments() != null){
//            String project = getArguments().getString("project");
//            setProject(project);
//        }
    }


}
