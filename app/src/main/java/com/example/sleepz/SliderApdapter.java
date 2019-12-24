package com.example.sleepz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.zip.Inflater;

public class SliderApdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    public SliderApdapter(Context context){
        this.context = context;
    }
    //arrays image
    public int [] slideImages = {
            R.drawable.eating,
            R.drawable.owl,
            R.drawable.coding
    };
    // arrays headings
    public String [] slide_headings = {
            "DONT'T EAT TOO MUCH",
            "TRY NOT TO SLEEP LATE",
            "DON'T CODE AT NIGHT"
    };
    // arrays descriptions
    public String [] slide_desc = {
            "orem Ipsum is simply dummy text of the printing and typesetting industry",
            "orem Ipsum is simply dummy text of the printing and typesetting industry",
            "orem Ipsum is simply dummy text of the printing and typesetting industry"
    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView heading = (TextView) view.findViewById(R.id.heading);
        TextView desc = (TextView) view.findViewById(R.id.description);

        imageView.setImageResource(slideImages[position]);
        heading.setText(slide_headings[position]);
        desc.setText(slide_desc[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
