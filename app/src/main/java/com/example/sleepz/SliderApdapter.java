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
            "Your stomach will let your brain know when it is at it’s maximum capacity by sending signals that say “That’s it!” with a bloat sensation. If you continue to eat you will get a major stomach ache. If you go farther then that… I would assume it would be excruciating as your stomach expands past it’s limit.",
            "Staying up late could be harmful to your health: " +
                    "Linking to high blood sugar; " +
                    "Leading to poor eating habits; " +
                    "Linking to heart disease; " +
                    "Making you sick; " +
                    "Linking to depression; " +
                    "Affecting the amount of sleep you end up getting at night; " +
                    "If you're staying up to study, you're probably not retaining information.",
            "Programmers often work at night because the brain doesn't tend to look around for distractions, the light of a computer screen makes them more alert and because they don't want to be forced to time. Still know that night work will focus and be more effective. However, in the long run, it will have a significant impact on health. So no matter how quiet the work, coders should spend a few hours to rest."
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
