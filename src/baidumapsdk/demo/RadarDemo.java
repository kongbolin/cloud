package baidumapsdk.demo;


import java.util.ArrayList;
import java.util.List;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.radar.RadarNearbyInfo;
import com.baidu.mapapi.radar.RadarNearbyResult;
import com.baidu.mapapi.radar.RadarNearbySearchOption;
import com.baidu.mapapi.radar.RadarSearchError;
import com.baidu.mapapi.radar.RadarSearchListener;
import com.baidu.mapapi.radar.RadarSearchManager;
import com.baidu.mapapi.radar.RadarUploadInfo;
import com.baidu.mapapi.radar.RadarUploadInfoCallback;
import com.example.cloud.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;

/**
 * 婕旂ず鍛ㄨ竟闆疯揪鐨勪笟鍔″満鏅娇鐢�
 *
 */
public class RadarDemo extends Activity implements RadarUploadInfoCallback,RadarSearchListener,BDLocationListener,OnMarkerClickListener,OnMapClickListener{

	//鐣岄潰绌洪棿鐩稿叧
	private CustomViewPager mPager;//鑷畾涔塿iewPager锛岀洰鐨勬槸绂佺敤鎵嬪娍婊戝姩
    private List<View> listViews;
	private TabHost mTabHost;
	private EditText userId;
	private EditText userDes;
	private Button uploadOnece;
	private Button uploadContinue;
	private Button stopUpload;
	private Button switchBtn;
	private Button searchNearbyBtn;
	private Button clearRstBtn;
	private Button clearInfoBtn;
	private int index = 0;
	private Button listPreBtn;
	private Button listNextBtn;
	private TextView listCurPage;
	private Button mapPreBtn;
	private Button mapNextBtn;
	private TextView mapCurPage;
	
	
	
	// 瀹氫綅鐩稿叧
	LocationClient mLocClient;
	private int pageIndex = 0;
	private int curPage = 0;
	private int totalPage = 0;
	private LatLng pt = null;

	//鍛ㄨ竟闆疯揪鐩稿叧
	RadarNearbyResult listResult = null;
	ListView mResultListView = null;
	RadarResultListAdapter mResultListAdapter = null;
	private String userID = "";
	private String userComment = "";
	private boolean uploadAuto = false;
	
	//鍦板浘鐩稿叧
	private MapView mMapView;
	private BaiduMap mBaiduMap;
	private TextView popupText = null;//娉℃场view
	BitmapDescriptor ff3 = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radartablayout);
		//鍒濆鍖朥I鍜屽湴鍥�
		initUI();
		//鍛ㄨ竟闆疯揪璁剧疆鐩戝惉
		RadarSearchManager.getInstance().addNearbyInfoListener(this);
		//鍛ㄨ竟闆疯揪璁剧疆鐢ㄦ埛锛宨d涓虹┖榛樿鏄澶囨爣璇�
		RadarSearchManager.getInstance().setUserID(userID);
		// 瀹氫綅鍒濆鍖�
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(this);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(false);// 鎵撳紑gps
		option.setCoorType("bd09ll"); // 璁剧疆鍧愭爣绫诲瀷
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();
	}
    
	private void initUI() {
		mTabHost = (TabHost) findViewById(R.id.tabhost);
		mTabHost.setup();
		mTabHost.addTab(mTabHost.newTabSpec("tabUpload")
				.setIndicator(composeLayout("涓婁紶浣嶇疆", 0))
				.setContent(R.id.tabUpload));
		mTabHost.addTab(mTabHost.newTabSpec("tabGet")
				.setIndicator(composeLayout("妫�绱㈠懆杈�", 0))
				.setContent(R.id.tabGet));
		mTabHost.setCurrentTab(0);
		
		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				for(int i=0;i<mTabHost.getTabWidget().getChildCount();i++){
					mTabHost.getTabWidget().getChildAt(i).setBackgroundDrawable(null);
				}
				mTabHost.getTabWidget().getChildAt(mTabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#B0E2FF"));
			}

		});
		
		mPager = (CustomViewPager) findViewById(R.id.viewpager);
        listViews = new ArrayList<View>();
        LayoutInflater mInflater = getLayoutInflater();
        
        View layout = mInflater.inflate(R.layout.activity_radarlist, null);
        View mapLayout = mInflater.inflate(R.layout.activity_radarmap, null);
        //鍦板浘鍒濆鍖�
        mMapView = (MapView) mapLayout.findViewById(R.id.map);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setOnMarkerClickListener(this);
        mBaiduMap.setOnMapClickListener(this);
        mBaiduMap.setMyLocationEnabled(true);
        listViews.add(layout);
        listViews.add(mapLayout);
        mPager.setAdapter(new MyPagerAdapter(listViews));
        mPager.setCurrentItem(0);
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
        
		userId = (EditText)findViewById(R.id.id);
		userDes= (EditText)findViewById(R.id.des);
		uploadOnece = (Button)findViewById(R.id.uploadonece);
		uploadContinue = (Button)findViewById(R.id.uploadcontinue);
		stopUpload = (Button)findViewById(R.id.stopupload);
		switchBtn = (Button)findViewById(R.id.switchButton);
		searchNearbyBtn = (Button)findViewById(R.id.searchNearByButton);
		clearRstBtn = (Button)findViewById(R.id.clearResultButton);
		clearInfoBtn = (Button)findViewById(R.id.clearInfoButton);
		listPreBtn = (Button)layout.findViewById(R.id.radarlistpre);
		listNextBtn = (Button)layout.findViewById(R.id.radarlistnext);
		listCurPage = (TextView)layout.findViewById(R.id.radarListPage);
		mapPreBtn = (Button)mapLayout.findViewById(R.id.radarmappre);
		mapNextBtn = (Button)mapLayout.findViewById(R.id.radarmapnext);
		mapCurPage = (TextView)mapLayout.findViewById(R.id.radarMapPage);
		uploadContinue.setEnabled(true);
		stopUpload.setEnabled(false);
		clearRstBtn.setEnabled(false);
		clearInfoBtn.setEnabled(false);
		listPreBtn.setVisibility(View.INVISIBLE);
		listNextBtn.setVisibility(View.INVISIBLE);
		mapPreBtn.setVisibility(View.INVISIBLE);
		mapNextBtn.setVisibility(View.INVISIBLE);
		listCurPage.setVisibility(View.INVISIBLE);
		mapCurPage.setVisibility(View.INVISIBLE);
		
		mTabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.DKGRAY);
		ArrayList<RadarNearbyInfo> infos = new ArrayList<RadarNearbyInfo>();
		mResultListAdapter = new RadarResultListAdapter(null);
		mResultListView = (ListView) layout.findViewById(R.id.radar_list);
		mResultListView.setAdapter(mResultListAdapter);
		mResultListAdapter.notifyDataSetChanged();
		TextWatcher textWatcher = new TextWatcher() {

	        @Override
	        public void onTextChanged(CharSequence s, int start, int before, int count) {
	            // TODO Auto-generated method stub

	        	userID = userId.getText().toString();
	        	userComment = userDes.getText().toString();
	        	RadarSearchManager.getInstance().setUserID(userID);
	        }

	        @Override
	        public void beforeTextChanged(CharSequence s, int start, int count,
	                int after) {
	            // TODO Auto-generated method stub

	        }

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
	    };
	    //鐢ㄦ埛ID鍜岀敤鎴锋弿杩扮洃鍚枃鏈鍙樺寲
	    userId.addTextChangedListener(textWatcher);
	    userDes.addTextChangedListener(textWatcher);
	}
	/**
	 * 涓婁紶涓�娆′綅缃�
	 * @param v
	 */
	public void uploadOnceClick(View v) {
		if (pt == null) {
			Toast.makeText(RadarDemo.this, "鏈幏鍙栧埌浣嶇疆", Toast.LENGTH_LONG)
			.show();
			return;
		}
		RadarUploadInfo info = new RadarUploadInfo();
		info.comments = userComment;
		info.pt = pt;
		RadarSearchManager.getInstance().uploadInfoRequest(info);
		clearInfoBtn.setEnabled(true);
	}
	/**
	 * 寮�濮嬭嚜鍔ㄤ笂浼�
	 * @param v
	 */
	public void uploadContinueClick(View v) {
		if (pt == null) {
			Toast.makeText(RadarDemo.this, "鏈幏鍙栧埌浣嶇疆", Toast.LENGTH_LONG)
			.show();
			return;
		}
		uploadAuto = true;
		RadarSearchManager.getInstance().startUploadAuto(this, 5000);
		uploadContinue.setEnabled(false);
		stopUpload.setEnabled(true);
		clearInfoBtn.setEnabled(true);
	}
	/**
	 * 鍋滄鑷姩涓婁紶
	 * @param v
	 */
	public void stopUploadClick(View v) {
		uploadAuto = false;
		RadarSearchManager.getInstance().stopUploadAuto();
		stopUpload.setEnabled(false);
		uploadContinue.setEnabled(true);
	}
	/**
	 * 娓呴櫎鑷繁褰撳墠鐨勪俊鎭�
	 * @param v
	 */
	public void clearInfoClick(View v) {
		RadarSearchManager.getInstance().clearUserInfo();
	}
	/**
	 * 鏌ユ壘鍛ㄨ竟鐨勪汉
	 * @param v
	 */
	public void searchNearby(View v) {
		if (pt == null) {
			Toast.makeText(RadarDemo.this, "鏈幏鍙栧埌浣嶇疆", Toast.LENGTH_LONG)
			.show();
			return;
		}
		pageIndex = 0;
		searchRequest(pageIndex);
	}
	/**
	 * 涓婁竴椤�
	 * @param v
	 */
	public void preClick(View v) {
		if (pageIndex < 1) {
			return;
		}
		//涓婁竴椤�
		pageIndex--;
		searchRequest(pageIndex);
	}
	/**
	 * 涓嬩竴椤�
	 * @param v
	 */
	public void nextClick(View v) {
		if (pageIndex >= totalPage - 1) {
			return;
		}
		//涓嬩竴椤�
		pageIndex++;
		searchRequest(pageIndex);
	}
	private void searchRequest(int index) {
		curPage = 0;
		totalPage = 0;

		RadarNearbySearchOption option = new RadarNearbySearchOption().centerPt(pt).pageNum(pageIndex).radius(2000);
		RadarSearchManager.getInstance().nearbyInfoRequest(option);
	
		listPreBtn.setVisibility(View.INVISIBLE);
		listNextBtn.setVisibility(View.INVISIBLE);
		mapPreBtn.setVisibility(View.INVISIBLE);
		mapNextBtn.setVisibility(View.INVISIBLE);
		listCurPage.setText("0/0");
		mapCurPage.setText("0/0");
		mBaiduMap.hideInfoWindow();
	}
	/**
	 * 娓呴櫎鏌ユ壘缁撴灉
	 * @param v
	 */
	public void clearResult(View v) {
		parseResultToList(null);
		parseResultToMap(null);
		clearRstBtn.setEnabled(false);
		listPreBtn.setVisibility(View.INVISIBLE);
		listNextBtn.setVisibility(View.INVISIBLE);
		mapPreBtn.setVisibility(View.INVISIBLE);
		mapNextBtn.setVisibility(View.INVISIBLE);
		listCurPage.setVisibility(View.INVISIBLE);
		mapCurPage.setVisibility(View.INVISIBLE);
		mBaiduMap.hideInfoWindow();
	}
	//viewPager鍒囨崲
	public void switchClick(View v) {
		if (index == 0) {
			//鍒囨崲涓哄湴鍥�
			index = 1;
			switchBtn.setText("鍒楄〃");
		} else {
			//鍒囨崲涓哄垪琛�
			index = 0;
			switchBtn.setText("鍦板浘");
		}
		mPager.setCurrentItem(index);
		
	}
	/**
     * 鏇存柊缁撴灉鍒楄〃
     * @param res
     */
    public void parseResultToList(RadarNearbyResult res) {
    	if (res == null) {
    		if (mResultListAdapter.list != null) {
    			mResultListAdapter.list.clear();
            	mResultListAdapter.notifyDataSetChanged();
    		}
    		
    	} else {
    		mResultListAdapter.list = res.infoList;
        	mResultListAdapter.notifyDataSetChanged();
        	if (curPage > 0) {
        		listPreBtn.setVisibility(View.VISIBLE);
        	}
        	if (totalPage - 1 > curPage) {
        		listNextBtn.setVisibility(View.VISIBLE);
        	}
        	if (totalPage > 0) {
        		listCurPage.setVisibility(View.VISIBLE);
            	listCurPage.setText(String.valueOf(curPage + 1)+"/"+String.valueOf(totalPage));
        	}
        	
    	}
    	
    }
    /**
     * 鏇存柊缁撴灉鍦板浘
     * @param res
     */
    public void parseResultToMap(RadarNearbyResult res) {
    	mBaiduMap.clear();
    	if (res != null && res.infoList != null && res.infoList.size() > 0) {
    		for (int i = 0;i< res.infoList.size();i++) {
        		MarkerOptions option = new MarkerOptions().icon(ff3).position(res.infoList.get(i).pt);
        		Bundle des = new Bundle();
        		if (res.infoList.get(i).comments == null || res.infoList.get(i).comments.equals("")) {
        			des.putString("des", "娌℃湁澶囨敞");
        		} else {
        			des.putString("des", res.infoList.get(i).comments);
        		}
        		
        		option.extraInfo(des);
        		mBaiduMap.addOverlay(option);
        	}
    	}
    	if (curPage > 0) {
    		mapPreBtn.setVisibility(View.VISIBLE);
    	}
    	if (totalPage - 1 > curPage) {
    		mapNextBtn.setVisibility(View.VISIBLE);
    	}
    	if (totalPage > 0) {
    		mapCurPage.setVisibility(View.VISIBLE);
    		mapCurPage.setText(String.valueOf(curPage + 1)+"/"+String.valueOf(totalPage));
    	}
    	
    }
    /**
     * 瀹炵幇涓婁紶callback锛岃嚜鍔ㄤ笂浼�
     */
	@Override
	public RadarUploadInfo OnUploadInfoCallback() {
		// TODO Auto-generated method stub
		RadarUploadInfo info = new RadarUploadInfo();
		info.comments = userComment;
		info.pt = pt;
		Log.e("hjtest", "OnUploadInfoCallback");
		return info;
	}
	@Override
	public void onGetNearbyInfoList(RadarNearbyResult result,
			RadarSearchError error) {
		// TODO Auto-generated method stub
		if (error == RadarSearchError.RADAR_NO_ERROR) {
			Toast.makeText(RadarDemo.this, "鏌ヨ鍛ㄨ竟鎴愬姛", Toast.LENGTH_LONG)
			.show();
			//鑾峰彇鎴愬姛
			listResult = result;
			curPage = result.pageIndex;
			totalPage = result.pageNum;
			//澶勭悊鏁版嵁
			parseResultToList(listResult);
			parseResultToMap(listResult);
			clearRstBtn.setEnabled(true);
		} else {
			//鑾峰彇澶辫触
			curPage = 0;
			totalPage = 0;
			Toast.makeText(RadarDemo.this, "鏌ヨ鍛ㄨ竟澶辫触", Toast.LENGTH_LONG)
			.show();
		}
		
	}
	@Override
	public void onGetUploadState(RadarSearchError error) {
		// TODO Auto-generated method stub
		if (error == RadarSearchError.RADAR_NO_ERROR) {
			//涓婁紶鎴愬姛
			if (!uploadAuto) {
				Toast.makeText(RadarDemo.this, "鍗曟涓婁紶浣嶇疆鎴愬姛", Toast.LENGTH_LONG)
				.show();
			}
		} else {
			//涓婁紶澶辫触
			if (!uploadAuto) {
				Toast.makeText(RadarDemo.this, "鍗曟涓婁紶浣嶇疆澶辫触", Toast.LENGTH_LONG)
				.show();
			}
		}
	}
	@Override
	public void onGetClearInfoState(RadarSearchError error) {
		// TODO Auto-generated method stub
		if (error == RadarSearchError.RADAR_NO_ERROR) {
			//娓呴櫎鎴愬姛
			Toast.makeText(RadarDemo.this, "娓呴櫎浣嶇疆鎴愬姛", Toast.LENGTH_LONG)
			.show();
		} else {
			//娓呴櫎澶辫触
			Toast.makeText(RadarDemo.this, "娓呴櫎浣嶇疆澶辫触", Toast.LENGTH_LONG)
			.show();
		}
	}
	@Override
	public void onMapClick(LatLng point) {
		// TODO Auto-generated method stub
		mBaiduMap.hideInfoWindow();
	}

	@Override
	public boolean onMapPoiClick(MapPoi poi) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		// TODO Auto-generated method stub
		mBaiduMap.hideInfoWindow();
		if (marker != null) {
			popupText = new TextView(RadarDemo.this);
	        popupText.setBackgroundResource(R.drawable.popup);
	        popupText.setTextColor(0xFF000000);
	        popupText.setText(marker.getExtraInfo().getString("des"));
	        mBaiduMap.showInfoWindow(new InfoWindow(popupText, marker.getPosition(), -47));
	        MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(marker.getPosition());
	        mBaiduMap.setMapStatus(update);
	        return true;
		} else {
			return false;
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
		mTabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#B0E2FF"));
	}

	@Override
	protected void onDestroy() {
		// 閫�鍑烘椂閿�姣佸畾浣�
		mLocClient.stop();
		//閲婃斁鍛ㄨ竟闆疯揪鐩稿叧
		RadarSearchManager.getInstance().removeNearbyInfoListener(this);
		RadarSearchManager.getInstance().clearUserInfo();
		RadarSearchManager.getInstance().destroy();
		//閲婃斁鍦板浘
		ff3.recycle();
		mMapView.onDestroy();
		mBaiduMap = null;
		super.onDestroy();
	}
	//瀹氫綅SDK鍥炶皟
		@Override
		public void onReceiveLocation(BDLocation arg0) {
			// TODO Auto-generated method stub
			if (arg0 == null || mBaiduMap == null)
				return;
			pt = new LatLng(arg0.getLatitude(), arg0.getLongitude());
			MyLocationData locData = new MyLocationData.Builder()
			.accuracy(arg0.getRadius())
			// 姝ゅ璁剧疆寮�鍙戣�呰幏鍙栧埌鐨勬柟鍚戜俊鎭紝椤烘椂閽�0-360
			.direction(100).latitude(arg0.getLatitude())
			.longitude(arg0.getLongitude()).build();
			if (mBaiduMap != null) {
				mBaiduMap.setMyLocationData(locData);
			}
			
		}
    public class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
        	if (arg0 == 0) {
    			//鍒囨崲涓哄垪琛�
    			index = 0;
    			switchBtn.setText("鍦板浘");
    		} else {
    			//鍒囨崲涓哄湴鍥�
    			index = 1;
    			switchBtn.setText("鍒楄〃");
    		}
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }
	/**
     * ViewPager閫傞厤鍣�
     */
    public class MyPagerAdapter extends PagerAdapter {
        public List<View> mListViews;

        public MyPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(mListViews.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(mListViews.get(arg1), 0);
            return mListViews.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }
    }
    
	
	/**
	 * 缁撴灉鍒楄〃listview閫傞厤鍣�
	 *
	 */
	private class RadarResultListAdapter extends BaseAdapter {
		public List<RadarNearbyInfo> list;
		public RadarResultListAdapter(List<RadarNearbyInfo> res) {
			super();
			this.list = res;
		}

		@Override
		public View getView(int index, View convertView, ViewGroup parent) {
			convertView = View.inflate(RadarDemo.this,
					R.layout.demo_info_item, null);
			TextView title = (TextView) convertView.findViewById(R.id.title);
			TextView desc = (TextView) convertView.findViewById(R.id.desc);
			title.setTextColor(Color.parseColor("#0000FF"));
			desc.setTextColor(Color.parseColor("#0000FF"));
			if (list == null || list.size() == 0 || index >= list.size()) {
				desc.setText("");
				title.setText("");
			} else {
				if (list.get(index).comments == null || list.get(index).comments.equals("")) {
					desc.setText(String.valueOf(list.get(index).distance) + "绫�"+ "_娌℃湁澶囨敞");
				} else {
					desc.setText(String.valueOf(list.get(index).distance) + "绫�"+ "_"+list.get(index).comments);
				}
					
				title.setText(list.get(index).userID);
			}
			

			return convertView;
		}

		@Override
		public int getCount() {
			if (list == null || (list!= null && list.size() < 10)) {
				return 10;
			} else {
				return list.size();
			}
			
		}

		@Override
		public Object getItem(int index) {
			if (list == null) {
				return new RadarNearbyInfo();
			} else {
				return list.get(index);
			}
			
		}

		@Override
		public long getItemId(int id) {
			return id;
		}
	}
	
	public View composeLayout(String s, int i) {
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		ImageView iv = new ImageView(this);
		iv.setImageResource(i);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(0, 5, 0, 0);
//		layout.addView(iv, lp);
		TextView tv = new TextView(this);
		tv.setGravity(Gravity.CENTER);
		tv.setSingleLine(true);
		tv.setText(s);
		tv.setTextColor(Color.parseColor("#0000FF"));
		tv.setTextSize(20);
		layout.addView(tv, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT));
		return layout;
	}

	@Override
	public void onReceivePoi(BDLocation arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
