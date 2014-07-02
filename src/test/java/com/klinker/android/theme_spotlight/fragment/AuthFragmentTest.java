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

package com.klinker.android.theme_spotlight.fragment;

import com.klinker.android.theme_spotlight.AbstractSpotlightTest;
import org.junit.Test;
import org.robolectric.util.FragmentTestUtil;

import static junit.framework.Assert.assertNotNull;

public class AuthFragmentTest extends AbstractSpotlightTest {

    @Test
    public void testCreateFragment() throws Exception {
        AuthFragment fragment = AuthFragment.newInstance();
        FragmentTestUtil.startFragment(fragment);
        assertNotNull(fragment);
        assertNotNull(fragment.getAuthActivity());
    }
}