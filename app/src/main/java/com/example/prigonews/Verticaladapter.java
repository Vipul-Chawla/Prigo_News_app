package com.example.prigonews;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Verticaladapter extends RecyclerView.Adapter<Verticaladapter.MyVeriviewholder> {
    ArrayList<veri_data_class.Article>list = new ArrayList();
    Context context;

    public Verticaladapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Verticaladapter.MyVeriviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_item,parent,false);
        return new MyVeriviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Verticaladapter.MyVeriviewholder holder, int position) {
            holder.title.setText(list.get(position).getTitle());
            holder.description.setText(list.get(position).getDescription());
            holder.source.setText(list.get(position).getSource().name);
            holder.published_date.setText(list.get(position).getPublishedAt());
            holder.url = list.get(position).getUrl();
            String image = list.get(position).getUrlToImage();
        Glide.with(holder.itemView.getContext()).load(image).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyVeriviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView title;
        TextView description;
        TextView source;
        TextView published_date;
        String url;
        public MyVeriviewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ver_image);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            source = itemView.findViewById(R.id.source);
            published_date = itemView.findViewById(R.id.date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                    CustomTabsIntent.Builder builder= new CustomTabsIntent.Builder();
                    CustomTabsIntent customTabsIntent = builder.build();
                    customTabsIntent.launchUrl(context, Uri.parse(url));

        }
    }

    void updatelist(ArrayList<veri_data_class.Article> list1)
    {
        this.list = list1;
        notifyDataSetChanged();
    }
}
