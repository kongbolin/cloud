package baidumapsdk.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMapDoubleClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMapLongClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMapStatusChangeListener;
import com.baidu.mapapi.map.BaiduMap.OnMapTouchListener;
import com.baidu.mapapi.map.BaiduMap.SnapshotReadyCallback;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.example.cloud.R;

/**
 * 婕旂ず鍦板浘缂╂斁锛屾棆杞紝瑙嗚鎺у埗
 */
public class MapControlDemo extends Activity {

	/**
	 * MapView 鏄湴鍥句富鎺т欢
	 */
	private MapView mMapView;
	private BaiduMap mBaiduMap;

	/**
	 * 褰撳墠鍦扮偣鍑荤偣
	 */
	private LatLng currentPt;
	/**
	 * 鎺у埗鎸夐挳
	 */
	private Button zoomButton;
	private Button rotateButton;
	private Button overlookButton;
	private Button saveScreenButton;

	private String touchType;

	/**
	 * 鐢ㄤ簬鏄剧ず鍦板浘鐘舵�佺殑闈㈡澘
	 */
	private TextView mStateBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mapcontrol);
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		mStateBar = (TextView) findViewById(R.id.state);
		initListener();
	}

	private void initListener() {
		mBaiduMap.setOnMapTouchListener(new OnMapTouchListener() {
			
			@Override
			public void onTouch(MotionEvent event) {
				
			}
		});
		
		mBaiduMap.setOnMapClickListener(new OnMapClickListener() {
			public void onMapClick(LatLng point) {
				touchType = "鍗曞嚮";
				currentPt = point;
				updateMapState();
			}

			public boolean onMapPoiClick(MapPoi poi) {
				return false;
			}
		});
		mBaiduMap.setOnMapLongClickListener(new OnMapLongClickListener() {
			public void onMapLongClick(LatLng point) {
				touchType = "闀挎寜";
				currentPt = point;
				updateMapState();
			}
		});
		mBaiduMap.setOnMapDoubleClickListener(new OnMapDoubleClickListener() {
			public void onMapDoubleClick(LatLng point) {
				touchType = "鍙屽嚮";
				currentPt = point;
				updateMapState();
			}
		});
		mBaiduMap.setOnMapStatusChangeListener(new OnMapStatusChangeListener() {
			public void onMapStatusChangeStart(MapStatus status) {
				updateMapState();
			}

			public void onMapStatusChangeFinish(MapStatus status) {
				updateMapState();
			}

			public void onMapStatusChange(MapStatus status) {
				updateMapState();
			}
		});
		zoomButton = (Button) findViewById(R.id.zoombutton);
		rotateButton = (Button) findViewById(R.id.rotatebutton);
		overlookButton = (Button) findViewById(R.id.overlookbutton);
		saveScreenButton = (Button) findViewById(R.id.savescreen);
		OnClickListener onClickListener = new OnClickListener() {
			@Override
			public void onClick(View view) {
				if (view.equals(zoomButton)) {
					perfomZoom();
				} else if (view.equals(rotateButton)) {
					perfomRotate();
				} else if (view.equals(overlookButton)) {
					perfomOverlook();
				} else if (view.equals(saveScreenButton)) {
					// 鎴浘锛屽湪SnapshotReadyCallback涓繚瀛樺浘鐗囧埌 sd 鍗�
					mBaiduMap.snapshot(new SnapshotReadyCallback() {
						public void onSnapshotReady(Bitmap snapshot) {
							File file = new File("/mnt/sdcard/test.png");
							FileOutputStream out;
							try {
								out = new FileOutputStream(file);
								if (snapshot.compress(
										Bitmap.CompressFormat.PNG, 100, out)) {
									out.flush();
									out.close();
								}
								Toast.makeText(MapControlDemo.this,
										"灞忓箷鎴浘鎴愬姛锛屽浘鐗囧瓨鍦�: " + file.toString(),
										Toast.LENGTH_SHORT).show();
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					});
					Toast.makeText(MapControlDemo.this, "姝ｅ湪鎴彇灞忓箷鍥剧墖...",
							Toast.LENGTH_SHORT).show();

				}
				updateMapState();
			}

		};
		zoomButton.setOnClickListener(onClickListener);
		rotateButton.setOnClickListener(onClickListener);
		overlookButton.setOnClickListener(onClickListener);
		saveScreenButton.setOnClickListener(onClickListener);
	}

	/**
	 * 澶勭悊缂╂斁 sdk 缂╂斁绾у埆鑼冨洿锛� [3.0,19.0]
	 */
	private void perfomZoom() {
		EditText t = (EditText) findViewById(R.id.zoomlevel);
		try {
			float zoomLevel = Float.parseFloat(t.getText().toString());
			MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(zoomLevel);
			mBaiduMap.animateMapStatus(u);
		} catch (NumberFormatException e) {
			Toast.makeText(this, "璇疯緭鍏ユ纭殑缂╂斁绾у埆", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 澶勭悊鏃嬭浆 鏃嬭浆瑙掕寖鍥达細 -180 ~ 180 , 鍗曚綅锛氬害 閫嗘椂閽堟棆杞�
	 */
	private void perfomRotate() {
		EditText t = (EditText) findViewById(R.id.rotateangle);
		try {
			int rotateAngle = Integer.parseInt(t.getText().toString());
			MapStatus ms = new MapStatus.Builder(mBaiduMap.getMapStatus()).rotate(rotateAngle).build();
			MapStatusUpdate u = MapStatusUpdateFactory.newMapStatus(ms);
			mBaiduMap.animateMapStatus(u);
		} catch (NumberFormatException e) {
			Toast.makeText(this, "璇疯緭鍏ユ纭殑鏃嬭浆瑙掑害", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 澶勭悊淇 淇鑼冨洿锛� -45 ~ 0 , 鍗曚綅锛� 搴�
	 */
	private void perfomOverlook() {
		EditText t = (EditText) findViewById(R.id.overlookangle);
		try {
			int overlookAngle = Integer.parseInt(t.getText().toString());
			MapStatus ms = new MapStatus.Builder(mBaiduMap.getMapStatus()).overlook(overlookAngle).build();
			MapStatusUpdate u = MapStatusUpdateFactory.newMapStatus(ms);
			mBaiduMap.animateMapStatus(u);
		} catch (NumberFormatException e) {
			Toast.makeText(this, "璇疯緭鍏ユ纭殑淇", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 鏇存柊鍦板浘鐘舵�佹樉绀洪潰鏉�
	 */
	private void updateMapState() {
		if (mStateBar == null) {
			return;
		}
		String state = "";
		if (currentPt == null) {
			state = "鐐瑰嚮銆侀暱鎸夈�佸弻鍑诲湴鍥句互鑾峰彇缁忕含搴﹀拰鍦板浘鐘舵��";
		} else {
			state = String.format(touchType + ",褰撳墠缁忓害锛� %f 褰撳墠绾害锛�%f",
					currentPt.longitude, currentPt.latitude);
		}
		state += "\n";
		MapStatus ms = mBaiduMap.getMapStatus();
		state += String.format(
				"zoom=%.1f rotate=%d overlook=%d",
				ms.zoom, (int) ms.rotate, (int) ms.overlook);
		mStateBar.setText(state);
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
