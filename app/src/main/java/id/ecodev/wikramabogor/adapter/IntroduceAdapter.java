package id.ecodev.wikramabogor.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import id.ecodev.wikramabogor.Model.Slide;
import id.ecodev.wikramabogor.R;

public class IntroduceAdapter extends PagerAdapter {
    Context context;
    List<Slide> slides = new ArrayList<>();

    public IntroduceAdapter(Context context, List<Slide> slides) {
        this.context = context;
        this.slides = slides;
    }

    @Override
    public int getCount() {
        return slides.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_slide, null);

        Slide slide = slides.get(position);

        ImageView image = view.findViewById(R.id.image_slide);
        TextView tvTitle = view.findViewById(R.id.tv_title_slide);
        TextView tvDesc = view.findViewById(R.id.tv_description_slide);

        Picasso.get()
                .load(slide.getImage())
                .into(image);
        tvTitle.setText(slide.getTitle());
        tvDesc.setText(slide.getDescription());


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
