package id.ecodev.wikramabogor.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.ecodev.wikramabogor.Model.Survey;
import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.ui.DetailSurveyScreen.DetailSurveyActivity;

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.Holder> {

    List<Survey> surveys = new ArrayList<>();
    Context context;
    View view;

    public SurveyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(context).inflate(R.layout.list_survey,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.citySurvey.setText(surveys.get(position).getPlace_name());
        holder.addressSurvey.setText(surveys.get(position).getAddress());
        holder.dateSurvey.setText(surveys.get(position).getCreated_at());
        holder.objectCount.setText("Jumlah Objek : "+surveys.get(position).getObjek_count());
        holder.cardClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail = new Intent(context,DetailSurveyActivity.class);
                detail.putExtra("id",surveys.get(position).getId());
                detail.putExtra("city",surveys.get(position).getPlace_name());
                detail.putExtra("address",surveys.get(position).getAddress());
                detail.putExtra("date",surveys.get(position).getCreated_at());
                detail.putExtra("object_count",surveys.get(position).getObjek_count());
                detail.putExtra("lat",surveys.get(position).getLatitude());
                detail.putExtra("long",surveys.get(position).getLongtitude());
                view.getContext().startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return surveys.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView citySurvey,addressSurvey,dateSurvey,objectCount;
        CardView cardClicked;

        public Holder(@NonNull View itemView) {
            super(itemView);
            citySurvey = itemView.findViewById(R.id.citySurvey);
            addressSurvey = itemView.findViewById(R.id.addressSurvey);
            dateSurvey = itemView.findViewById(R.id.dateSurvey);
            objectCount = itemView.findViewById(R.id.objectCount);
            cardClicked = itemView.findViewById(R.id.cardObject);
        }
    }

    public void replace_data(List<Survey> surveys)
    {
        this.surveys = surveys;
        notifyDataSetChanged();
    }
}
