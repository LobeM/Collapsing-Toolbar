package com.musonda.toolbarani.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.musonda.toolbarani.model.Desset;
import com.musonda.toolbarani.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DessertAdapter extends RecyclerView.Adapter<DessertAdapter.DessertVh> {

    private List<Desset> mDessets;
    private Context mContext;

    public DessertAdapter(Context context) {
        mContext = context;

        mDessets = Desset.prepareDesserts(
                context.getResources().getStringArray(R.array.dessert_names),
                context.getResources().getStringArray(R.array.dessert_descriptions));
    }

    public static class DessertVh extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mDescription;
        private TextView mFirstLetter;

        public DessertVh(@NonNull View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.txt_name);
            mDescription = itemView.findViewById(R.id.txt_desc);
            mFirstLetter = itemView.findViewById(R.id.txt_firstletter);
        }
    }

    @NonNull
    @Override
    public DessertVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_dessert, parent, false);
        return new DessertAdapter.DessertVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DessertVh holder, int position) {
        Desset desset = mDessets.get(position);

        holder.mName.setText(desset.getName());
        holder.mDescription.setText(desset.getDescription());
        holder.mFirstLetter.setText(String.valueOf(desset.getFirstLetter()));
    }

    @Override
    public int getItemCount() {
        return mDessets == null ? 0 : mDessets.size();
    }
}
