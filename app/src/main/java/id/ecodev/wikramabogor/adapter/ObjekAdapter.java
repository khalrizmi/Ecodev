package id.ecodev.wikramabogor.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import id.ecodev.wikramabogor.Model.Objek;
import id.ecodev.wikramabogor.R;
import id.ecodev.wikramabogor.ui.DetailObjekScreen.DetailObjectActivity;

public class ObjekAdapter extends RecyclerView.Adapter<ObjekAdapter.Holder> {

    List<Objek> objeks = new ArrayList<>();
    Context context;
    View view;

    public ObjekAdapter(Context context) {
        this.context = context;
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView  textName,textDescription,textDate,textVerif, textNote;
        ImageView imageObject;
        CardView cardObject,cardVerif, cardVerif2;
        LinearLayout layoutNote;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.nameObject);
            textDescription = itemView.findViewById(R.id.descriptionObject);
            textDate = itemView.findViewById(R.id.dateObject);
            imageObject = itemView.findViewById(R.id.photoObject);
            cardObject  = itemView.findViewById(R.id.cardObject);
            textVerif   = itemView.findViewById(R.id.textVerified);
            cardVerif   = itemView.findViewById(R.id.cardVerified);
            cardVerif2   = itemView.findViewById(R.id.cardVerified2);
            layoutNote = itemView.findViewById(R.id.layoutNote);
            textNote = itemView.findViewById(R.id.noteObject);
        }
    }

    @NonNull
    @Override
    public ObjekAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(context).inflate(R.layout.list_objek,viewGroup,false);
        return new Holder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final ObjekAdapter.Holder holder, final int i) {
        Picasso.get().load(objeks.get(i).getPhoto()).placeholder(R.drawable.placeholder).into(holder.imageObject, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {

            }
        });

        holder.textName.setText(objeks.get(i).getName());
        holder.textDescription.setText(objeks.get(i).getDescription());
        holder.textDate.setText(objeks.get(i).getCreated_at());
        holder.cardObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail = new Intent(context, DetailObjectActivity.class);
                detail.putExtra("id",objeks.get(i).getId());
                detail.putExtra("image",objeks.get(i).getPhoto());
                detail.putExtra("name",objeks.get(i).getName());
                detail.putExtra("description",objeks.get(i).getDescription());
                detail.putExtra("date",objeks.get(i).getCreated_at());
                detail.putExtra("verified",objeks.get(i).getVerified());
                detail.putExtra("note",objeks.get(i).getNote());
                detail.putExtra("classname",context.getClass().getSimpleName());
                view.getContext().startActivity(detail);
            }
        });

        if (TextUtils.isEmpty(objeks.get(i).getNote())) {
            holder.layoutNote.setVisibility(View.GONE);
        } else {
            holder.textNote.setText(objeks.get(i).getNote());
            holder.layoutNote.setVisibility(View.VISIBLE);
        }
        if (objeks.get(i).getVerified().equals("0")) {
//            holder.cardVerif.setCardBackgroundColor(Color.parseColor("#e04141"));
//            holder.textVerif.setText("Belum di Verifikasi");
            holder.cardVerif2.setVisibility(View.VISIBLE);
            holder.cardVerif.setVisibility(View.GONE);
        } else {
            holder.cardVerif2.setVisibility(View.GONE);
            holder.cardVerif.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return objeks.size();
    }

    public void replace_data(List<Objek> objeks)
    {
        this.objeks = objeks;
        notifyDataSetChanged();
    }

}
