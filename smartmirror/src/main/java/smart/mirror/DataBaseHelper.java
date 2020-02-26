package smart.mirror;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLData;
//Smart Device (Haider Ibrahim, Minh Nguyen , Trung Trinh)
public class DataBaseHelper extends SQLiteOpenHelper {

     public static final String DATABASENAME = "waitinglist.db";
    public static final String TABLENAME = "waitingListStack";
    public static final String USERNAME = "name";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASENAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String action = "CREATE TABLE ";

       db.execSQL(action + TABLENAME + " ("+ USERNAME + "TEXT PRIMARY KEY"+ ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String action = " EXIST ";
     db.execSQL("DROP TABLE IF"+ action  + TABLENAME);
    }

    public boolean insertData(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(USERNAME,name);

        long result =db.insert(TABLENAME,null,contentvalues);

        if(result==-1){
            return false;

        }else{
            return true;
        }
    }
}
