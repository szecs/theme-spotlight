/*
 * Copyright (C) 2014 Klinker Apps, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.klinker.android.theme_spotlight.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.klinker.android.theme_spotlight.R;
import com.klinker.android.theme_spotlight.activity.FeaturedThemerActivity;
import com.klinker.android.theme_spotlight.data.FeaturedThemer;
import com.klinker.android.theme_spotlight.data.NetworkIconLoader;
import com.klinker.android.theme_spotlight.fragment.FeaturedThemerFragment;

public class FeaturedThemerAdapter extends AbstractCachingRecyclerAdapter {

    private static final String TAG = "ThemeAdapter";
    private final FeaturedThemerFragment fragment;
    private final FeaturedThemer[] items;

    public FeaturedThemerAdapter(FeaturedThemerFragment fragment, FeaturedThemer[] items) {
        super();
        this.fragment = fragment;
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = getLayoutInflater().inflate(R.layout.themer_item, parent, false);
        final ViewHolder holder = createViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent featuredThemerIntent = new Intent(fragment.getActivity(), FeaturedThemerActivity.class);
                featuredThemerIntent.putExtra(FeaturedThemerActivity.EXTRA_THEMER_POSITION, holder.position);
                fragment.getActivity().startActivity(featuredThemerIntent);
            }
        });

        return holder;
    }

    public LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(fragment.getActivity());
    }

    public ViewHolder createViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.position = position;

        final FeaturedThemer item = items[position];

        holder.title.setText(item.getName());

        String description = item.getDescription();
        if (description != null) {
            holder.description.setText(item.getDescription());
            holder.description.setVisibility(View.VISIBLE);
        } else {
            holder.description.setVisibility(View.GONE);
        }

        // check if we already have this item stored
        Bitmap icon = getBitmapFromMemCache(item.getIconUrl());
        if (icon != null) {
            holder.icon.setImageBitmap(icon);
        } else {
            holder.icon.setImageDrawable(new ColorDrawable(android.R.color.transparent));

            // start a new thread to download and cache our icon
            NetworkIconLoader loader = new NetworkIconLoader(fragment.getAuthActivity(), item.getIconUrl(), holder.icon, item.getIconUrl());
            new Thread(loader).start();
        }

        if (holder.progressBar.getVisibility() == View.VISIBLE) {
            holder.progressBar.setVisibility(View.GONE);
        }
    }
}
