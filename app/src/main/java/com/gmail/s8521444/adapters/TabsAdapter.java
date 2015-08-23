package com.gmail.s8521444.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.gmail.s8521444.R;
import com.gmail.s8521444.fragments.MessagesFragment;
import com.gmail.s8521444.fragments.SimpleFragment;

public class TabsAdapter extends FragmentStatePagerAdapter {

    private final Context context;

    public TabsAdapter(final FragmentManager fm, final Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return MessagesFragment.newInstance();
        else
            return SimpleFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        final int[] imageResId = {
                R.drawable.ic_messages,
                R.drawable.ic_profile,
                R.drawable.ic_tags
        };

        final Drawable image = context.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        final SpannableString sb = new SpannableString(" ");
        final ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}
