package baidumapsdk.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.UiSettings;
import com.example.cloud.R;

/**
 * 婕旂ず鍦板浘UI鎺у埗鍔熻兘
 */
public class UISettingDemo extends Activity {

	/**
	 * MapView 鏄湴鍥句富鎺т欢
	 */
	private MapView mMapView;
	private BaiduMap mBaiduMap;
	private UiSettings mUiSettings;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uisetting);

		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		mUiSettings = mBaiduMap.getUiSettings();

		MapStatus ms = new MapStatus.Builder().overlook(-30).build();
		MapStatusUpdate u = MapStatusUpdateFactory.newMapStatus(ms);
		mBaiduMap.animateMapStatus(u, 1000);
	}

	/**
	 * 鏄惁鍚敤缂╂斁鎵嬪娍
	 * 
	 * @param v
	 */
	public void setZoomEnable(View v) {
		mUiSettings.setZoomGesturesEnabled(((CheckBox) v).isChecked());
	}

	/**
	 * 鏄惁鍚敤骞崇Щ鎵嬪娍
	 * 
	 * @param v
	 */
	public void setScrollEnable(View v) {
		mUiSettings.setScrollGesturesEnabled(((CheckBox) v).isChecked());
	}

	/**
	 * 鏄惁鍚敤鏃嬭浆鎵嬪娍
	 * 
	 * @param v
	 */
	public void setRotateEnable(View v) {
		mUiSettings.setRotateGesturesEnabled(((CheckBox) v).isChecked());
	}

	/**
	 * 鏄惁鍚敤淇鎵嬪娍
	 * 
	 * @param v
	 */
	public void setOverlookEnable(View v) {
		mUiSettings.setOverlookingGesturesEnabled(((CheckBox) v).isChecked());
	}

	/**
	 * 鏄惁鍚敤鎸囧崡閽堝浘灞�
	 * 
	 * @param v
	 */
	public void setCompassEnable(View v) {
		mUiSettings.setCompassEnabled(((CheckBox) v).isChecked());
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
