package baidumapsdk.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.example.cloud.R;

/**
 * 婕旂ず鍦板浘鍥惧眰鏄剧ず鐨勬帶鍒舵柟娉�
 */
public class LayersDemo extends Activity {

	/**
	 * MapView 鏄湴鍥句富鎺т欢
	 */
	private MapView mMapView;
	private BaiduMap mBaiduMap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layers);
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
	}

	/**
	 * 璁剧疆搴曞浘鏄剧ず妯″紡
	 * 
	 * @param view
	 */
	public void setMapMode(View view) {
		boolean checked = ((RadioButton) view).isChecked();
		switch (view.getId()) {
		case R.id.normal:
			if (checked)
				mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
			break;
		case R.id.statellite:
			if (checked)
				mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
			break;
		}
	}

	/**
	 * 璁剧疆鏄惁鏄剧ず浜ら�氬浘
	 * 
	 * @param view
	 */
	public void setTraffic(View view) {
		mBaiduMap.setTrafficEnabled(((CheckBox) view).isChecked());
	}

	/**
	 * 璁剧疆鏄惁鏄剧ず鐧惧害鐑姏鍥�
	 * 
	 * @param view
	 */
	public void setBaiduHeatMap(View view) {
		mBaiduMap.setBaiduHeatMapEnabled(((CheckBox) view).isChecked());
	}
	
	@Override
	protected void onPause() {
		// MapView鐨勭敓鍛藉懆鏈熶笌Activity鍚屾锛屽綋activity鎸傝捣鏃堕渶璋冪敤MapView.onPause()
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		// MapView鐨勭敓鍛藉懆鏈熶笌Activity鍚屾锛屽綋activity鎭㈠鏃堕渶璋冪敤MapView.onResume()
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// MapView鐨勭敓鍛藉懆鏈熶笌Activity鍚屾锛屽綋activity閿�姣佹椂闇�璋冪敤MapView.destroy()
		mMapView.onDestroy();
		super.onDestroy();
	}

}
