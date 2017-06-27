//package com.example.cloud;
//
//import com.baidu.location.LocationClient;
//import com.baidu.location.LocationClientOption;
//import com.example.cloud.R;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.net.Uri;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class view01 extends Activity {
//
//	TextView textView1;
//	private LocationClient mLocClient;
//	String str1;
//
//	// MyApplication app=MyApplication.getInstance();
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
//
//		mLocClient = ((Location) getApplication()).mLocationClient;
//		textView1 = (TextView) findViewById(R.id.textView2);
//		((Location) getApplication()).mTv = textView1;
//		setLocationOption();
//		mLocClient.start();
//		mLocClient.requestLocation();
//		Log.d("MainActivity", str1);
//		Log.d("MainActivity", "qqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
//
//		// Log.d("MainActivity",(String) app.getMap().get("data1"));
//		// Log.d("MainActivity",(String) app.getMap().get("data2"));
//		// Log.d("MainActivity",(String) app.getMap().get("data3"));
//		// Log.d("MainActivity",(String) app.getMap().get("data4"));
//		//
//	}
//
//	// 设置相关参数
//	private void setLocationOption() {
//		LocationClientOption option = new LocationClientOption();
//		// option.setOpenGps(true); // 打开gps
//		option.setCoorType("bd009ll"); // 设置坐标类型
//		option.setServiceName("com.baidu.location.service_v2.9");
//		option.setPoiExtraInfo(true);
//
//		option.setAddrType("all");
//
//		option.setScanSpan(3000);
//
//		option.setPriority(LocationClientOption.NetWorkFirst); // 设置网络优先
//
//		option.setPoiNumber(10);
//		option.disableCache(true);
//		mLocClient.setLocOption(option);
//		str1 = Location.str1;
//		for (int i = 0; i < 10000; i++) {
//			if (i == 0) {
//				AlertDialog alertDialog = new AlertDialog.Builder(view01.this).create();
//				// alertDialog.setIcon(R.drawable.desert);
//				// alertDialog.setTitle("System Information:");
//				AlertDialog.Builder settingDialog = new AlertDialog.Builder(this);
//				settingDialog.setInverseBackgroundForced(true);
//				alertDialog.setMessage(str1);
//				// 添加取消按钮
//				alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						Toast.makeText(view01.this, "You clicked the Cancel Button", Toast.LENGTH_LONG).show();
//					}
//				});
//				// 添加确定按钮
//				alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "拨打", new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// Toast.makeText(viewpaper.this,"You clicked the
//						// Confirm
//						// Button",Toast.LENGTH_LONG).show();
//						String phoneNumber = "18710861689";
//						// 3.给这个号码打电话
//						Intent intent8 = new Intent();
//						intent8.setAction("android.intent.action.CALL");
//						intent8.addCategory("android.intent.category.DEFAULT");
//						intent8.setData(Uri.parse("tel:" + phoneNumber));
//						startActivity(intent8);
//					}
//				});
//				// 添加中立按钮
//				// alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,"neutral",new
//				// DialogInterface.OnClickListener() {
//				// @Override
//				// public void onClick(DialogInterface dialog, int which) {
//				// Toast.makeText(viewpaper.this,"You clicked the neutral
//				// Button",Toast.LENGTH_LONG).show();
//				// }
//				// });
//				alertDialog.show();
//			} else if (i == 9999) {
//				AlertDialog alertDialog = new AlertDialog.Builder(view01.this).create();
//				// alertDialog.setIcon(R.drawable.desert);
//				// alertDialog.setTitle("System Information:");
//				AlertDialog.Builder settingDialog = new AlertDialog.Builder(this);
//				settingDialog.setInverseBackgroundForced(true);
//				alertDialog.setMessage(str1);
//				// 添加取消按钮
//				alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						Toast.makeText(view01.this, "You clicked the Cancel Button", Toast.LENGTH_LONG).show();
//					}
//				});
//				// 添加确定按钮
//				alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "拨打", new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// Toast.makeText(viewpaper.this,"You clicked the
//						// Confirm
//						// Button",Toast.LENGTH_LONG).show();
//						String phoneNumber = "18710861689";
//						// 3.给这个号码打电话
//						Intent intent8 = new Intent();
//						intent8.setAction("android.intent.action.CALL");
//						intent8.addCategory("android.intent.category.DEFAULT");
//						intent8.setData(Uri.parse("tel:" + phoneNumber));
//						startActivity(intent8);
//					}
//				});
//				// 添加中立按钮
//				// alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,"neutral",new
//				// DialogInterface.OnClickListener() {
//				// @Override
//				// public void onClick(DialogInterface dialog, int which) {
//				// Toast.makeText(viewpaper.this,"You clicked the neutral
//				// Button",Toast.LENGTH_LONG).show();
//				// }
//				// });
//				alertDialog.show();
//			}
//		}
//	}
//
//	// @Override
//	// public boolean onCreateOptionsMenu(Menu menu) {
//	// // Inflate the menu; this adds items to the action bar if it is present.
//	// getMenuInflater().inflate(R.menu.main, menu);
//	// return true;
//	// }
//
//}
