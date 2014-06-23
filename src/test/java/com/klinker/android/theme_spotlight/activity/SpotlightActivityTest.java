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

package com.klinker.android.theme_spotlight.activity;

import com.klinker.android.theme_spotlight.R;
import com.klinker.android.theme_spotlight.fragment.EvolveThemeFragment;
import com.klinker.android.theme_spotlight.fragment.FeaturedThemeFragment;
import com.klinker.android.theme_spotlight.fragment.TalonThemeFragment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class SpotlightActivityTest {

    private SpotlightActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(SpotlightActivity.class).create().get();
    }

    @Test
    public void testCreation() throws Exception {
        assertTrue(activity != null);
    }

    @Test
    public void testActionbarTitle() {
        activity.setupActionbar(0);
        assertEquals("Checking title is evolvesms themes", activity.getTitle(), "EvolveSMS Themes");
        assertEquals("checking icon is evolve", activity.getActionbarIcon(), R.drawable.evolve_logo);

        activity.setupActionbar(1);
        assertEquals("checking title is talon themes", activity.getTitle(), "Talon Themes");
        assertEquals("checking icon is talon", activity.getActionbarIcon(), R.drawable.talon_logo);

        activity.setupActionbar(2);
        assertEquals("checking title is featured themers", activity.getTitle(), "Featured Themers");
        assertEquals("checking icon is featured themers", activity.getActionbarIcon(), R.drawable.featured_logo);
    }

    @Test
    public void testSwitchingFragments() {
        activity.switchFragments(0);
        assertTrue(activity.getCurrentFragment() instanceof EvolveThemeFragment);

        activity.switchFragments(1);
        assertTrue(activity.getCurrentFragment() instanceof TalonThemeFragment);

        activity.switchFragments(2);
        assertTrue(activity.getCurrentFragment() instanceof FeaturedThemeFragment);
    }
}
