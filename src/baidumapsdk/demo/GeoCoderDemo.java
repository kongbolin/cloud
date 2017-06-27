package baidumapsdk.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.example.cloud.R;

/**
 * 姝emo鐢ㄦ潵灞曠ず濡備綍杩涜鍦扮悊缂栫爜鎼滅储锛堢敤鍦板潃妫�绱㈠潗鏍囷級銆佸弽鍦扮悊缂栫爜鎼滅储锛堢敤鍧愭爣妫�绱㈠湴鍧�锛�
 */
public class GeoCoderDemo extends Activity implements
		OnGetGeoCoderResultListener {
	GeoCoder mSearch = null; // 鎼滅储妯″潡锛屼篃鍙幓鎺夊湴鍥炬ā鍧楃嫭绔嬩娇鐢�
	BaiduMap mBaiduMap = null;
	MapView mMapView = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_geocoder);
		CharSequence titleLable = "鍦扮悊缂栫爜鍔熻兘";
		setTitle(titleLable);

		// 鍦板浘鍒濆鍖�
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();

		// 鍒濆鍖栨悳绱㈡ā鍧楋紝娉ㄥ唽浜嬩欢鐩戝惉
		mSearch = GeoCoder.newInstance();
		mSearch.setOnGetGeoCodeResultListener(this);
	}

	/**
	 * 鍙戣捣鎼滅储
	 * 
	 * @param v
	 */
	public void SearchButtonProcess(View v) {
		if (v.getId() == R.id.reversegeocode) {
			EditText lat = (EditText) findViewById(R.id.lat);
			EditText lon = (EditText) findViewById(R.id.lon);
			LatLng ptCenter = new LatLng((Float.valueOf(lat.getText()
					.toString())), (Float.valueOf(lon.getText().toString())));
			// 鍙岹eo鎼滅储
			mSearch.reverseGeoCode(new ReverseGeoCodeOption()
					.location(ptCenter));
		} else if (v.getId() == R.id.geocode) {
			EditText editCity = (EditText) findViewById(R.id.city);
			EditText editGeoCodeKey = (EditText) findViewById(R.id.geocodekey);
			// Geo鎼滅储
			mSearch.geocode(new GeoCodeOption().city(
					editCity.getText().toString()).address(
					editGeoCodeKey.getText().toString()));
		}
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		mMapView.onDestroy();
		mSearch.destroy();
		super.onDestroy();
	}

	@Override
	public void onGetGeoCodeResult(GeoCodeResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(GeoCoderDemo.this, "鎶辨瓑锛屾湭鑳芥壘鍒扮粨鏋�", Toast.LENGTH_LONG)
					.show();
			return;
		}
		mBaiduMap.clear();
		mBaiduMap.addOverlay(new MarkerOptions().position(result.getLocation())
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.icon_marka)));
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
				.getLocation()));
		String strInfo = String.format("绾害锛�%f 缁忓害锛�%f",
				result.getLocation().latitude, result.getLocation().longitude);
		Toast.makeText(GeoCoderDemo.this, strInfo, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(GeoCoderDemo.this, "鎶辨瓑锛屾湭鑳芥壘鍒扮粨鏋�", Toast.LENGTH_LONG)
					.show();
			return;
		}
		mBaiduMap.clear();
		mBaiduMap.addOverlay(new MarkerOptions().position(result.getLocation())
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.icon_marka)));
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
				.getLocation()));
		Toast.makeText(GeoCoderDemo.this, result.getAddress(),
				Toast.LENGTH_LONG).show();

	}

}
