package com.example.sleepz;

import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class tips_content_activity extends AppCompatActivity {
    private ViewPager SlideViewPager;
    private LinearLayout dot_layout;
    private SliderApdapter sliderApdapter;
    private TextView[] dots;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_content_layout);
        SlideViewPager = (ViewPager) findViewById(R.id.slide_viewpager);
        dot_layout = (LinearLayout) findViewById(R.id.dots_layout);

        sliderApdapter = new SliderApdapter(this);
        SlideViewPager.setAdapter(sliderApdapter);
        addDots(0);
        SlideViewPager.addOnPageChangeListener(viewListener);
    }
    public void addDots(int position){
        dots = new TextView[3];
        dot_layout.removeAllViews();
        for (int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.transpanrentWhite));
            dot_layout.addView(dots[i]);
        }
        if (dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.title_color));
        }
    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
