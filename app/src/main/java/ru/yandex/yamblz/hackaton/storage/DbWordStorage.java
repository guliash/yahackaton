package ru.yandex.yamblz.hackaton.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ru.yandex.yamblz.hackaton.core.Word;

public class DbWordStorage extends SQLiteOpenHelper implements WordsStorage {

    private static final int DATABASE_VERSION = 1;

    private static final String WORDS_TABLE = "words";

    private static final String CREATE_WORDS_TABLE = "create table %s (id integer primary key," +
            "text text unique,lang text,rating integer);";

    private Context mContext;

    public DbWordStorage(Context context) {
        super(context, "words", null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format(CREATE_WORDS_TABLE, WORDS_TABLE));
        migrateWords(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void migrateWords(SQLiteDatabase db) {
        JsonObject jsonObject = new JsonParser().parse(readJson()).getAsJsonObject();
        JsonArray en = jsonObject.getAsJsonArray("en");
        JsonArray ru = jsonObject.getAsJsonArray("ru");
        for(int i = 0; i < en.size(); i++) {
            ContentValues cv = new ContentValues();
            cv.put("text", en.get(i).getAsString());
            cv.put("lang", "en");
            db.insert(WORDS_TABLE, null, cv);
        }
        for(int i = 0; i < ru.size(); i++) {
            ContentValues cv = new ContentValues();
            cv.put("text", ru.get(i).getAsString());
            cv.put("lang", "ru");
            db.insert(WORDS_TABLE, null, cv);
        }
    }

    private String readJson() {
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();
        try {
            reader = new BufferedReader(
                    new InputStreamReader(mContext.getAssets().open("words.json"), "UTF-8"));

            String mLine;
            while ((mLine = reader.readLine()) != null) {
                result.append(mLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result.toString();
    }

    @Override
    public List<Word> getWords() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(WORDS_TABLE, new String[] {"text", "lang", "rating"}, null, null, null, null, null);
        List<Word> words = new ArrayList<>();
        if(cursor.moveToFirst()) {
            int textColIndex = cursor.getColumnIndex("text");
            int langColIndex = cursor.getColumnIndex("lang");
            int ratingColIndex = cursor.getColumnIndex("rating");
            do {
                words.add(new Word(cursor.getString(textColIndex), cursor.getInt(ratingColIndex),
                        cursor.getString(langColIndex)));
            } while(cursor.moveToNext());
        }
        db.close();
        return words;
    }

    @Override
    public void persistWord(Word word) {

    }

    @Override
    public void persistWords(List<Word> words) {

    }
}
