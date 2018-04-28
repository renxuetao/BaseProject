package com.lenovo.video.utils;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by wanggl8 on 2017/12/29.
 */

public class AesUtil
{


	/**
	 * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
	 */
	public AesUtil()
	{

	}

	// 加密
	public static String encrypt(String sSrc, String sKey, String ivParameter) throws Exception
	{
		LogUtils.d("bigdata 进入aes加密，要加密的数据是：" + sSrc);
		if (null != sSrc && !"".equals(sSrc))
		{
			if (null != sKey && !"".equals(sKey.trim()))
			{

			}
			else
			{
				sKey = "0102030405060708";
			}
			if (null != ivParameter && !"".equals(ivParameter.trim()))
			{

			}
			else
			{
				ivParameter = "0102030405060708";
			}
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] raw = sKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
			return new String(Base64.encode(encrypted, Base64.NO_WRAP));// 此处使用BASE64做转码。
		}
		else
		{
			return "";
		}
	}

	// 解密
	public static String decrypt(String sSrc, String sKey, String ivParameter) throws Exception
	{
		try
		{
			if (null != sSrc && !"".equals(sSrc))
			{
				if (null != sKey && !"".equals(sKey))
				{

				}
				else
				{
					sKey = "0102030405060708";
				}
				if (null != ivParameter && !"".equals(ivParameter))
				{

				}
				else
				{
					ivParameter = "0102030405060708";
				}
				byte[] raw = sKey.getBytes("ASCII");
				SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
				cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

				byte[] encrypted1 = Base64.decode(sSrc, Base64.NO_WRAP);// 先用base64解密
				//				byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);

				byte[] original = cipher.doFinal(encrypted1);
				return new String(original, "utf-8");
			}
			else
			{
				return "";
			}
		}
		catch (Exception ex)
		{
			LogUtils.d(ex.toString());
			return null;
		}
	}
	}
