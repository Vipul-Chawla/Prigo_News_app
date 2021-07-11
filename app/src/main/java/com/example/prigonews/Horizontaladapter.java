package com.example.prigonews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Horizontaladapter extends RecyclerView.Adapter<Horizontaladapter.Myhoriviewholder> {
    private ArrayList<Hori_data_class> list ;
    private onCategoryclick moncategoryclick;
    public Horizontaladapter(ArrayList<Hori_data_class> list,onCategoryclick moncategoryclick) {
        this.list = list;
        this.moncategoryclick = moncategoryclick;
    }

    @NonNull
    @Override
    public Myhoriviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_item,parent,false);
        return new Myhoriviewholder(view,moncategoryclick);
    }

    @Override
    public void onBindViewHolder(@NonNull Myhoriviewholder holder, int position) {
            holder.text.setText(list.get(position).getCategory());
            holder.image.setImageResource(list.get(position).getImage_id());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myhoriviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text;
        ImageView image;
        onCategoryclick oncategoryclick;
        public Myhoriviewholder(@NonNull View itemView,onCategoryclick oncategoryclick) {
            super(itemView);
            image = itemView.findViewById(R.id.hori_image);
            text = itemView.findViewById(R.id.category_name);
            this.oncategoryclick = oncategoryclick;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            oncategoryclick.newsonclick(getAbsoluteAdapterPosition());
        }
    }
    public interface onCategoryclick{
        void newsonclick(int position);

    }
}
