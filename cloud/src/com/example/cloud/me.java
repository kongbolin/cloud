package com.example.cloud;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.baidu.location.r;
import com.example.cloud.R;

public class me extends Activity implements OnClickListener {
	private Button me;
	private Button order;
	private Button firstpage;
	private Button servers;
	private Button exist;
	private Button system_me;
	private Button system_exit;
	private TextView text_username;
	private int f = 1;
	private int o = 1;
	private int m = 0;
	private int displayheight;

	private LinearLayout me_label;
	private SharedPreferences pre;
	private String username;
	private String password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me);
		pre = getSharedPreferences("userinfo", MODE_PRIVATE);
		read();
		text_username = (TextView) findViewById(R.id.text_username);
		system_me = (Button) findViewById(R.id.system_me);
		system_exit = (Button) findViewById(R.id.system_exit);
		displayheight = getWindow().getWindowManager().getDefaultDisplay().getHeight();
		// me_label = (LinearLayout) findViewById(R.id.me_label);
		// RelativeLayout.LayoutParams layoutParams =
		// (android.widget.RelativeLayout.LayoutParams) me_label
		// .getLayoutParams();
		// layoutParams.topMargin = displayheight - 60;
		text_username.setText(username);
		me = (Button) findViewById(R.id.me);
		servers = (Button) findViewById(R.id.servers);
		order = (Button) findViewById(R.id.older);
		exist = (Button) findViewById(R.id.finish);
		firstpage = (Button) findViewById(R.id.firstpage);
		me.setOnClickListener(this);
		servers.setOnClickListener(this);
		exist.setOnClickListener(this);
		order.setOnClickListener(this);
		firstpage.setOnClickListener(this);
		system_me.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(me.this, our.class);
				// resultCode---请求码
				Bundle bundle = new Bundle(); // 创建Bundle对象
				// bundle.putString("something", temp_other); // 装入数据
				intent.putExtras(bundle);
				startActivityForResult(intent, 6);

			}

		});
		system_exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(me.this, login.class);
				// resultCode---请求码
				Bundle bundle = new Bundle(); // 创建Bundle对象
				// bundle.putString("something", temp_other); // 装入数据
				intent.putExtras(bundle);
				startActivityForResult(intent, 5);
				Write();
				finish();
			}

		});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
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
			AlertDialog alertDialog = new AlertDialog.Builder(me.this).create();
			// alertDialog.setIcon(R.drawable.desert);
			// alertDialog.setTitle("System Information:");
			AlertDialog.Builder settingDialog = new AlertDialog.Builder(this);
			settingDialog.setInverseBackgroundForced(true);
			alertDialog.setMessage("10086");
			// 添加取消按钮
			alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(me.this, "You clicked the Cancel Button", Toast.LENGTH_LONG).show();
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
			// Intent intent10 = new Intent();
			// intent10.setFlags(intent10.FLAG_ACTIVITY_CLEAR_TOP);
			// startActivity(intent10);
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

	private void read() {
		// TODO Auto-generated method stub
		username = pre.getString("username", "");
		password = pre.getString("password", "");
		Log.d("username", username);
		Log.d("password", password);
	}

	private void Write() {
		// TODO Auto-generated method stub
		Editor edit = pre.edit();
		edit.putString("username", "ewedsbgbfhb");
		edit.putString("password", "dvbsdghjhnhndf");
		edit.commit();
	}

}
