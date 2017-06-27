package com.example.cloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class baomuedit extends Activity{
	
	private Button btn_baomu_cancel;
	private Button btn_baomu_affirm;
	private EditText text_baomu_otherneed;
	private String temp_other;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baomuedit);
		btn_baomu_cancel = (Button) findViewById(R.id.btn_baomu_cancel);
		btn_baomu_affirm = (Button) findViewById(R.id.btn_baomu_affirm);
		text_baomu_otherneed=(EditText) findViewById(R.id.text_baomu_otherneed);
		Intent intent = this.getIntent();        //��ȡ���е�intent����   
		Bundle bundle = intent.getExtras();    //��ȡintent�����bundle����   
		temp_other = bundle.getString("something"); 
		text_baomu_otherneed.setText(temp_other);
		btn_baomu_affirm.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// resultCode----�����
				Intent intent = new Intent();
//				intent.putExtra("otherneed", text_otherneed.getText().toString());
				intent.putExtra("otherneed", text_baomu_otherneed.getText().toString());
				setResult(0, intent);
				finish();
			}

		});
		btn_baomu_cancel.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// resultCode----�����
//				Intent intent = new Intent();
////				intent.putExtra("otherneed", text_otherneed.getText().toString());
//				intent.putExtra("otherneed", null);
//				setResult(0, intent);
				finish();
			}

		});
	}

}
