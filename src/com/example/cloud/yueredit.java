package com.example.cloud;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class yueredit extends Activity{
	
	private Button btn_yuer_cancel;
	private Button btn_yuer_affirm;
	private EditText text_yuer_otherneed;
private String temp_other;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yueredit);
		btn_yuer_cancel = (Button) findViewById(R.id.btn_yuer_cancel);
		btn_yuer_affirm = (Button) findViewById(R.id.btn_yuer_affirm);
		text_yuer_otherneed=(EditText) findViewById(R.id.text_yuer_otherneed);
		Intent intent = this.getIntent();        //获取已有的intent对象   
		Bundle bundle = intent.getExtras();    //获取intent里面的bundle对象   
		temp_other = bundle.getString("something"); 
		text_yuer_otherneed.setText(temp_other);
		btn_yuer_affirm.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// resultCode----结果码
				Intent intent = new Intent();
//				intent.putExtra("otherneed", text_otherneed.getText().toString());
				intent.putExtra("otherneed", text_yuer_otherneed.getText().toString());
				setResult(0, intent);
				finish();
			}

		});
		btn_yuer_cancel.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// resultCode----结果码
//				Intent intent = new Intent();
////				intent.putExtra("otherneed", text_otherneed.getText().toString());
//				intent.putExtra("otherneed", null);
//				setResult(0, intent);
				finish();
			}

		});
	}

}
