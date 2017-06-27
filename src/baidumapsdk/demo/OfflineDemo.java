package baidumapsdk.demo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.baidu.mapapi.map.offline.MKOfflineMapListener;
import com.example.cloud.R;

public class OfflineDemo extends Activity implements MKOfflineMapListener {

	private MKOfflineMap mOffline = null;
	private TextView cidView;
	private TextView stateView;
	private EditText cityNameView;
	/**
	 * 宸蹭笅杞界殑绂荤嚎鍦板浘淇℃伅鍒楄〃
	 */
	private ArrayList<MKOLUpdateElement> localMapList = null;
	private LocalMapAdapter lAdapter = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_offline);
		mOffline = new MKOfflineMap();
		mOffline.init(this);
		initView();

	}

	private void initView() {

		cidView = (TextView) findViewById(R.id.cityid);
		cityNameView = (EditText) findViewById(R.id.city);
		stateView = (TextView) findViewById(R.id.state);

		ListView hotCityList = (ListView) findViewById(R.id.hotcitylist);
		ArrayList<String> hotCities = new ArrayList<String>();
		// 鑾峰彇鐑椆鍩庡競鍒楄〃
		ArrayList<MKOLSearchRecord> records1 = mOffline.getHotCityList();
		if (records1 != null) {
			for (MKOLSearchRecord r : records1) {
				hotCities.add(r.cityName + "(" + r.cityID + ")" + "   --"
						+ this.formatDataSize(r.size));
			}
		}
		ListAdapter hAdapter = (ListAdapter) new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, hotCities);
		hotCityList.setAdapter(hAdapter);

		ListView allCityList = (ListView) findViewById(R.id.allcitylist);
		// 鑾峰彇鎵�鏈夋敮鎸佺绾垮湴鍥剧殑鍩庡競
		ArrayList<String> allCities = new ArrayList<String>();
		ArrayList<MKOLSearchRecord> records2 = mOffline.getOfflineCityList();
		if (records1 != null) {
			for (MKOLSearchRecord r : records2) {
				allCities.add(r.cityName + "(" + r.cityID + ")" + "   --"
						+ this.formatDataSize(r.size));
			}
		}
		ListAdapter aAdapter = (ListAdapter) new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, allCities);
		allCityList.setAdapter(aAdapter);

		LinearLayout cl = (LinearLayout) findViewById(R.id.citylist_layout);
		LinearLayout lm = (LinearLayout) findViewById(R.id.localmap_layout);
		lm.setVisibility(View.GONE);
		cl.setVisibility(View.VISIBLE);

		// 鑾峰彇宸蹭笅杩囩殑绂荤嚎鍦板浘淇℃伅
		localMapList = mOffline.getAllUpdateInfo();
		if (localMapList == null) {
			localMapList = new ArrayList<MKOLUpdateElement>();
		}

		ListView localMapListView = (ListView) findViewById(R.id.localmaplist);
		lAdapter = new LocalMapAdapter();
		localMapListView.setAdapter(lAdapter);

	}

	/**
	 * 鍒囨崲鑷冲煄甯傚垪琛�
	 * 
	 * @param view
	 */
	public void clickCityListButton(View view) {
		LinearLayout cl = (LinearLayout) findViewById(R.id.citylist_layout);
		LinearLayout lm = (LinearLayout) findViewById(R.id.localmap_layout);
		lm.setVisibility(View.GONE);
		cl.setVisibility(View.VISIBLE);

	}

	/**
	 * 鍒囨崲鑷充笅杞界鐞嗗垪琛�
	 * 
	 * @param view
	 */
	public void clickLocalMapListButton(View view) {
		LinearLayout cl = (LinearLayout) findViewById(R.id.citylist_layout);
		LinearLayout lm = (LinearLayout) findViewById(R.id.localmap_layout);
		lm.setVisibility(View.VISIBLE);
		cl.setVisibility(View.GONE);
	}

	/**
	 * 鎼滅储绂荤嚎闇�甯�
	 * 
	 * @param view
	 */
	public void search(View view) {
		ArrayList<MKOLSearchRecord> records = mOffline.searchCity(cityNameView
				.getText().toString());
		if (records == null || records.size() != 1)
			return;
		cidView.setText(String.valueOf(records.get(0).cityID));
	}

	/**
	 * 寮�濮嬩笅杞�
	 * 
	 * @param view
	 */
	public void start(View view) {
		int cityid = Integer.parseInt(cidView.getText().toString());
		mOffline.start(cityid);
		clickLocalMapListButton(null);
		Toast.makeText(this, "寮�濮嬩笅杞界绾垮湴鍥�. cityid: " + cityid, Toast.LENGTH_SHORT)
				.show();
		updateView();
	}

	/**
	 * 鏆傚仠涓嬭浇
	 * 
	 * @param view
	 */
	public void stop(View view) {
		int cityid = Integer.parseInt(cidView.getText().toString());
		mOffline.pause(cityid);
		Toast.makeText(this, "鏆傚仠涓嬭浇绂荤嚎鍦板浘. cityid: " + cityid, Toast.LENGTH_SHORT)
				.show();
		updateView();
	}

	/**
	 * 鍒犻櫎绂荤嚎鍦板浘
	 * 
	 * @param view
	 */
	public void remove(View view) {
		int cityid = Integer.parseInt(cidView.getText().toString());
		mOffline.remove(cityid);
		Toast.makeText(this, "鍒犻櫎绂荤嚎鍦板浘. cityid: " + cityid, Toast.LENGTH_SHORT)
				.show();
		updateView();
	}

	/**
	 * 浠嶴D鍗″鍏ョ绾垮湴鍥惧畨瑁呭寘
	 * 
	 * @param view
	 */
	public void importFromSDCard(View view) {
		int num = mOffline.importOfflineData();
		String msg = "";
		if (num == 0) {
			msg = "娌℃湁瀵煎叆绂荤嚎鍖咃紝杩欏彲鑳芥槸绂荤嚎鍖呮斁缃綅缃笉姝ｇ‘锛屾垨绂荤嚎鍖呭凡缁忓鍏ヨ繃";
		} else {
			msg = String.format("鎴愬姛瀵煎叆 %d 涓绾垮寘锛屽彲浠ュ湪涓嬭浇绠＄悊鏌ョ湅", num);
		}
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		updateView();
	}

	/**
	 * 鏇存柊鐘舵�佹樉绀�
	 */
	public void updateView() {
		localMapList = mOffline.getAllUpdateInfo();
		if (localMapList == null) {
			localMapList = new ArrayList<MKOLUpdateElement>();
		}
		lAdapter.notifyDataSetChanged();
	}

	@Override
	protected void onPause() {
		int cityid = Integer.parseInt(cidView.getText().toString());
		MKOLUpdateElement temp = mOffline.getUpdateInfo(cityid);
		if (temp != null && temp.status == MKOLUpdateElement.DOWNLOADING) {
			mOffline.pause(cityid);
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	public String formatDataSize(int size) {
		String ret = "";
		if (size < (1024 * 1024)) {
			ret = String.format("%dK", size / 1024);
		} else {
			ret = String.format("%.1fM", size / (1024 * 1024.0));
		}
		return ret;
	}

	@Override
	protected void onDestroy() {
		/**
		 * 閫�鍑烘椂锛岄攢姣佺绾垮湴鍥炬ā鍧�
		 */
		mOffline.destroy();
		super.onDestroy();
	}

	@Override
	public void onGetOfflineMapState(int type, int state) {
		switch (type) {
		case MKOfflineMap.TYPE_DOWNLOAD_UPDATE: {
			MKOLUpdateElement update = mOffline.getUpdateInfo(state);
			// 澶勭悊涓嬭浇杩涘害鏇存柊鎻愮ず
			if (update != null) {
				stateView.setText(String.format("%s : %d%%", update.cityName,
						update.ratio));
				updateView();
			}
		}
			break;
		case MKOfflineMap.TYPE_NEW_OFFLINE:
			// 鏈夋柊绂荤嚎鍦板浘瀹夎
			Log.d("OfflineDemo", String.format("add offlinemap num:%d", state));
			break;
		case MKOfflineMap.TYPE_VER_UPDATE:
			// 鐗堟湰鏇存柊鎻愮ず
			// MKOLUpdateElement e = mOffline.getUpdateInfo(state);

			break;
		}

	}

	/**
	 * 绂荤嚎鍦板浘绠＄悊鍒楄〃閫傞厤鍣�
	 */
	public class LocalMapAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return localMapList.size();
		}

		@Override
		public Object getItem(int index) {
			return localMapList.get(index);
		}

		@Override
		public long getItemId(int index) {
			return index;
		}

		@Override
		public View getView(int index, View view, ViewGroup arg2) {
			MKOLUpdateElement e = (MKOLUpdateElement) getItem(index);
			view = View.inflate(OfflineDemo.this,
					R.layout.offline_localmap_list, null);
			initViewItem(view, e);
			return view;
		}

		void initViewItem(View view, final MKOLUpdateElement e) {
			Button display = (Button) view.findViewById(R.id.display);
			Button remove = (Button) view.findViewById(R.id.remove);
			TextView title = (TextView) view.findViewById(R.id.title);
			TextView update = (TextView) view.findViewById(R.id.update);
			TextView ratio = (TextView) view.findViewById(R.id.ratio);
			ratio.setText(e.ratio + "%");
			title.setText(e.cityName);
			if (e.update) {
				update.setText("鍙洿鏂�");
			} else {
				update.setText("鏈�鏂�");
			}
			if (e.ratio != 100) {
				display.setEnabled(false);
			} else {
				display.setEnabled(true);
			}
			remove.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					mOffline.remove(e.cityID);
					updateView();
				}
			});
			display.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.putExtra("x", e.geoPt.longitude);
					intent.putExtra("y", e.geoPt.latitude);
					intent.setClass(OfflineDemo.this, BaseMapDemo.class);
					startActivity(intent);
				}
			});
		}

	}

}