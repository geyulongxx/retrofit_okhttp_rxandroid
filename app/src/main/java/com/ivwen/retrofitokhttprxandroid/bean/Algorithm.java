package com.ivwen.retrofitokhttprxandroid.bean;

import java.security.MessageDigest;

/**
 * Created by t00333907 on 2015/12/15.
 */
public class Algorithm
{
    public static String MD5(String input)
    {
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(input.getBytes());
            byte[] md5Bytes = messageDigest.digest();

            StringBuilder hexValue = new StringBuilder();
            for (byte md5Byte : md5Bytes)
            {
                int val = md5Byte & 0xff;
                if (val < 16)
                {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }

            return hexValue.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }
}
