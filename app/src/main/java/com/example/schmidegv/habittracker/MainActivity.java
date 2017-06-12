package com.example.schmidegv.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schmidegv.habittracker.data.HabitContract.HabitEntry;
import com.example.schmidegv.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    /** Database helper that will provide us access to the database */
    private HabitDbHelper mDbHelper = new HabitDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertHabit();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the habits database.
     */
    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_SPORT,
                HabitEntry.COLUMN_HABIT_HOURS,
                HabitEntry.COLUMN_HABIT_DAY};

        // Perform a query on the habits table
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                 // The sort order

        TextView displayView = (TextView) findViewById(R.id.info_text_view);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The habits table contains <number of rows in Cursor> habits.
            // _id - sport - hours spent - day
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The habits table contains " + cursor.getCount() + " habits.\n\n");
            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_HABIT_SPORT + " - " +
                    HabitEntry.COLUMN_HABIT_HOURS + " - " +
                    HabitEntry.COLUMN_HABIT_DAY + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int sportColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_SPORT);
            int hoursColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_HOURS);
            int dayColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DAY);

            cursor.moveToFirst();
            // Use that index to extract the String or Int value of the word
            // at the current row the cursor is on.
            int currentID = cursor.getInt(idColumnIndex);
            String currentSport = cursor.getString(sportColumnIndex);
            int currentHours = cursor.getInt(hoursColumnIndex);
            String  currentDay = cursor.getString(dayColumnIndex);
            // Display the values from each column of the current row in the cursor in the TextView
            displayView.append(("\n" + currentID + " - " +
                    currentSport + " - " +
                    currentHours + " - " +
                    currentDay));
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    /**
     * Helper method to insert hardcoded habit data into the database. For debugging purposes only.
     */
    private void insertHabit() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and sport habit attributes are the values.
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_SPORT, "Aerobic");
        values.put(HabitEntry.COLUMN_HABIT_HOURS, 2);
        values.put(HabitEntry.COLUMN_HABIT_DAY, "Friday");
    }
}
