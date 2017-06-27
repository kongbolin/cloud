//package com.example.cloud;
//
//import android.app.Application;
//import android.os.Process;
//import android.os.Vibrator;
//import android.util.Log;
//import android.widget.TextView;
//
//import com.baidu.location.BDLocation;
//import com.baidu.location.BDLocationListener;
//import com.baidu.location.BDNotifyListener;
//import com.baidu.location.GeofenceClient;
//import com.baidu.location.LocationClient;
//
//public class Location extends Application {
//
//	public LocationClient mLocationClient = null;
//	public GeofenceClient mGeofenceClient;
//	private String mData;  
//	public MyLocationListenner myListener = new MyLocationListenner();
//	public TextView mTv;
//	public NotifyLister mNotifyer=null;
//	public Vibrator mVibrator01;
//	public static String TAG = "LocTestDemo";
//	
//	@Override
//	public void onCreate() {
//	
//		mLocationClient = new LocationClient( this );
//		/**������������������������������������������������������������������������������������������������������������������������������������
//		 * �����AK��Ӧ��ǩ�������󶨣����ʹ�����Լ��Ĺ�������Ҫ�滻Ϊ�Լ������Key
//		 * ������������������������������������������������������������������������������������������������������������������������������������
//		 */
//		mLocationClient.setAK("0Fa49070e3d9a18fb1df084293c5a335");
//		mLocationClient.registerLocationListener( myListener );
//		mGeofenceClient = new GeofenceClient(this);
//		//λ��������ش���
////		mNotifyer = new NotifyLister();
////		mNotifyer.SetNotifyLocation(40.047883,116.312564,3000,"gps");//4����������Ҫλ�����ѵĵ�����꣬���庬������Ϊ��γ�ȣ����ȣ����뷶Χ������ϵ����(gcj02,gps,bd09,bd09ll)
////		mLocationClient.registerNotify(mNotifyer);
//		
//		super.onCreate(); 
//		Log.d(TAG, "... Application onCreate... pid=" + Process.myPid());
//	}
//	
//	/**
//	 * ��ʾ�����ַ���
//	 * @param str
//	 */
//	public void logMsg(String str) {
//		try {
//			mData = str;
//			if ( mTv != null )
//				mTv.setText(mData);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * �����������и���λ�õ�ʱ�򣬸�ʽ�����ַ������������Ļ��
//	 */
//	public class MyLocationListenner implements BDLocationListener {
//		@Override
//		public void onReceiveLocation(BDLocation location) {
//			if (location == null)
//				return ;
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
////			if (location.getLocType() == BDLocation.TypeGpsLocation){
////				sb.append("\nspeed : ");
////				sb.append(location.getSpeed());
////				sb.append("\nsatellite : ");
////				sb.append(location.getSatelliteNumber());
////			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
//				/**
//				 * ��ʽ����ʾ��ַ��Ϣ
//				 */
////				sb.append("\nʡ��");
////				sb.append(location.getProvince());
////				sb.append("\n�У�");
////				sb.append(location.getCity());
////				sb.append("\n��/�أ�");
////				sb.append(location.getDistrict());
//				sb.append("\naddr : ");
//				sb.append(location.getCity());
////			}
//			sb.append("\nsdk version : ");
//			sb.append(mLocationClient.getVersion());
//			sb.append("\nisCellChangeFlag : ");
//			sb.append(location.isCellChangeFlag());
//			logMsg(sb.toString());
//			Log.i(TAG, sb.toString());
//		}
//		
//		public void onReceivePoi(BDLocation poiLocation) {
//			if (poiLocation == null){
//				return ; 
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
//			if (poiLocation.getLocType() == BDLocation.TypeNetWorkLocation){
//				sb.append("\naddr : ");
//				sb.append(poiLocation.getAddrStr());
//			} 
//			if(poiLocation.hasPoi()){
//				sb.append("\nPoi:");
//				sb.append(poiLocation.getPoi());
//			}else{				
//				sb.append("noPoi information");
//			}
//			logMsg(sb.toString());
//		}
//	}
//	
//	public class NotifyLister extends BDNotifyListener{
//		public void onNotify(BDLocation mlocation, float distance){
//			mVibrator01.vibrate(1000);
//		}
//	}
//}