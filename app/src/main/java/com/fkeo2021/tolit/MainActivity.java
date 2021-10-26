package com.fkeo2021.tolit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml에서 만든 <fragment>[SupportMapFragment] 객체 참조
        FragmentManager fragmentManager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.frag_map);

        //비동기 방식으로 지도데이터 읽어오도록 요청
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                //지도준비가 끝났을때 발동 메소드
                //이 메소드 안에서 지도 관련 설정들하기

                //지도의 특정좌표 이동 및 줌인
                LatLng wang = new LatLng(37.561195, 127.037617);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(wang, 15));

                //마커추가
                MarkerOptions marker = new MarkerOptions();
                marker.position(wang);
                marker.title("현재위치");
                marker.snippet("왕십리역");

                googleMap.addMarker(marker);

                //줌기능
                UiSettings settings = googleMap.getUiSettings();
                settings.setZoomControlsEnabled(true);

                //내 위치 표시 하기 버튼 [실시간 업데이트 됨]
                settings.setMyLocationButtonEnabled(true);

                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);

            }
        });

        //지도에서 내 위치 탐색 허용으로 동적포미션 적용
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkResult = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
            if (checkResult == PackageManager.PERMISSION_DENIED) {
                String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
                requestPermissions(permissions, 0);
            }
        }
    }
    //다이얼로그 선택하면 발동하는 메소드

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
}