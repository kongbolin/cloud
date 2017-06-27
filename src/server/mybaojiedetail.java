package server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.cloud.R;
import android.app.Activity;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class mybaojiedetail extends Activity {
	private int btn_baojie_order_cancle_cle = 0;
	private int btn_baojie_order_confirm_cicle = 0;
	private int btn_baojie_order_confirm_cle = 0;
	private TextView text_baojie_order_typenamescreen;
	private TextView text_baojie_order_otherneedscreen;
	private TextView text_baojie_order_addressscreen;
	private TextView text_baojie_order_contactscreen;
	private TextView text_baojie_order_phonenumberscreen;
	private TextView text_baojie_order_lengthtimescreen;
	private TextView text_baojie_order_totalpricescreen;
	private TextView text_baojie_order_gotimescreen;
	private String typename = "error";
	private String otherneed = "error";
	private String address = "error";
	private String contact = "error";
	private String phonenumber = "error";
	private String lengthtime = "error";
	private String totalprice = "error";
	private String gotime = "error";
	private String orderid = "error";
	private String userid = "error";
	private TextView text_baojie_order_detail;
	private Button btn_baojie_order_cancle;
	private Button btn_baojie_order_confirm;
	private int ct_id;
	private String ct_name;
	JSONArray jArray;
	String result = null;
	InputStream is = null;
	StringBuilder sb = null;
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				System.out.println("handleMessage thread id " + Thread.currentThread().getId());
				System.out.println("msg.arg1:" + msg.arg1);
				System.out.println("msg.arg2:" + msg.arg2);
				System.out.println("sfcadDdaf");
				go();
				// address.this.statusTextView.setText("文件下载完成");
				break;
			case 2:
				System.out.println("handleMessage thread id " + Thread.currentThread().getId());
				System.out.println("msg.arg1:" + msg.arg1);
				System.out.println("msg.arg2:" + msg.arg2);
				System.out.println("sfcaf");
				goback();

				// address.this.statusTextView.setText("文件下载完成");
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mybaojiedetail);
		btn_baojie_order_cancle = (Button) findViewById(R.id.btn_baojie_order_cancle);
		btn_baojie_order_confirm = (Button) findViewById(R.id.btn_baojie_order_confirm);
		text_baojie_order_typenamescreen = (TextView) findViewById(R.id.text_baojie_order_typenamescreen);
		text_baojie_order_otherneedscreen = (TextView) findViewById(R.id.text_baojie_order_otherneedscreen);
		text_baojie_order_addressscreen = (TextView) findViewById(R.id.text_baojie_order_addressscreen);
		text_baojie_order_contactscreen = (TextView) findViewById(R.id.text_baojie_order_contactscreen);
		text_baojie_order_phonenumberscreen = (TextView) findViewById(R.id.text_baojie_order_phonenumberscreen);
		text_baojie_order_lengthtimescreen = (TextView) findViewById(R.id.text_baojie_order_lengthtimescreen);
		text_baojie_order_totalpricescreen = (TextView) findViewById(R.id.text_baojie_order_totalpricescreen);
		text_baojie_order_gotimescreen = (TextView) findViewById(R.id.text_baojie_order_gotimescreen);
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		orderid = bundle.getString("orderid");
		userid = bundle.getString("userid");
		Log.d("baojiedetail", orderid);
		Log.d("baojiedetail", userid);
		btn_baojie_order_cancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				(new Thread() {
					@Override
					public void run() {
						ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
						btn_baojie_order_cancle_cle = 0;
						for (btn_baojie_order_cancle_cle = 0; btn_baojie_order_cancle_cle < 1; btn_baojie_order_cancle_cle = btn_baojie_order_cancle_cle) {
							try {
								nameValuePairs.add(new BasicNameValuePair("userid", userid));
								nameValuePairs.add(new BasicNameValuePair("orderid", orderid));
								HttpClient httpclient = new DefaultHttpClient();
								HttpPost httppost = new HttpPost("http://www.sundaytek.com/deletebaojieorder.php");
								httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
								HttpResponse response = httpclient.execute(httppost);
								HttpEntity entity = response.getEntity();
								btn_baojie_order_cancle_cle = 1;
								is = entity.getContent();
								Log.e("log_tag", "isme ");
							} catch (Exception e) {
								Log.d("log_tag", "Error in http connection" + e.toString());
								btn_baojie_order_cancle_cle = 0;
							}
						}
						Message msg = new Message();
						msg.what = 2;
						msg.arg1 = 123;
						msg.arg2 = 321;
						Log.e("log_tag", "把effewf车开 ");
						uiHandler.sendMessage(msg);
					}
				}).start();
			}
		});
		btn_baojie_order_confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("staues", true);
				setResult(0, intent);
				finish();
			}

		});
		(new Thread() {

			@Override

			public void run() {
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				// http get
				btn_baojie_order_confirm_cicle = 0;
				btn_baojie_order_confirm_cle = 0;
				for (btn_baojie_order_confirm_cle = 0; btn_baojie_order_confirm_cle < 1; btn_baojie_order_confirm_cle = btn_baojie_order_confirm_cle) {
					try {
						nameValuePairs.add(new BasicNameValuePair("userid", userid));
						nameValuePairs.add(new BasicNameValuePair("orderid", orderid));
						HttpClient httpclient = new DefaultHttpClient();
						HttpPost httppost = new HttpPost("http://www.sundaytek.com/selectbaojieorder.php");
						httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
						HttpResponse response = httpclient.execute(httppost);
						HttpEntity entity = response.getEntity();
						is = entity.getContent();
						btn_baojie_order_confirm_cicle = 0;
						btn_baojie_order_confirm_cle = 0;
						Log.e("log_tag", "isme ");
					} catch (Exception e) {
						Log.d("log_tag", "Error in http connection" + e.toString());
						btn_baojie_order_confirm_cicle = 1;
					}
					if (btn_baojie_order_confirm_cicle == 0) {
						try {
							BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
							sb = new StringBuilder();
							sb.append(reader.readLine() + "\n");

							String line = "0";
							while ((line = reader.readLine()) != null) {
								sb.append(line + "\n");
							}
							is.close();
							result = sb.toString();
							btn_baojie_order_confirm_cle = 1;
							result = delete(result);
							Log.e("log_tag", "ismetoo ");
						} catch (Exception e) {
							Log.e("log_tag", "Error converting result " + e.toString());
						}
						// paring data
						String typename1;
						String otherneed1;
						String address1;
						String contact1;
						String phonenumber1;
						String lengthtime1;
						String totalprice1;
						String gotime1;
						try {
							jArray = new JSONArray(result);
							JSONObject json_data = null;
							btn_baojie_order_confirm_cle = 1;
							for (int i = 0; i < jArray.length(); i++) {
								json_data = jArray.getJSONObject(i);

								typename1 = json_data.getString("typename");
								otherneed1 = json_data.getString("otherneed");
								address1 = json_data.getString("address");
								contact1 = json_data.getString("contact");
								phonenumber1 = json_data.getString("phonenumber");
								lengthtime1 = json_data.getString("lengthtime");
								totalprice1 = json_data.getString("totalprice");
								gotime1 = json_data.getString("gotime");
								typename = typename1;
								otherneed = otherneed1;
								address = address1;
								contact = contact1;
								phonenumber = phonenumber1;
								lengthtime = lengthtime1;
								totalprice = totalprice1;
								gotime = gotime1;
								Message msg = new Message();
								msg.what = 1;
								msg.arg1 = 123;
								msg.arg2 = 321;
								uiHandler.sendMessage(msg);
							}
						} catch (JSONException e1) {
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}).start();
	}

	protected void goback() {
		Intent intent = new Intent();
		intent.putExtra("staues", false);
		setResult(0, intent);
		finish();
	}

	protected void go() {
		text_baojie_order_typenamescreen.setText(typename);
		text_baojie_order_otherneedscreen.setText(otherneed);
		text_baojie_order_addressscreen.setText(address);
		text_baojie_order_contactscreen.setText(contact);
		text_baojie_order_phonenumberscreen.setText(phonenumber);
		text_baojie_order_lengthtimescreen.setText(lengthtime);
		text_baojie_order_totalpricescreen.setText(totalprice);
		text_baojie_order_gotimescreen.setText(gotime);
	}

	protected String delete(String result2) {
		String str = result2;
		String b[] = str.split("");// 截取字符串为数组
		int k = b.length;
		String sss = Integer.toString(k);
		Log.i("log_tag", sss);
		int i = 0;
		int j = 0;
		for (int p = i; p < k; p++) {
			if (b[p].equals("[")) {
				p = k;
			} else {
				b[p] = "";
			}
			j++;
		}
		String sss1 = Integer.toString(j);
		Log.i("log_tag", sss1);
		String right = "";
		for (int s = j - 1; s < k; s++) {
			right = right + b[s];
		}
		Log.d("log_tag", "right");
		Log.i("log_tag", right);
		return right;

	}
}
