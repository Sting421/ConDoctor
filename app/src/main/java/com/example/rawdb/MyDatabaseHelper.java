    package com.example.rawdb;

    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;
    import android.util.Log;
    import android.widget.Toast;
    import androidx.annotation.Nullable;

    class MyDatabaseHelper extends SQLiteOpenHelper {

        private Context context;
        private static final String DATABASE_NAME = "ConDoctor.db";
        private static final int DATABASE_VERSION = 3;

        private static final String TABLE_NAME = "my_library";
        private static final String COLUMN_ID = "_id";
        private static final String COLUMN_TITLE = "book_title";
        private static final String COLUMN_AUTHOR = "book_author";
        private static final String COLUMN_PAGES = "book_pages";

        /*  **********************************************************************
                                     Login
            ********************************************************************** */
        private static final String DOCTORS_DATA = "USERS";
        private static final String COLUMN_USERNAME = "username";
        private static final String COLUMN_PASSWORD = "password";



        MyDatabaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query1 = "CREATE TABLE " + TABLE_NAME +
                    " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_AUTHOR + " TEXT, " +
                    COLUMN_PAGES + " INTEGER);";
            db.execSQL(query1);

            String query2 = "CREATE TABLE " + DOCTORS_DATA +
                    " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT );";

            db.execSQL(query2);
        }



        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }

        void addBook(String title, String author, int pages){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_TITLE, title);
            cv.put(COLUMN_AUTHOR, author);
            cv.put(COLUMN_PAGES, pages);
            long result = db.insert(TABLE_NAME,null, cv);
            if(result == -1){
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
            }
        }
        void addUser(String username, String password){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_USERNAME, username);
            cv.put(COLUMN_PASSWORD, password);

            long result = db.insert(DOCTORS_DATA,null, cv);
            if(result == -1){
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
            }
        }

        Cursor readAllData(String username) {
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_AUTHOR + " = ?";
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = null;
            if (db != null) {
                cursor = db.rawQuery(query, new String[]{username});
            }
            return cursor;
        }



        void updateData(String row_id, String title, String author, String pages){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_TITLE, title);
            cv.put(COLUMN_AUTHOR, author);
            cv.put(COLUMN_PAGES, pages);

            long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
            if(result == -1){
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            }

        }

        void deleteOneRow(String row_id){
            SQLiteDatabase db = this.getWritableDatabase();
            long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
            if(result == -1){
                Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
            }
        }
        public int patientCounter(String doctorname) {
            int count = 0;
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE "+COLUMN_AUTHOR+" = ?";
            Cursor cursor = db.rawQuery(query, new String[]{doctorname});

            if (cursor != null) {
                count = cursor.getCount();
                cursor.close();
            }
            db.close();

            return count;
        }


        void deleteAllData(){
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DELETE FROM " + TABLE_NAME);
        }

        boolean userChecker(String username, String password) {
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT username FROM " + DOCTORS_DATA + " WHERE username = ? and password = ?";
            Cursor cursor = db.rawQuery(query, new String[]{username, password});
            boolean exists = cursor.getCount() > 0;
            cursor.close();
            db.close();
            return exists;
        }



    }