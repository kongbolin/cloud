package baidumapsdk.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

/**
 * 婕旂ずMapView鐨勫熀鏈敤娉�
 */
public class BaseMapDemo extends Activity {
	@SuppressWarnings("unused")
	private static final String LTAG = BaseMapDemo.class.getSimpleName();
	private MapView mMapView;
	private BaiduMap mBaiduMap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		if (intent.hasExtra("x") && intent.hasExtra("y")) {
			// 褰撶敤intent鍙傛暟鏃讹紝璁剧疆涓績鐐逛负鎸囧畾鐐�
			Bundle b = intent.getExtras();
			LatLng p = new LatLng(b.getDouble("y"), b.getDouble("x"));
			mMapView = new MapView(this, new BaiduMapOptions().mapStatus(new MapStatus.Builder().target(p).build()));
		} else {
			mMapView = new MapView(this, new BaiduMapOptions());
		}
		setContentView(mMapView);
		mBaiduMap = mMapView.getMap();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// activity 鏆傚仠鏃跺悓鏃舵殏鍋滃湴鍥炬帶浠�
		mMapView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// activity 鎭㈠鏃跺悓鏃舵仮澶嶅湴鍥炬帶浠�
		mMapView.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// activity 閿�姣佹椂鍚屾椂閿�姣佸湴鍥炬帶浠�
		mMapView.onDestroy();
	}

}
