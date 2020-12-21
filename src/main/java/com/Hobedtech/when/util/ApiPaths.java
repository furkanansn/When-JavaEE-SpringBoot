package com.Hobedtech.when.util;


public final class ApiPaths {

    private static final String BASE_PATH = "/api";

    public static final String NORMALUSERAPIKEY = "77goJKlwme9bJ9WMEc5A";
    public static final String VENUEAPIKEY = "PM5n8TwxBL7ZDkmRshHe";
    public static final String ADMINAPIKEY = "9dTHINNUXsqA5O9tWHym";


    public static final class SearchCtrl {
        public static final String CTRL = BASE_PATH + "/search";
    }
    public static final class EventCtrl {
        public static final String CTRL = BASE_PATH + "/events";
    }
    public static final class FavCtrl {
        public static final String CTRL = BASE_PATH + "/fav";
    }
    public static final class FriendsCtrl {
        public static final String CTRL = BASE_PATH + "/friends";
    }
    public static final class UserCtrl {
        public static final String CTRL = BASE_PATH + "/users";
    }
    public static final class NotificationCtrl {
        public static final String CTRL = BASE_PATH + "/notification";
    }
    public static final class UserVipCtrl {
        public static final String CTRL = BASE_PATH + "/user-vip";
    }
    public static final class CommentCtrl {
        public static final String CTRL = BASE_PATH + "/comment";
    }

}
