package com.example.cloud;

import android.widget.AdapterView.OnItemSelectedListener;
import java.util.ArrayList;
import java.util.List;
//package com.example.cloud;

import android.util.Log;
import com.example.cloud.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.Toast;

public class viewpaper extends Activity implements OnClickListener {
	private ViewPager viewpager;
	private testadapter adapter;
	private LinearLayout viewGroup;
	private ArrayList<View> list;
	private ImageView dot, dots[];
	private int h = 0;
	private Button btn_nice;
	private Button btn_baojie;
	private Button btn_zhongdiangong;
	private Button btn_baomu;
	private Button btn_yuer;
	private Button btn_oldman;
	private Button btn_yuesao;
	private Button btn_yuesao1;
	private Button btn_yuesao2;
	private Button me;
	private Button order;
	private Button firstpage;
	private Button servers;
	private Button exist;
	private Runnable runnable;
	private int f = 0;
	private int o = 1;
	private int m = 1;
	private int autoChangeTime = 5000;
	private int memory = 0;
	private LinearLayout dotoc;
	private int width1;
	private int width2;
	private int heigth1;
	private int heigth2;

	// public LocationClient mLocationClient = null;
	// public GeofenceClient mGeofenceClient;
	// private String mData;
	// public MyLocationListenner myListener = new MyLocationListenner();
	// public TextView mTv;
	// public NotifyLister mNotifyer=null;
	// public Vibrator mVibrator01;
	// public static String TAG = "LocTestDemo";
	// private String phonenumber="10086";
	// private Handler handler;
	// private final int SPLASH_DISPLAY_LENGHT = 3000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.first);？
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.first);
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		width1 = getWindow().getWindowManager().getDefaultDisplay().getWidth();
		heigth1 = (int) (width1 / 2.267);
		FrameLayout.LayoutParams layoutParams1 = (android.widget.FrameLayout.LayoutParams) viewpager.getLayoutParams();
		layoutParams1.height = heigth1;
		viewpager.setLayoutParams(layoutParams1);
		viewGroup = (LinearLayout) findViewById(R.id.viewGroup);
		FrameLayout.LayoutParams layoutParams = (android.widget.FrameLayout.LayoutParams) viewGroup.getLayoutParams();
		layoutParams.topMargin = heigth1 - 40;// 将默认的距离底部20dp，改为0，这样底部区域全被listview填满。
		viewGroup.setLayoutParams(layoutParams);
		Context ctx = viewpaper.this;
		SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
		memory = sp.getInt("INT_KEY", 0);
		// 获取下拉列表对象
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);

		// 获取字符串数组
		final String[] stringArray = getResources().getStringArray(R.array.stringArray);
		String temp;
		temp = stringArray[0];
		stringArray[0] = stringArray[memory];
		stringArray[memory] = temp;
		// 通过适配器 填充数据
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				stringArray);

		spinner.setAdapter(adapter);

		// 设置一个选中的监听事件
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				int index = arg0.getSelectedItemPosition(); // 选中的pos
				Context ctx = viewpaper.this;
				SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
				// 存入数据
				Editor editor = sp.edit();
				// editor.putString("STRING_KEY", "string");
				Log.d("SP", stringArray[index]);
				if (index != 0) {
					editor.putInt("INT_KEY", index);
					// editor.putBoolean("BOOLEAN_KEY", true);
					editor.commit();
				}

				// 返回STRING_KEY的值
				// Log.d("SP", sp.getString("STRING_KEY", "none"));
				// 如果NOT_EXIST不存在，则返回值为"none"
				// Log.d("SP", sp.getString("NOT_EXIST", "none"));

				Toast.makeText(getBaseContext(), "SELECT: " + stringArray[index], Toast.LENGTH_SHORT).show();

			}

			//
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});
		// mLocationClient = new LocationClient( this );
		// /**——————————————————————————————————————————————————————————————————
		// * 这里的AK和应用签名包名绑定，如果使用在自己的工程中需要替换为自己申请的Key
		// * ——————————————————————————————————————————————————————————————————
		// */
		// mLocationClient.setAK("0Fa49070e3d9a18fb1df084293c5a335");
		// mLocationClient.registerLocationListener( myListener );
		// mGeofenceClient = new GeofenceClient(this);
		// //位置提醒相关代码
		// mNotifyer = new NotifyLister();
		// mNotifyer.SetNotifyLocation(40.047883,116.312564,3000,"gps");//4个参数代表要位置提醒的点的坐标，具体含义依次为：纬度，经度，距离范围，坐标系类型(gcj02,gps,bd09,bd09ll)
		// mLocationClient.registerNotify(mNotifyer);

		// super.onCreate();
		// Log.d(TAG, "... Application onCreate... pid=" + Process.myPid());
		// btn_nice=(Button) findViewById(R.id.btn_nice);
		// btn_nice.setOnClickListener(this);
		// new Handler().postDelayed(new Runnable(){
		//
		// @Override
		// public void run() {
		// Intent mainIntent = new Intent(viewpaper.this,Choose.class);
		// Welcome.this.startActivity(mainIntent);
		// Welcome.this.finish();
		// }
		//
		// }, SPLASH_DISPLAY_LENGHT);
		//
		// handler = new Handler()
		//
		// {
		// @Override
		// public void handleMessage(Message msg) {
		// if (msg.what == 1) {
		// setContentView(R.layout.first);
		// ;
		//
		// }
		// }
		// };

		btn_baojie = (Button) findViewById(R.id.btn_baojie);
		btn_zhongdiangong = (Button) findViewById(R.id.btn_zhongdiangong);
		btn_baomu = (Button) findViewById(R.id.btn_baomu);
		btn_yuer = (Button) findViewById(R.id.btn_yuer);
		btn_oldman = (Button) findViewById(R.id.btn_oldman);
		btn_yuesao = (Button) findViewById(R.id.btn_yuesao);
		btn_yuesao1 = (Button) findViewById(R.id.btn_yuesao1);
		btn_yuesao2 = (Button) findViewById(R.id.btn_yuesao2);
		me = (Button) findViewById(R.id.me);
		servers = (Button) findViewById(R.id.servers);
		order = (Button) findViewById(R.id.older);
		exist = (Button) findViewById(R.id.finish);
		firstpage = (Button) findViewById(R.id.firstpage);
		btn_baojie.setOnClickListener(this);
		btn_zhongdiangong.setOnClickListener(this);
		btn_baomu.setOnClickListener(this);
		btn_yuer.setOnClickListener(this);
		btn_oldman.setOnClickListener(this);
		btn_yuesao.setOnClickListener(this);
		btn_yuesao1.setOnClickListener(this);
		btn_yuesao2.setOnClickListener(this);
		me.setOnClickListener(this);
		servers.setOnClickListener(this);
		exist.setOnClickListener(this);
		order.setOnClickListener(this);
		firstpage.setOnClickListener(this);
		// Runnable runnable = new Runnable() {
		//
		// @Override
		// public void run() {
		// // TODO Auto-generated method stub
		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// handler.sendEmptyMessage(1);
		// }
		// };
		// Thread thread = new Thread(runnable);
		// thread.start();
		initViewPager();
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }
	private void initViewPager() {
		adapter = new testadapter(this);
		adapter.change(getList());

		viewpager.setAdapter(adapter);
		viewpager.setOnPageChangeListener(myOnPageChangeListener);
		viewpager.setOnClickListener(this);
		// viewpager.setOnClickListener(onClick);
		initDot();

		runnable = new Runnable() {
			@Override
			public void run() {
				int next = viewpager.getCurrentItem() + 1;
				if (next >= adapter.getCount()) {
					next = 0;
				}
				viewHandler.sendEmptyMessage(next);
			}
		};
		viewHandler.postDelayed(runnable, autoChangeTime);
	}

	private List<Integer> getList() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(R.drawable.pictureone);
		list.add(R.drawable.picturetwo);
		list.add(R.drawable.picturethree);
		list.add(R.drawable.picturefour);

		return list;
	}

	// 初始化dot视图
	private void initDot() {
		viewGroup = (LinearLayout) findViewById(R.id.viewGroup);

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(40, 40);
		layoutParams.setMargins(4, 3, 4, 3);

		dots = new ImageView[adapter.getCount()];
		for (int i = 0; i < adapter.getCount(); i++) {
			dot = new ImageView(this);

			dot.setLayoutParams(layoutParams);
			dots[i] = dot;
			dots[i].setTag(i);
			dots[i].setOnClickListener(onClick);

			if (i == 0) {
				dots[i].setBackgroundResource(R.drawable.dotc);
			} else {
				dots[i].setBackgroundResource(R.drawable.dotn);
			}

			viewGroup.addView(dots[i]);
		}
	}

	OnPageChangeListener myOnPageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			setCurDot(arg0);
			next(arg0);

			viewHandler.removeCallbacks(runnable);
			viewHandler.postDelayed(runnable, autoChangeTime);
		}

	};

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.btn_baojie:
			Intent intent = new Intent();
			intent.setClass(this, baojie.class);
			startActivity(intent);
			break;
		case R.id.btn_zhongdiangong:
			Intent intent1 = new Intent();
			intent1.setClass(this, zhongdiangong.class);
			startActivity(intent1);
			break;
		case R.id.btn_baomu:
			Intent intent2 = new Intent();
			intent2.setClass(this, baomu.class);
			startActivity(intent2);
			break;
		case R.id.btn_yuer:
			Intent intent3 = new Intent();
			intent3.setClass(this, yuer.class);
			startActivity(intent3);
			break;
		case R.id.btn_oldman:
			Intent intent4 = new Intent();
			intent4.setClass(this, oldman.class);
			startActivity(intent4);
			break;
		case R.id.btn_yuesao:
			Intent intent5 = new Intent();
			intent5.setClass(this, yuesao.class);
			startActivity(intent5);
			break;
		case R.id.btn_yuesao1:
			Intent intent05 = new Intent();
			intent05.setClass(this, yuesao.class);
			startActivity(intent05);
			break;
		case R.id.btn_yuesao2:
			Intent intent15 = new Intent();
			intent15.setClass(this, yuesao.class);
			startActivity(intent15);
			break;
		case R.id.me:
			if (m == 1) {
				Intent intent6 = new Intent();
				intent6.setClass(this, me.class);
				startActivity(intent6);
				m = 0;
				f = 1;
				o = 1;
				finish();
			}
			
			break;
		case R.id.older:
			if (o == 1) {
				Intent intent7 = new Intent();
				intent7.setClass(this, order.class);
				startActivity(intent7);
				f = 1;
				m = 1;
				o = 0;
				finish();
			}
			
			break;
		case R.id.servers:

			AlertDialog alertDialog = new AlertDialog.Builder(viewpaper.this).create();
			// alertDialog.setIcon(R.drawable.desert);
			// alertDialog.setTitle("System Information:");
			AlertDialog.Builder settingDialog = new AlertDialog.Builder(this);
			settingDialog.setInverseBackgroundForced(true);
			alertDialog.setMessage("10086");
			// 添加取消按钮
			alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(viewpaper.this, "You clicked the Cancel Button", Toast.LENGTH_LONG).show();
				}
			});
			// 添加确定按钮
			alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "拨打", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// Toast.makeText(viewpaper.this,"You clicked the Confirm
					// Button",Toast.LENGTH_LONG).show();
					String phoneNumber = "10086";
					// 3.给这个号码打电话
					Intent intent8 = new Intent();
					intent8.setAction("android.intent.action.CALL");
					intent8.addCategory("android.intent.category.DEFAULT");
					intent8.setData(Uri.parse("tel:" + phoneNumber));
					startActivity(intent8);
				}
			});
			// 添加中立按钮
			// alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,"neutral",new
			// DialogInterface.OnClickListener() {
			// @Override
			// public void onClick(DialogInterface dialog, int which) {
			// Toast.makeText(viewpaper.this,"You clicked the neutral
			// Button",Toast.LENGTH_LONG).show();
			// }
			// });
			alertDialog.show();
			break;
		case R.id.finish:
//			Intent intent10 = new Intent();
//			intent10.setFlags(intent10.FLAG_ACTIVITY_CLEAR_TOP);
//			startActivity(intent10);
			finish();
			break;
		case R.id.firstpage:
			if (f == 1) {
				Intent intent9 = new Intent();
				intent9.setClass(this, viewpaper.class);
				startActivity(intent9);
				f = 0;
				m = 1;
				o = 1;
				finish();
			}
			
			break;
		default:
			break;

		}
	}

	private void next(int position) {
		// TODO Auto-generated method stub
		for (int i = 0; i < dots.length; i++) {
			if (position == i) {

				h = i;
			}
		}
	}

	public Object instantiateItem(ViewGroup container, final int position) {
		// 为每页添加点击监听，初始化音乐并点击时播放，并保证每次点击都可以重新播放
		switch (position) {
		case 0:
			list.get(0).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Log.d("SP12354656", "555");

				}
			});
			break;
		// case 1:
		// list.get(1).setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View view) {
		// Log.e("log", "你当前选择的是 cat ");
		// if(mediaPlayer!=null)
		// {
		// if (mediaPlayer.isPlaying())
		// {
		// mediaPlayer.stop();
		// mediaPlayer = null;
		// }
		// }
		// initMediaPlayer(1);
		// mediaPlayer.start();
		// }
		// });
		// break;
		// case 2:
		// list.get(2).setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View view) {
		// Log.e("log", "你当前选择的是 cat ");
		// if(mediaPlayer!=null)
		// {
		// if (mediaPlayer.isPlaying())
		// {
		// mediaPlayer.stop();
		// mediaPlayer = null;
		// }
		// }
		// initMediaPlayer(2);
		// mediaPlayer.start();
		// }
		// });
		// break;
		// case 3:
		// list.get(3).setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View view) {
		// Log.e("log", "你当前选择的是 cat ");
		// if(mediaPlayer!=null)
		// {
		// if (mediaPlayer.isPlaying())
		// {
		// mediaPlayer.stop();
		// mediaPlayer = null;
		// }
		// }
		// initMediaPlayer(3);
		// mediaPlayer.start();
		// }
		// });
		// break;
		// case 4:
		// list.get(4).setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View view) {
		// Log.e("log", "你当前选择的是 cat ");
		// if(mediaPlayer!=null)
		// {
		// if (mediaPlayer.isPlaying())
		// {
		// mediaPlayer.stop();
		// mediaPlayer = null;
		// }
		// }
		// initMediaPlayer(4);
		// mediaPlayer.start();
		// }
		// });
		// break;
		default:
			break;
		}
		((ViewPager) container).addView(list.get(position % list.size()), 0);
		return list.get(position % list.size());
	}

	// 实现dot点击响应功能
	OnClickListener onClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			int position = (Integer) v.getTag();
			setCurView(position);
			// switch (h) {
			// case 0:
			//
			//// list.get(0).setOnClickListener(new View.OnClickListener() {
			//// @Override
			//// public void onClick(View view) {
			//// Log.e("log", "你当前选择的是 dog ");
			//// if(mediaPlayer!=null)
			//// {
			//// if (mediaPlayer.isPlaying())
			//// {
			//// mediaPlayer.stop();
			//// mediaPlayer = null;
			//// }
			//// }
			//// initMediaPlayer(0);
			//// mediaPlayer.start();
			//// }
			//// });
			//// Intent intent = new Intent();
			//// intent.setClass(viewpaper.this, baojie.class);
			//// startActivity(intent);
			// break;
			// case 1:
			//
			// Intent intent1 = new Intent();
			// intent1.setClass(viewpaper.this, yuer.class);
			// startActivity(intent1);
			// break;
			// case 2:
			//
			// Intent intent2 = new Intent();
			// intent2.setClass(viewpaper.this, yuesao.class);
			// startActivity(intent2);
			// break;
			// case 3:
			//
			// Intent intent3 = new Intent();
			// intent3.setClass(viewpaper.this, oldman.class);
			// startActivity(intent3);
			// break;
			// default:
			// break;
			// }
		}

	};

	/**
	 * 设置当前的引导页
	 */
	private void setCurView(int position) {
		if (position < 0 || position > adapter.getCount()) {
			return;
		}
		viewpager.setCurrentItem(position);
	}

	/**
	 * 选中当前引导小点
	 */
	private void setCurDot(int position) {
		for (int i = 0; i < dots.length; i++) {
			if (position == i) {
				dots[i].setBackgroundResource(R.drawable.dotc);
			} else {
				dots[i].setBackgroundResource(R.drawable.dotn);
			}
		}
	}

	/**
	 * 每隔固定时间切换广告栏图片
	 */
	@SuppressLint("HandlerLeak")
	private final Handler viewHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			setCurView(msg.what);
		}

	};
	// public void logMsg(String str) {
	// try {
	// mData = str;
	// if ( mTv != null )
	// mTv.setText(mData);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// /**
	// * 监听函数，有更新位置的时候，格式化成字符串，输出到屏幕中
	// */
	// public class MyLocationListenner implements BDLocationListener {
	// @Override
	// public void onReceiveLocation(BDLocation location) {
	// if (location == null)
	// return ;
	// StringBuffer sb = new StringBuffer(256);
	// sb.append("time : ");
	// sb.append(location.getTime());
	// sb.append("\nerror code : ");
	// sb.append(location.getLocType());
	// sb.append("\nlatitude : ");
	// sb.append(location.getLatitude());
	// sb.append("\nlontitude : ");
	// sb.append(location.getLongitude());
	// sb.append("\nradius : ");
	// sb.append(location.getRadius());
	//// if (location.getLocType() == BDLocation.TypeGpsLocation){
	//// sb.append("\nspeed : ");
	//// sb.append(location.getSpeed());
	//// sb.append("\nsatellite : ");
	//// sb.append(location.getSatelliteNumber());
	//// } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
	// /**
	// * 格式化显示地址信息
	// */
	//// sb.append("\n省：");
	//// sb.append(location.getProvince());
	//// sb.append("\n市：");
	//// sb.append(location.getCity());
	//// sb.append("\n区/县：");
	//// sb.append(location.getDistrict());
	// sb.append("\naddr : ");
	// sb.append(location.getCity());
	//// }
	// sb.append("\nsdk version : ");
	// sb.append(mLocationClient.getVersion());
	// sb.append("\nisCellChangeFlag : ");
	// sb.append(location.isCellChangeFlag());
	// logMsg(sb.toString());
	// Log.i(TAG, sb.toString());
	// }
	//
	// public void onReceivePoi(BDLocation poiLocation) {
	// if (poiLocation == null){
	// return ;
	// }
	// StringBuffer sb = new StringBuffer(256);
	// sb.append("Poi time : ");
	// sb.append(poiLocation.getTime());
	// sb.append("\nerror code : ");
	// sb.append(poiLocation.getLocType());
	// sb.append("\nlatitude : ");
	// sb.append(poiLocation.getLatitude());
	// sb.append("\nlontitude : ");
	// sb.append(poiLocation.getLongitude());
	// sb.append("\nradius : ");
	// sb.append(poiLocation.getRadius());
	// if (poiLocation.getLocType() == BDLocation.TypeNetWorkLocation){
	// sb.append("\naddr : ");
	// sb.append(poiLocation.getAddrStr());
	// }
	// if(poiLocation.hasPoi()){
	// sb.append("\nPoi:");
	// sb.append(poiLocation.getPoi());
	// }else{
	// sb.append("noPoi information");
	// }
	// logMsg(sb.toString());
	// }
	// }
	//
	// public class NotifyLister extends BDNotifyListener{
	// public void onNotify(BDLocation mlocation, float distance){
	// mVibrator01.vibrate(1000);
	// }
	// }
}
