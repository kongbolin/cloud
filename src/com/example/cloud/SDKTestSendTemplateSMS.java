package com.example.cloud;

import java.util.HashMap;
import java.util.Set;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.cloopen.rest.sdk.CCPRestSDK.BodyType;

import android.content.Loader;
import android.util.Log;

public class SDKTestSendTemplateSMS {
	public static void main(String args, String username) {
		HashMap result = null;
		CCPRestSDK restAPI = new CCPRestSDK();
		Log.d("rtt", "gjg");
		restAPI.init("sandboxapp.cloopen.com", "8883");
		// ��ʼ����������ַ�Ͷ˿ڣ�ɳ�л������ó�sandboxapp.cloopen.com�������������ó�app.cloopen.com���˿ڶ���8883.
		restAPI.setAccount("8a48b5514f73ea32014f92bbf3ea3cd1", "38edf732f1004b2e8b59497adb288928");
		// ��ʼ�����˺����ƺ����˺����ƣ���½��ͨѶ��վ�󣬿���"����̨-Ӧ��"�п������������˺�ACCOUNT SID��
		// ���˺�����AUTH TOKEN��
		restAPI.setAppId("8a48b5514f73ea32014f92bfe4763cdd");
		// restAPI.setAccount("8a48b5514fa577af014fa86e43010bc9",
		// "617e4b3eb40c4af28e680583a55c0012");
		// restAPI.setAppId("8a48b5514fa577af014fa86ed0730bcc");//
		// ��ʼ��Ӧ��ID���������ɳ�л���������������"����̨-Ӧ��-����DEMO"�е�APPID��
		// ���л���������������ʹ���Լ�����Ӧ�õ�APPID
		Log.d("rtdssgt", "gsdfafjg");
		result = restAPI.sendTemplateSMS(username, "1", new String[] { args, "2" });
		Log.d("rtt", "gsdfafjg");
		System.out.println("SDKTestGetSubAccounts result=" + result);
		if ("000000".equals(result.get("statusCode"))) {
			// �����������data������Ϣ��map��
			HashMap data = (HashMap) result.get("data");
			Set<String> keySet = data.keySet();
			for (String key : keySet) {
				Object object = data.get(key);
				System.out.println(key + " = " + object);
			}
		} else {
			// �쳣�������������ʹ�����Ϣ
			System.out.println("������=" + result.get("statusCode") + " ������Ϣ= " + result.get("statusMsg"));
		}
	}
}