package com.ancientgames.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{
    private Context context;

    private ArrayList<String> Namelist;
    ///private ArrayList<String> DOBlist;
    private ArrayList<String> Agelist;
 //   ArrayList<String> Profilelist;
 private ArrayList<String> Ranklist;

    class SearchViewHolder extends RecyclerView.ViewHolder {

      //  ImageView profileImage;  DOB,
        TextView Name, Age ,Rank;

        private SearchViewHolder(View itemView) {
            super(itemView);

           // profileImage = (ImageView) itemView.findViewById(R.id.profileImage);
            Name = (TextView) itemView.findViewById(R.id.Name);
       //     DOB = (TextView) itemView.findViewById(R.id.DOB);
            Age = (TextView) itemView.findViewById(R.id.Age);
            Rank = (TextView) itemView.findViewById(R.id.Rank);
        }
    }

    public SearchAdapter(Context context, ArrayList<String> namelist, ArrayList<String> agelist, ArrayList<String> ranklist) {
        this.context = context;
        this.Namelist = namelist;
       // this.DOBlist = DOBlist; , ArrayList<String> DOBlist
        this.Agelist = agelist;
     //   this.Profilelist = profilelist; , ArrayList<String> profilelist
        this.Ranklist = ranklist;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_items, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.Name.setText(Namelist.get(position));
     //   holder.DOB.setText(DOBlist.get(position));
        holder.Age.setText(Agelist.get(position));
        holder.Rank.setText(Ranklist.get(position));


    }
    @Override
    public int getItemCount() {
        return Namelist.size();
    }
}
