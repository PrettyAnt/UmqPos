package unionlive.com.umqpos.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/6 9:33
 * @describe 此类主要用来处理位置信息
 */

public class LocationInfo {
    private final Context mContext;
    private LocationManager mLocationManager;
    private String          mLocationProvider;

    private String mLatitude;//纬度
    private String mLongitude;//经度
    private String mCurrentTime;//当前时间
    private List<Address> mLocationList=null;
    private String mCountryName;//国家名
    private String mCountryCode;//国家代码
    private String mLocality;//所在城市
    private String mStreet0;//所在街道0
    private String mStreet1;//所在街道1
    private String mStreet2;//所在街道2
    private Location mLocation;

    public LocationInfo(Context context) {
        this.mContext = context;
        initLocation();//初始化参数
        if (mLocation!=null) {
            initAddress();//初始化国际代码,城市,等街道信息
        }
    }

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String latitude) {
        mLatitude = latitude;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public void setLongitude(String longitude) {
        mLongitude = longitude;
    }

    public String getCurrentTime() {
        return mCurrentTime;
    }

    public void setCurrentTime(String currentTime) {
        mCurrentTime = currentTime;
    }

    public String getCountryName() {
        return mCountryName;
    }

    public void setCountryName(String countryName) {
        mCountryName = countryName;
    }

    public String getCountryCode() {
        return mCountryCode;
    }

    public void setCountryCode(String countryCode) {
        mCountryCode = countryCode;
    }

    public String getLocality() {
        return mLocality;
    }

    public void setLocality(String locality) {
        mLocality = locality;
    }

    public String getStreet0() {
        return mStreet0;
    }

    public void setStreet0(String street0) {
        mStreet0 = street0;
    }

    public String getStreet1() {
        return mStreet1;
    }

    public void setStreet1(String street1) {
        mStreet1 = street1;
    }

    public String getStreet2() {
        return mStreet2;
    }

    public void setStreet2(String street2) {
        mStreet2 = street2;
    }

    private void initLocation() {
        mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);


//        if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            Intent intent = new Intent(
//                    Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//            mContext.startActivity(intent); //Fixme 这里最好还要处理下,如果gps关闭请求打开
//        }
        List<String> providers = mLocationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            mLocationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            mLocationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(mContext, "没找到可用的位置提供器", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocation = mLocationManager.getLastKnownLocation(mLocationProvider);
        if (mLocation != null) {
            showLocation(mLocation);
        } else {
//            Toast.makeText(mContext, "location为空", Toast.LENGTH_SHORT).show();
            Log.d("LocationInfo", "location为空");
            return;
        }

        //mLocationManager.requestLocationUpdates(mLocationProvider,3000,1, locationListener);
    }

    private void showLocation(Location location) {
        if (location != null) {
            mLongitude = String.valueOf(location.getLongitude());//经度
            setLongitude(mLongitude);//设置经度
            mLatitude = String.valueOf(location.getLatitude());//纬度
            setLatitude(mLatitude);//设置纬度
            long time = location.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date(time);
            mCurrentTime = sdf.format(date);//当前时间
            setCurrentTime(mCurrentTime);//设置时间
        }
    }

    LocationListener locationListener =  new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(Location location) {
            //如果位置发生变化,重新显示
            showLocation(location);

        }
    };
    private void initAddress() {
        Geocoder gc = new Geocoder(mContext, Locale.getDefault());
        try {

            mLocationList = gc.getFromLocation(Double.parseDouble(mLatitude), Double.parseDouble(mLongitude), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mLocationList == null) {
            return;
        }
        try {
            Address address = mLocationList.get(0);
            //设备所在国家名
            mCountryName = address.getCountryName();
            //设备所在国家代码
            mCountryCode = address.getCountryCode();
            //设备当前所在城市
            mLocality = address.getLocality();
            //街道地址0
            mStreet0 = address.getAddressLine(0);
            //街道地址1
            mStreet1 = address.getAddressLine(1);
            //街道地址2
            mStreet2 = address.getAddressLine(2);
        } catch (Exception e) {

        }

    }
}
