package com.yury.goal.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.yury.goal.NewProject;
import com.yury.goal.R;
import com.yury.goal.ProjectActivity;
import com.yury.goal.adapters.ProjectsAdapter;
import com.yury.goal.classes.Project;

/**
 * Created by deyvidyury on 6/09/16.
 */
public class ProjectsFragment extends Fragment {
    private ListView listView;
    private ProjectsAdapter adapater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.projects_fragment,container,false);

        //ListView
        listView = (ListView)view.findViewById(R.id.listViewProjects);
        adapater = new ProjectsAdapter(getActivity());
        listView.setAdapter(adapater);
        listView.setOnItemClickListener(onItemClickProject());

        // Create the Add project menu
        setHasOptionsMenu(true);

        return view;
    }

    private AdapterView.OnItemClickListener onItemClickProject(){
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProjectsAdapter adapter = (ProjectsAdapter) parent.getAdapter();
                Project project = (Project)adapter.getItem(position);
                Intent intent = new Intent(getActivity(),ProjectActivity.class);
                intent.putExtra("project",project.getName());
                intent.putExtra("position",position);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapater.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.newProject){
            Intent intent = new Intent(getActivity(), NewProject.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
