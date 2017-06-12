package com.example.schmidegv.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by schmidegv on 2017. 06. 12..
 */

public class HabitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabitContract() {}

    /**
     * Inner class that defines constant values for the habits database table.
     * Each entry in the table represents a single habit.
     */
    public static final class HabitEntry implements BaseColumns {

        /**
         * Name of database table for sports
         */
        public final static String TABLE_NAME = "habits";

        /**
         * Unique ID number for the habit (only for use in the database table).
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the sport
         * Type: TEXT
         */
        public final static String COLUMN_HABIT_SPORT = "sport";

        /**
         * Hours spent with the sport
         * Type: INTEGER
         */
        public final static String COLUMN_HABIT_HOURS = "hours";

        /**
         * Day of the sport
         * Type: TEXT
         */
        public final static String COLUMN_HABIT_DAY = "day";
    }
}
