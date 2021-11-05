package com.fkeo2021.tolit;

import java.util.List;

public class ApiLocalResponse {
    PlaceMeta meta;
    List<Place> documents;
}

class PlaceMeta {
    int total_count;
    int pageable_count;
    boolean is_end;
}

class  Place{
    String id;
    String place_name;
    String category_name;
    String phone;
    String road_address_name;
    String x;
    String y;
    String place_url;

}
