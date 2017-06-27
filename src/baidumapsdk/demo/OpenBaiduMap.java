package baidumapsdk.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.mapapi.VersionInfo;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapAppNotSupportNaviException;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviParaOption;
import com.baidu.mapapi.utils.OpenClientUtil;
import com.baidu.mapapi.utils.poi.BaiduMapPoiSearch;
import com.baidu.mapapi.utils.poi.PoiParaOption;
import com.baidu.mapapi.utils.route.BaiduMapRoutePlan;
import com.baidu.mapapi.utils.route.RouteParaOption;
import com.baidu.mapapi.utils.route.RouteParaOption.EBusStrategyType;
import com.example.cloud.R;

public class OpenBaiduMap extends Activity {

	// 澶╁畨闂ㄥ潗鏍�
	double mLat1 = 39.915291;
	double mLon1 = 116.403857;
	// 鐧惧害澶у帵鍧愭爣
	double mLat2 = 40.056858;
	double mLon2 = 116.308194;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_open_baidumap);
		TextView text = (TextView) findViewById(R.id.open_Info);
		text.setTextColor(Color.YELLOW);
		text.setText("褰撴墜鏈烘病鏈夊畨瑁呯櫨搴﹀湴鍥惧鎴风鎴栫増鏈繃浣庢椂锛岄粯璁よ皟璧风櫨搴﹀湴鍥緒ebApp");
		ListView mListView = (ListView) findViewById(R.id.listView_openBaiduMap);
		mListView.setAdapter(new OpenBaiduMapListAdapter(getData()));
       
        mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					startNavi();
					break;

				case 1:
					startWebNavi();
					break;
					
				case 2:
					startPoiNearbySearch();
					break;
					
				case 3:
					startPoiDetails();
					break;
					
				case 4:
					startRoutePlanWalking();
					break;
					
				case 5:
					startRoutePlanDriving();
					break;
					
				case 6:
					startRoutePlanTransit();
					break;
					
				default:
					break;
				}
			}
		});

	}
	
    private List<String> getData(){      
        List<String> data = new ArrayList<String>();
        data.add("鍚姩鐧惧害鍦板浘瀵艰埅(Native)");
        data.add("鍚姩鐧惧害鍦板浘瀵艰埅(Web)");
        data.add("鍚姩鐧惧害鍦板浘Poi鍛ㄨ竟妫�绱�");
        data.add("鍚姩鐧惧害鍦板浘Poi璇︽儏椤甸潰");
        data.add("鍚姩鐧惧害鍦板浘姝ヨ璺嚎瑙勫垝");
        data.add("鍚姩鐧惧害鍦板浘椹捐溅璺嚎瑙勫垝");
        data.add("鍚姩鐧惧害鍦板浘鍏氦璺嚎瑙勫垝");
        return data;
    }
	
	/**
	 * 鍚姩鐧惧害鍦板浘瀵艰埅(Native)
	 * 
	 */
	public void startNavi() {
		LatLng pt1 = new LatLng(mLat1, mLon1);
		LatLng pt2 = new LatLng(mLat2, mLon2);
		
		// 鏋勫缓 瀵艰埅鍙傛暟
		NaviParaOption para = new NaviParaOption()
					.startPoint(pt1).endPoint(pt2)
					.startName("澶╁畨闂�").endName("鐧惧害澶у帵");

		try {
			BaiduMapNavigation.openBaiduMapNavi(para, this);
		} catch (BaiduMapAppNotSupportNaviException e) {
			e.printStackTrace();
			showDialog();
		}
		
	}

	/**
	 * 鍚姩鐧惧害鍦板浘瀵艰埅(Web)
	 */
	public void startWebNavi() {
		LatLng pt1 = new LatLng(mLat1, mLon1);
		LatLng pt2 = new LatLng(mLat2, mLon2);
		// 鏋勫缓 瀵艰埅鍙傛暟
		NaviParaOption para = new NaviParaOption()
				.startPoint(pt1).endPoint(pt2);

		BaiduMapNavigation.openWebBaiduMapNavi(para, this);
	}
	
	/**
	 * 鍚姩鐧惧害鍦板浘Poi鍛ㄨ竟妫�绱�
	 * 
	 */
	public void startPoiNearbySearch() {
		LatLng pt_center = new LatLng(mLat1, mLon1); // 澶╁畨闂�
		PoiParaOption para = new PoiParaOption()
			.key("澶╁畨闂�")
			.center(pt_center)
			.radius(2000);
		
		try {
			BaiduMapPoiSearch.openBaiduMapPoiNearbySearch(para, this);
		} catch (Exception e) {
			e.printStackTrace();
			showDialog();
		}
	
	}
	
	/**
	 * 鍚姩鐧惧害鍦板浘Poi璇︽儏椤甸潰
	 * 
	 */
	public void startPoiDetails() {
		PoiParaOption para = new PoiParaOption().uid("65e1ee886c885190f60e77ff");// 澶╁畨闂�
		
		try {
			BaiduMapPoiSearch.openBaiduMapPoiDetialsPage(para, this);
		} catch (Exception e) {
			e.printStackTrace();
			showDialog();
		}

	}
	
	/**
	 * 鍚姩鐧惧害鍦板浘姝ヨ璺嚎瑙勫垝
	 * 
	 */
	public void startRoutePlanWalking() {
		LatLng pt_start = new LatLng(34.264642646862, 108.95108518068);
		LatLng pt_end = new LatLng(mLat2, mLon2);
		
		// 鏋勫缓 route鎼滅储鍙傛暟
		RouteParaOption para = new RouteParaOption()
			.startPoint(pt_start)
//			.startName("澶╁畨闂�")
//			.endPoint(pt_end);
			.endName("澶ч泚濉�")
			.cityName("瑗垮畨");
		
//		RouteParaOption para = new RouteParaOption()
//		.startName("澶╁畨闂�").endName("鐧惧害澶у帵");
		
//		RouteParaOption para = new RouteParaOption()
//		.startPoint(pt_start).endPoint(pt_end);
		
		try {
			BaiduMapRoutePlan.openBaiduMapWalkingRoute(para, this);
		} catch (Exception e) {
			e.printStackTrace();
			showDialog();
		}
			
	}
	
	/**
	 * 鍚姩鐧惧害鍦板浘椹捐溅璺嚎瑙勫垝
	 * 
	 */
	public void startRoutePlanDriving() {
		LatLng pt_start = new LatLng(34.264642646862, 108.95108518068);
		LatLng pt_end = new LatLng(mLat2, mLon2);
		
		// 鏋勫缓 route鎼滅储鍙傛暟
		RouteParaOption para = new RouteParaOption()
			.startPoint(pt_start)
//			.startName("澶╁畨闂�")
//			.endPoint(pt_end);
			.endName("澶ч泚濉�")
			.cityName("瑗垮畨");
		
//		RouteParaOption para = new RouteParaOption()
//				.startName("澶╁畨闂�").endName("鐧惧害澶у帵");
					
//		RouteParaOption para = new RouteParaOption()
//		.startPoint(pt_start).endPoint(pt_end);
		
		try {
			BaiduMapRoutePlan.openBaiduMapDrivingRoute(para, this);	
		} catch (Exception e) {
			e.printStackTrace();
			showDialog();
		}
		
	}

	/**
	 * 鍚姩鐧惧害鍦板浘鍏氦璺嚎瑙勫垝
	 * 
	 */
	public void startRoutePlanTransit() {
		LatLng pt_start = new LatLng(mLat1, mLon1);
		LatLng pt_end = new LatLng(mLat2, mLon2);
		
		// 鏋勫缓 route鎼滅储鍙傛暟
		RouteParaOption para = new RouteParaOption()
//			.startPoint(pt_start)
			.startName("澶╁畨闂�")
			.endPoint(pt_end)
//			.endName("鐧惧害澶у帵")
			.busStrategyType(EBusStrategyType.bus_recommend_way);

//		RouteParaOption para = new RouteParaOption()
//				.startName("澶╁畨闂�").endName("鐧惧害澶у帵").busStrategyType(EBusStrategyType.bus_recommend_way);
		
//		RouteParaOption para = new RouteParaOption()
//		.startPoint(pt_start).endPoint(pt_end).busStrategyType(EBusStrategyType.bus_recommend_way);
		
		try {
			BaiduMapRoutePlan.openBaiduMapTransitRoute(para, this);
		} catch (Exception e) {
			e.printStackTrace();
			showDialog();
		}
		
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		BaiduMapRoutePlan.finish(this);
		BaiduMapPoiSearch.finish(this);
	}
	/**
	 * 鎻愮ず鏈畨瑁呯櫨搴﹀湴鍥綼pp鎴朼pp鐗堟湰杩囦綆
	 * 
	 */
	public void showDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("鎮ㄥ皻鏈畨瑁呯櫨搴﹀湴鍥綼pp鎴朼pp鐗堟湰杩囦綆锛岀偣鍑荤‘璁ゅ畨瑁咃紵");
		builder.setTitle("鎻愮ず");
		builder.setPositiveButton("纭", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				OpenClientUtil.getLatestBaiduMapApp(OpenBaiduMap.this);
			}
		});

		builder.setNegativeButton("鍙栨秷", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		builder.create().show();
		
	}
	
	
	private class OpenBaiduMapListAdapter extends BaseAdapter {
		List<String> list;
		public OpenBaiduMapListAdapter(List<String> list) {
			super();
			this.list = list;
		}

		@Override
		public View getView(int index, View convertView, ViewGroup parent) {
			convertView = View.inflate(OpenBaiduMap.this,
					R.layout.demo_info_item, null);
			TextView title = (TextView) convertView.findViewById(R.id.title);
			TextView desc = (TextView) convertView.findViewById(R.id.desc);
			desc.setVisibility(View.INVISIBLE);
			title.setText(list.get(index));

			return convertView;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int index) {
			return list.get(index);
		}

		@Override
		public long getItemId(int id) {
			return id;
		}
	}
}
