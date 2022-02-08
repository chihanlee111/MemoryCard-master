package com.vongvia.acitivity;

import android.provider.BaseColumns;

/**
 * Created by liqihan on 2017/6/18.
 */

public class RankDbContract {
    public static final class rankEntry implements BaseColumns {
        public static final String TABLE_NAME = "ranklist";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
