package com.lite.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.lite.common.app.MyApplication;
import com.lite.common.http.CustomHttpClient;
import com.loopj.android.http.AsyncHttpClient;
/**
 * @Description:所有fragment的基类
 * @author http://blog.csdn.net/finddreams
 */ 
public abstract class BaseFragment extends Fragment implements OnClickListener {
	protected Context context;     
	public View rootView;
	protected MyApplication myApp;
	protected AsyncHttpClient httpClient;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		initData();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		context = getActivity();
		myApp = (MyApplication) getActivity().getApplication();
		httpClient = CustomHttpClient.getHttpClient();
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView  = initView(inflater);
		return rootView;
	}
	public View getRootView(){
		return rootView;
	}
	@Override
	public void onClick(View arg0) {
	
		
	}
	protected abstract View initView(LayoutInflater inflater);

	protected abstract void initData();

	protected abstract void processClick(View v);

}
