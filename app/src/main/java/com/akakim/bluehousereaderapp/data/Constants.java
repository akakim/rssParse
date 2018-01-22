package com.akakim.bluehousereaderapp.data;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-12-23
 * @since 0.0.1
 */

public class Constants {
    public static final String PUBLIC_OPINION_BASE_URL = "https://www1.president.go.kr/petitions";
//    public static final String PUBLIC_OPINION_BEST_ORDER_URL = "https://www1.president.go.kr/petitions?order=best";

    // PUBLIC_OPINION_BASE_URL 쓰는 영역
    // get 방식으로 parameter를 보낸다.
    // ? 시작시
    // & 시작이후 중간 연결시
    public static final String BOARD_QUERY_PARAM = "page";              //
    public static final String BEST_CONTENT_LIST_PARAMS = "order=best";
    public static final String WECOME_TO_JO_GUK = "answer";             // 성능향상을 위해 이 페이지는 캐싱을 해야될거같다.


}
