package baidumapsdk.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.share.LocationShareURLOption;
import com.baidu.mapapi.search.share.OnGetShareUrlResultListener;
import com.baidu.mapapi.search.share.PoiDetailShareURLOption;
import com.baidu.mapapi.search.share.ShareUrlResult;
import com.baidu.mapapi.search.share.ShareUrlSearch;
import com.example.cloud.R;

/**
 * 婕旂ずpoi鎼滅储鍔熻兘
 */
public class ShareDemoActivity extends Activity implements
		OnGetPoiSearchResultListener, OnGetShareUrlResultListener,
		OnGetGeoCoderResultListener, BaiduMap.OnMarkerClickListener {

	private MapView mMapView = null;
	private PoiSearch mPoiSearch = null; // 鎼滅储妯″潡锛屼篃鍙幓鎺夊湴鍥炬ā鍧楃嫭绔嬩娇鐢�
	private ShareUrlSearch mShareUrlSearch = null;
	private GeoCoder mGeoCoder = null;
	// 淇濆瓨鎼滅储缁撴灉鍦板潃
	private String currentAddr = null;
	// 鎼滅储鍩庡競
	private String mCity = "鍖椾含";
	// 鎼滅储鍏抽敭瀛�
	private String searchKey = "椁愰";
	// 鍙嶅湴鐞嗙紪璇戠偣鍧愭爣
	private LatLng mPoint = new LatLng(40.056878, 116.308141);
	private BaiduMap mBaiduMap = null;
	private Marker mAddrMarker = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share_demo_activity);
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		mPoiSearch = PoiSearch.newInstance();
		mPoiSearch.setOnGetPoiSearchResultListener(this);
		mShareUrlSearch = ShareUrlSearch.newInstance();
		mShareUrlSearch.setOnGetShareUrlResultListener(this);
		mGeoCoder = GeoCoder.newInstance();
		mGeoCoder.setOnGetGeoCodeResultListener(this);
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
		mPoiSearch.destroy();
		mShareUrlSearch.destroy();
		super.onDestroy();
	}

	public void sharePoi(View view) {
		// 鍙戣捣poi鎼滅储
		mPoiSearch.searchInCity((new PoiCitySearchOption()).city(mCity)
				.keyword(searchKey));
		Toast.makeText(this, "鍦�" + mCity + "鎼滅储 " + searchKey,
				Toast.LENGTH_SHORT).show();
	}

	public void shareAddr(View view) {
		// 鍙戣捣鍙嶅湴鐞嗙紪鐮佽姹�
		mGeoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(mPoint));
		Toast.makeText(
				this,
				String.format("鎼滅储浣嶇疆锛� %f锛�%f", mPoint.latitude, mPoint.longitude),
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onGetPoiResult(PoiResult result) {

		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(ShareDemoActivity.this, "鎶辨瓑锛屾湭鎵惧埌缁撴灉",
					Toast.LENGTH_LONG).show();
			return;
		}
		mBaiduMap.clear();
		PoiShareOverlay poiOverlay = new PoiShareOverlay(mBaiduMap);
		mBaiduMap.setOnMarkerClickListener(poiOverlay);
		poiOverlay.setData(result);
		poiOverlay.addToMap();
		poiOverlay.zoomToSpan();
	}

	@Override
	public void onGetPoiDetailResult(PoiDetailResult result) {

	}

	@Override
	public void onGetPoiDetailShareUrlResult(ShareUrlResult result) {

		// 鍒嗕韩鐭覆缁撴灉
		Intent it = new Intent(Intent.ACTION_SEND);
		it.putExtra(Intent.EXTRA_TEXT, "鎮ㄧ殑鏈嬪弸閫氳繃鐧惧害鍦板浘SDK涓庢偍鍒嗕韩涓�涓綅缃�: " + currentAddr
				+ " -- " + result.getUrl());
		it.setType("text/plain");
		startActivity(Intent.createChooser(it, "灏嗙煭涓插垎浜埌"));

	}

	@Override
	public void onGetLocationShareUrlResult(ShareUrlResult result) {

		// 鍒嗕韩鐭覆缁撴灉
		Intent it = new Intent(Intent.ACTION_SEND);
		it.putExtra(Intent.EXTRA_TEXT, "鎮ㄧ殑鏈嬪弸閫氳繃鐧惧害鍦板浘SDK涓庢偍鍒嗕韩涓�涓綅缃�: " + currentAddr
				+ " -- " + result.getUrl());
		it.setType("text/plain");
		startActivity(Intent.createChooser(it, "灏嗙煭涓插垎浜埌"));

	}

	@Override
	public void onGetGeoCodeResult(GeoCodeResult result) {

	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(ShareDemoActivity.this, "鎶辨瓑锛屾湭鎵惧埌缁撴灉",
					Toast.LENGTH_LONG).show();
			return;
		}
		mBaiduMap.clear();
		mBaiduMap.setOnMarkerClickListener(this);
		mAddrMarker = (Marker) mBaiduMap.addOverlay(new MarkerOptions()
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.icon_marka))
				.title(result.getAddress()).position(result.getLocation()));

	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		if (marker == mAddrMarker) {
			mShareUrlSearch
					.requestLocationShareUrl(new LocationShareURLOption()
							.location(marker.getPosition()).snippet("娴嬭瘯鍒嗕韩鐐�")
							.name(marker.getTitle()));
		}
		return true;
	}

	/**
	 * 浣跨敤PoiOverlay 灞曠ずpoi鐐癸紝鍦╬oi琚偣鍑绘椂鍙戣捣鐭覆璇锋眰.
	 */
	private class PoiShareOverlay extends PoiOverlay {

		public PoiShareOverlay(BaiduMap baiduMap) {
			super(baiduMap);
		}

		@Override
		public boolean onPoiClick(int i) {
			PoiInfo info = getPoiResult().getAllPoi().get(i);
			currentAddr = info.address;
			mShareUrlSearch
					.requestPoiDetailShareUrl(new PoiDetailShareURLOption()
							.poiUid(info.uid));
			return true;
		}
	}
}
