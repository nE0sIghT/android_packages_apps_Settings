/*
 * Copyright (C) 2017 Xperia Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package com.android.settings.xosp;
 
import android.app.Activity;
import android.app.ActivityManagerNative;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.support.v7.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceGroup;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.provider.MediaStore;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;
import android.view.IWindowManager;
import android.view.Display;
import android.view.Window;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.R;
import com.android.settings.Utils;
import com.android.internal.logging.MetricsProto.MetricsEvent;

public class About extends SettingsPreferenceFragment 
        implements OnPreferenceChangeListener {
    
    Preference mSiteUrl;
    Preference mDownloadsSiteUrl;
    Preference mStatisticsSiteUrl;
    Preference mJenkinsSiteUrl;
    Preference mSourceUrl;
    Preference mGoogleUrl;
    Preference mFacebookUrl;
    //Preference mTranslationsUrl;
    Preference mDonateUrl;
    Preference mTelegramChannelUrl;
    Preference mTelegramGroupUrl;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.about_xosp);
        PreferenceScreen prefSet = getPreferenceScreen();
        ContentResolver resolver = getContentResolver();
        mSiteUrl = findPreference("xosp_site");
        mDownloadsSiteUrl = findPreference("xosp_downloads");
        mStatisticsSiteUrl = findPreference("xosp_statistics");
        mJenkinsSiteUrl = findPreference("xosp_jenkins");
        mSourceUrl = findPreference("xosp_source");
        mGoogleUrl = findPreference("xosp_google_plus");
        mFacebookUrl = findPreference("xosp_facebook");
        mDonateUrl = findPreference("xosp_donate");
        //mTranslationsUrl = findPreference("xosp_translations");
        mTelegramChannelUrl = findPreference("xosp_telegram_channel");
        mTelegramGroupUrl = findPreference("xosp_telegram_group");
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        return false;
    }
    
    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference == mSiteUrl) {
            launchUrl("https://xosp.org");
        } else if (preference == mDownloadsSiteUrl) {
            launchUrl("https://downloads.xosp.org");
        } else if(preference == mStatisticsSiteUrl) {
            launchUrl("https://stats.xosp.org");
        } else if (preference == mJenkinsSiteUrl) {
            launchUrl("http://jenkins.xosp.org");
        } else if (preference == mSourceUrl) {
            launchUrl("https://github.com/XOSP-Project");
        } else if (preference == mGoogleUrl) {
            launchUrl("https://plus.google.com/u/0/communities/117671498272072664538");
        } else if (preference == mFacebookUrl) {
            launchUrl("https://www.facebook.com/xosprom");
        } else if (preference == mDonateUrl) {
            launchUrl("https://www.paypal.me/xosp");
        }/* else if (preference == mTranslationsUrl){
            launchUrl("https://os4fvts.oneskyapp.com/admin/project/dashboard/project/136264");
        }*/ else if (preference == mTelegramChannelUrl){
            launchUrl("https://t.me/xosp_channel");
        } else if (preference == mTelegramGroupUrl){
            launchUrl("https://t.me/XOSPCommunity");
        }  else {
            // If not handled, let preferences handle it.
            return super.onPreferenceTreeClick(preference);
        }
         return true; 
    }
    private void launchUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uriUrl);
        getActivity().startActivity(intent);
    }
    
    @Override
    protected int getMetricsCategory() {
        return MetricsEvent.XOSP;
    }
}
