package id.ecodev.wikramabogor.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import id.ecodev.wikramabogor.Model.Category;
import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.base.BaseFragment;
import id.ecodev.wikramabogor.ui.SurveyScreen.SurveyActivity;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Holder> {

    List<Category> categories = new ArrayList<>();
    Context context;

    public CategoryAdapter(Context context) {
        this.context = context;
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView iconCategory;
        TextView textTitle;
        LinearLayout cardIcon;
        public Holder(@NonNull View itemView) {
            super(itemView);
            iconCategory = itemView.findViewById(R.id.icon_category);
            textTitle    = itemView.findViewById(R.id.text_title);
            cardIcon     = itemView.findViewById(R.id.cardIcon);
        }
    }

    @NonNull
    @Override
    public CategoryAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_list,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.Holder holder, final int i) {
        Picasso.get().load(categories.get(i).getIcon_category()).into(holder.iconCategory);
        holder.textTitle.setText(categories.get(i).getTitle());
        holder.cardIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SurveyActivity.stepperLayout.setCurrentStepPosition(1);
                BaseFragment.category_id = categories.get(i).getId();
                BaseFragment.category    = categories.get(i).getTitle();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void replace_data(List<Category> categories){
        this.categories = categories;
        notifyDataSetChanged();
    }
}
