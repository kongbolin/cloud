//package com.example.cloud;
//
//import android.app.AlertDialog;
//import android.app.Application;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Process;
//import android.os.Vibrator;
//import android.util.Log;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.app.AlertDialog;
//import com.baidu.location.BDLocation;
//import com.baidu.location.BDLocationListener;
//import com.baidu.location.BDNotifyListener;
//import com.baidu.location.GeofenceClient;
//import com.baidu.location.LocationClient;
//import com.example.cloud.view01;
//import com.example.cloud.MyApplication;
//import com.example.cloud.Location;
//
//public class Location extends Application {
//
//	public LocationClient mLocationClient = null;
//	public GeofenceClient mGeofenceClient;
//	private String mData;
//	public MyLocationListenner myListener = new MyLocationListenner();
//	public TextView mTv;
//	public NotifyLister mNotifyer = null;
//	public Vibrator mVibrator01;
//	public static String str1 = "000";
//	public static String TAG = "LocTestDemo";
//
//	@Override
//	public void onCreate() {
//
//		mLocationClient = new LocationClient(this);
//		/**
//		 * 鈥斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�
//		 * 斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺��
//		 * 杩欓噷鐨凙K鍜屽簲鐢ㄧ鍚嶅寘鍚嶇粦瀹氾紝濡傛灉浣跨敤鍦ㄨ嚜宸辩殑宸ョ▼涓渶瑕佹浛鎹负鑷繁鐢宠鐨凨ey
//		 * 鈥斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�
//		 * 斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺�斺��
//		 */
//		mLocationClient.setAK("0Fa49070e3d9a18fb1df084293c5a335");
//		mLocationClient.registerLocationListener(myListener);
//		mGeofenceClient = new GeofenceClient(this);
//		// 浣嶇疆鎻愰啋鐩稿叧浠ｇ爜
//		// mNotifyer = new NotifyLister();
//		// mNotifyer.SetNotifyLocation(40.047883,116.312564,3000,"gps");//4涓弬鏁颁唬琛ㄨ浣嶇疆鎻愰啋鐨勭偣鐨勫潗鏍囷紝鍏蜂綋鍚箟渚濇涓猴細绾害锛岀粡搴︼紝璺濈鑼冨洿锛屽潗鏍囩郴绫诲瀷(gcj02,gps,bd09,bd09ll)
//		// mLocationClient.registerNotify(mNotifyer);
//
//		super.onCreate();
//		Log.d(TAG, "... Application onCreate... pid=" + Process.myPid());
//		// Intent intent=new Intent(Location.this,MainActivity.class);
//		// MyApplication app=MyApplication.getInstance();
//		// app.getMap().put("data1","鐜嬩簩");
//		// app.getMap().put("data2","寮犱笁");
//		// app.getMap().put("data3","楹诲瓙");
//		// app.getMap().put("data4","鏉庡洓");
//		// startActivity(intent);
//
//	}
//
//	/**
//	 * 鏄剧ず璇锋眰瀛楃涓�
//	 * 
//	 * @param str
//	 */
//	public void logMsg(String str) {
//		try {
//			mData = str;
//			if (mTv != null)
//				mTv.setText(mData);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 鐩戝惉鍑芥暟锛屾湁鏇存柊浣嶇疆鐨勬椂鍊欙紝鏍煎紡鍖栨垚瀛楃涓诧紝杈撳嚭鍒板睆骞曚腑
//	 */
//	public class MyLocationListenner implements BDLocationListener {
//		@Override
//		public void onReceiveLocation(BDLocation location) {
//			if (location == null)
//				return;
//			StringBuffer sb = new StringBuffer(256);
//			sb.append("time : ");
//			sb.append(location.getTime());
//			sb.append("\nerror code : ");
//			sb.append(location.getLocType());
//			sb.append("\nlatitude : ");
//			sb.append(location.getLatitude());
//			sb.append("\nlontitude : ");
//			sb.append(location.getLongitude());
//			sb.append("\nradius : ");
//			sb.append(location.getRadius());
//
//			// if (location.getLocType() == BDLocation.TypeGpsLocation){
//			// sb.append("\nspeed : ");
//			// sb.append(location.getSpeed());
//			// sb.append("\nsatellite : ");
//			// sb.append(location.getSatelliteNumber());
//			// } else if (location.getLocType() ==
//			// BDLocation.TypeNetWorkLocation){
//			/**
//			 * 鏍煎紡鍖栨樉绀哄湴鍧�淇℃伅
//			 */
//			// sb.append("\n鐪侊細");
//			// sb.append(location.getProvince());
//			// sb.append("\n甯傦細");
//			// sb.append(location.getCity());
//			// sb.append("\n鍖�/鍘匡細");
//			// sb.append(location.getDistrict());
//			sb.append("\naddr : ");
//			sb.append(location.getCity());
//			str1 = location.getCity();
//
//			// }
//			sb.append("\nsdk version : ");
//			sb.append(mLocationClient.getVersion());
//			sb.append("\nisCellChangeFlag : ");
//			sb.append(location.isCellChangeFlag());
//			logMsg(sb.toString());
//			Log.i(TAG, sb.toString());
//			// AlertDialog alertDialog = new
//			// AlertDialog.Builder(Location.this).create();
//			// // alertDialog.setIcon(R.drawable.desert);
//			// // alertDialog.setTitle("System Information:");
//			// AlertDialog.Builder settingDialog = new
//			// AlertDialog.Builder(this);
//			// settingDialog.setInverseBackgroundForced(true);
//			// alertDialog.setMessage(str1);
//			// // 娣诲姞鍙栨秷鎸夐挳
//			// alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "鍙栨秷", new
//			// DialogInterface.OnClickListener() {
//			// @Override
//			// public void onClick(DialogInterface dialog, int which) {
//			// Toast.makeText(Location.this, "You clicked the Cancel Button",
//			// Toast.LENGTH_LONG).show();
//			// }
//			// });
//			// // 娣诲姞纭畾鎸夐挳
//			// alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "鎷ㄦ墦", new
//			// DialogInterface.OnClickListener() {
//			// @Override
//			// public void onClick(DialogInterface dialog, int which) {
//			// // Toast.makeText(viewpaper.this,"You clicked the Confirm
//			// // Button",Toast.LENGTH_LONG).show();
//			// String phoneNumber = "18710861689";
//			// // 3.缁欒繖涓彿鐮佹墦鐢佃瘽
//			// Intent intent8 = new Intent();
//			// intent8.setAction("android.intent.action.CALL");
//			// intent8.addCategory("android.intent.category.DEFAULT");
//			// intent8.setData(Uri.parse("tel:" + phoneNumber));
//			// startActivity(intent8);
//			// }
//			// });
//			// // 娣诲姞涓珛鎸夐挳
//			// //
//			// alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,"neutral",new
//			// // DialogInterface.OnClickListener() {
//			// // @Override
//			// // public void onClick(DialogInterface dialog, int which) {
//			// // Toast.makeText(viewpaper.this,"You clicked the neutral
//			// // Button",Toast.LENGTH_LONG).show();
//			// // }
//			// // });
//			// alertDialog.show();
//		}
//
//		public void onReceivePoi(BDLocation poiLocation) {
//			if (poiLocation == null) {
//				return;
//			}
//			StringBuffer sb = new StringBuffer(256);
//			sb.append("Poi time : ");
//			sb.append(poiLocation.getTime());
//			sb.append("\nerror code : ");
//			sb.append(poiLocation.getLocType());
//			sb.append("\nlatitude : ");
//			sb.append(poiLocation.getLatitude());
//			sb.append("\nlontitude : ");
//			sb.append(poiLocation.getLongitude());
//			sb.append("\nradius : ");
//			sb.append(poiLocation.getRadius());
//			if (poiLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
//				sb.append("\naddr : ");
//				sb.append(poiLocation.getAddrStr());
//			}
//			if (poiLocation.hasPoi()) {
//				sb.append("\nPoi:");
//				sb.append(poiLocation.getPoi());
//			} else {
//				sb.append("noPoi information");
//			}
//			logMsg(sb.toString());
//		}
//	}
//
//	public class NotifyLister extends BDNotifyListener {
//		public void onNotify(BDLocation mlocation, float distance) {
//			mVibrator01.vibrate(1000);
//
//		}
//	}
//
//}