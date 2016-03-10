package com.example.dell.chihuobao.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.chihuobao.R;


public class SettingFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.setting, container, false);
      /*  SettingsItemView settings_connect_manager= (SettingsItemView) view.findViewById(R.id.settings_connect_manager);
        settings_connect_manager.setOnSettingClickListener(new SettingsItemView.settingClickListener() {
            @Override
            public void rightClick() {
                Toast.makeText(getActivity(), "a", Toast.LENGTH_SHORT).show();
            }
        });*/
		return view;

	}

}
