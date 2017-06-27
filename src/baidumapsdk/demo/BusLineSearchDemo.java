package baidumapsdk.demo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.SupportMapFragment;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.BusLineOverlay;
import com.baidu.mapapi.search.busline.BusLineResult;
import com.baidu.mapapi.search.busline.BusLineSearch;
import com.baidu.mapapi.search.busline.BusLineSearchOption;
import com.baidu.mapapi.search.busline.OnGetBusLineSearchResultListener;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.example.cloud.R;

/**
 * 姝emo鐢ㄦ潵灞曠ず濡備綍杩涜鍏氦绾胯矾璇︽儏妫�绱紝骞朵娇鐢≧outeOverlay鍦ㄥ湴鍥句笂缁樺埗
 * 鍚屾椂灞曠ず濡備綍娴忚璺嚎鑺傜偣骞跺脊鍑烘场娉�
 */
public class BusLineSearchDemo extends FragmentActivity
		implements OnGetPoiSearchResultListener, OnGetBusLineSearchResultListener, BaiduMap.OnMapClickListener {
	private Button mBtnPre = null;// 涓婁竴涓妭鐐�
	private Button mBtnNext = null;// 涓嬩竴涓妭鐐�
	private int nodeIndex = -2;// 鑺傜偣绱㈠紩,渚涙祻瑙堣妭鐐规椂浣跨敤
	private BusLineResult route = null;// 淇濆瓨椹捐溅/姝ヨ璺嚎鏁版嵁鐨勫彉閲忥紝渚涙祻瑙堣妭鐐规椂浣跨敤
	private List<String> busLineIDList = null;
	private int busLineIndex = 0;
	// 鎼滅储鐩稿叧
	private PoiSearch mSearch = null; // 鎼滅储妯″潡锛屼篃鍙幓鎺夊湴鍥炬ā鍧楃嫭绔嬩娇鐢�
	private BusLineSearch mBusLineSearch = null;
	private BaiduMap mBaiduMap = null;
	BusLineOverlay overlay;// 鍏氦璺嚎缁樺埗瀵硅薄

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_busline);
		CharSequence titleLable = "鍏氦绾胯矾鏌ヨ鍔熻兘";
		setTitle(titleLable);
		mBtnPre = (Button) findViewById(R.id.pre);
		mBtnNext = (Button) findViewById(R.id.next);
		mBtnPre.setVisibility(View.INVISIBLE);
		mBtnNext.setVisibility(View.INVISIBLE);
		mBaiduMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.bmapView)).getBaiduMap();
		mBaiduMap.setOnMapClickListener(this);
		mSearch = PoiSearch.newInstance();
		mSearch.setOnGetPoiSearchResultListener(this);
		mBusLineSearch = BusLineSearch.newInstance();
		mBusLineSearch.setOnGetBusLineSearchResultListener(this);
		busLineIDList = new ArrayList<String>();
		overlay = new BusLineOverlay(mBaiduMap);
		mBaiduMap.setOnMarkerClickListener(overlay);
	}

	/**
	 * 鍙戣捣妫�绱�
	 * 
	 * @param v
	 */
	public void searchButtonProcess(View v) {
		busLineIDList.clear();
		busLineIndex = 0;
		mBtnPre.setVisibility(View.INVISIBLE);
		mBtnNext.setVisibility(View.INVISIBLE);
		EditText editCity = (EditText) findViewById(R.id.city);
		EditText editSearchKey = (EditText) findViewById(R.id.searchkey);
		// 鍙戣捣poi妫�绱紝浠庡緱鍒版墍鏈塸oi涓壘鍒板叕浜ょ嚎璺被鍨嬬殑poi锛屽啀浣跨敤璇oi鐨剈id杩涜鍏氦璇︽儏鎼滅储
		mSearch.searchInCity((new PoiCitySearchOption()).city(editCity.getText().toString())
				.keyword(editSearchKey.getText().toString()));
	}

	public void SearchNextBusline(View v) {
		if (busLineIndex >= busLineIDList.size()) {
			busLineIndex = 0;
		}
		if (busLineIndex >= 0 && busLineIndex < busLineIDList.size() && busLineIDList.size() > 0) {
			mBusLineSearch.searchBusLine(
					(new BusLineSearchOption().city(((EditText) findViewById(R.id.city)).getText().toString())
							.uid(busLineIDList.get(busLineIndex))));

			busLineIndex++;
		}

	}

	/**
	 * 鑺傜偣娴忚绀轰緥
	 * 
	 * @param v
	 */
	public void nodeClick(View v) {

		if (nodeIndex < -1 || route == null || nodeIndex >= route.getStations().size())
			return;
		TextView popupText = new TextView(this);
		popupText.setBackgroundResource(R.drawable.popup);
		popupText.setTextColor(0xff000000);
		// 涓婁竴涓妭鐐�
		if (mBtnPre.equals(v) && nodeIndex > 0) {
			// 绱㈠紩鍑�
			nodeIndex--;
		}
		// 涓嬩竴涓妭鐐�
		if (mBtnNext.equals(v) && nodeIndex < (route.getStations().size() - 1)) {
			// 绱㈠紩鍔�
			nodeIndex++;
		}
		if (nodeIndex >= 0) {
			// 绉诲姩鍒版寚瀹氱储寮曠殑鍧愭爣
			mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(route.getStations().get(nodeIndex).getLocation()));
			// 寮瑰嚭娉℃场
			popupText.setText(route.getStations().get(nodeIndex).getTitle());
			mBaiduMap.showInfoWindow(new InfoWindow(popupText, route.getStations().get(nodeIndex).getLocation(), 0));
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		mSearch.destroy();
		mBusLineSearch.destroy();
		super.onDestroy();
	}

	@Override
	public void onGetBusLineResult(BusLineResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(BusLineSearchDemo.this, "鎶辨瓑锛屾湭鎵惧埌缁撴灉", Toast.LENGTH_LONG).show();
			return;
		}
		mBaiduMap.clear();
		route = result;
		nodeIndex = -1;
		overlay.removeFromMap();
		overlay.setData(result);
		overlay.addToMap();
		overlay.zoomToSpan();
		mBtnPre.setVisibility(View.VISIBLE);
		mBtnNext.setVisibility(View.VISIBLE);
		Toast.makeText(BusLineSearchDemo.this, result.getBusLineName(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onGetPoiResult(PoiResult result) {

		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(BusLineSearchDemo.this, "鎶辨瓑锛屾湭鎵惧埌缁撴灉", Toast.LENGTH_LONG).show();
			return;
		}
		// 閬嶅巻鎵�鏈塸oi锛屾壘鍒扮被鍨嬩负鍏氦绾胯矾鐨刾oi
		busLineIDList.clear();
		for (PoiInfo poi : result.getAllPoi()) {
			if (poi.type == PoiInfo.POITYPE.BUS_LINE || poi.type == PoiInfo.POITYPE.SUBWAY_LINE) {
				busLineIDList.add(poi.uid);
			}
		}
		SearchNextBusline(null);
		route = null;
	}

	@Override
	public void onGetPoiDetailResult(PoiDetailResult result) {

	}

	@Override
	public void onMapClick(LatLng point) {
		mBaiduMap.hideInfoWindow();
	}

	@Override
	public boolean onMapPoiClick(MapPoi poi) {
		return false;
	}
}
