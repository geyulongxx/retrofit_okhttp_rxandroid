package com.ivwen.retrofitokhttprxandroid.retrofit;

/**
 * Created by meipian on 16/6/15.
 */
public class IpInfo
{
    public IpInfo data;
    public String country;
    public String country_id;
    public String area;
    public String area_id;
    public String region;
    public String region_id;
    public String city;
    public String city_id;
    public String county;
    public String county_id;
    public String isp;
    public String isp_id;
    public String ip;


    @Override
    public String toString()
    {
        return "IpInfo{" +
            "data=" + data +
            ", country='" + country + '\'' +
            ", country_id='" + country_id + '\'' +
            ", area='" + area + '\'' +
            ", area_id='" + area_id + '\'' +
            ", region='" + region + '\'' +
            ", region_id='" + region_id + '\'' +
            ", city='" + city + '\'' +
            ", city_id='" + city_id + '\'' +
            ", county='" + county + '\'' +
            ", county_id='" + county_id + '\'' +
            ", isp='" + isp + '\'' +
            ", isp_id='" + isp_id + '\'' +
            ", ip='" + ip + '\'' +
            '}';
    }
}
