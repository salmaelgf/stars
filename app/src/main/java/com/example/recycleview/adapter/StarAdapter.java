package com.example.recycleview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.recycleview.R;
import com.example.recycleview.beans.Star;

import java.util.ArrayList;
import java.util.List;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> implements Filterable {

    private static final String TAG = "StarAdapter";
    private List<Star> stars;
    private List<Star> starsFiltered;
    private Context context;

    public StarAdapter(Context context, List<Star> stars) {
        this.stars = stars;
        this.starsFiltered = new ArrayList<>(stars); // Initialisation de la liste filtrée
        this.context = context;
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.star_item, viewGroup, false);
        return new StarViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder starViewHolder, int i) {
        Log.d(TAG, "onBindView call ! " + i);

        Star currentStar = starsFiltered.get(i); // Utilisation de la liste filtrée

        // Charger l'image avec Glide
        Glide.with(context)
                .asBitmap()
                .load(currentStar.getImg())
                .apply(new RequestOptions().override(100, 100))
                .into(starViewHolder.img);

        // Définir les autres informations
        starViewHolder.name.setText(currentStar.getName().toUpperCase());
        starViewHolder.stars.setRating(currentStar.getStar());
        starViewHolder.idss.setText(String.valueOf(currentStar.getId()));
    }

    @Override
    public int getItemCount() {
        return starsFiltered.size(); // Retourne la taille de la liste filtrée
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Star> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(stars);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Star star : stars) {
                        if (star.getName().toLowerCase().contains(filterPattern)) {
                            filteredList.add(star);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                results.count = filteredList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                starsFiltered.clear();
                starsFiltered.addAll((List<Star>) results.values);
                notifyDataSetChanged();
            }
        };
    }

    public static class StarViewHolder extends RecyclerView.ViewHolder {
        TextView idss;
        ImageView img;
        TextView name;
        RatingBar stars;
        RelativeLayout parent;

        public StarViewHolder(@NonNull View itemView) {
            super(itemView);
            idss = itemView.findViewById(R.id.ids);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            stars = itemView.findViewById(R.id.stars);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
