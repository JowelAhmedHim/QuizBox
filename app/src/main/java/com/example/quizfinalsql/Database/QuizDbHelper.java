package com.example.quizfinalsql.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quizfinalsql.Model.QuestionModel;
import com.example.quizfinalsql.Database.QuizContact.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "FinalQuiz";
    public static final int DATABASE_VERSION = 2 ;

    private  SQLiteDatabase db;

    final String CREATE_TABLE = " CREATE TABLE " +
            QuestionTable.TABLE_NAME + " ( " +
            QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QuestionTable.COLUMN_QUESTION + " TEXT, " +
            QuestionTable.COLUMN_OPTION1 +" TEXT, " +
            QuestionTable.COLUMN_OPTION2 + " TEXT, "+
            QuestionTable.COLUMN_OPTION3 + " TEXT, "+
            QuestionTable.COLUMN_OPTION4 + " TEXT, "+
            QuestionTable.COLUMN_ANSWER_NR + " INTEGER, " +
            QuestionTable.COLUMN_CATEGORY + " TEXT " +
            " ) ";

    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(CREATE_TABLE);

        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
           onCreate(db);
    }

    private  void addQuestions(QuestionModel questionModel){
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION,questionModel.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1,questionModel.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2,questionModel.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3,questionModel.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4,questionModel.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER_NR,questionModel.getAnswerNr());
        cv.put(QuestionTable.COLUMN_CATEGORY,questionModel.getCategory());

        db.insert(QuestionTable.TABLE_NAME,null,cv);
    }

    private void  fillQuestionsTable(){
        QuestionModel q1 = new QuestionModel("বীরবল কার ছদ্দ নাম ?","রবীন্দ্রনাথ ঠাকুর","প্রমথ চৌধুরী","নজরূল ইসলাম","মনীর চৌধুরী",2,QuestionModel.CATEGORY_BANGLA);
        addQuestions(q1);
        QuestionModel q2 = new QuestionModel("সনেট কবিতার প্রবর্তক কে ?","শামসূর রাহমান","রবীন্দ্রনাথ ঠাকুর","মাইকেল মধুসূধন","হমায়ন কবীর",3,QuestionModel.CATEGORY_BANGLA);
        addQuestions(q2);
        QuestionModel q3 = new QuestionModel("শেষের কবিত্তা কি ?"," উপন্যাস","কবিতা","প্রবন্ধ","নাটক",1,QuestionModel.CATEGORY_BANGLA);
        addQuestions(q3);
        QuestionModel q4 = new QuestionModel("কাব্য সুধাকর কার উপাধী ?","শামসূর রাহমান","নজরূল ইসলাম"," প্রমথ চৌধুরী","গোলাম মুস্তাফা",4,QuestionModel.CATEGORY_BANGLA);
        addQuestions(q4);
        QuestionModel q5 = new QuestionModel("He got too tired______over work "," of","on","because off","for",1,QuestionModel.CATEGORY_ENGLISH);
        addQuestions(q5);
        QuestionModel q6 = new QuestionModel("He was seen _____ to school ","went","gone","go","going",4,QuestionModel.CATEGORY_ENGLISH);
        addQuestions(q6);
        QuestionModel q7 = new QuestionModel("Antonym of Rabid ?","Frantic","Sober","Chaos","Vulgar",2,QuestionModel.CATEGORY_ENGLISH);
        addQuestions(q7);
        QuestionModel q8 = new QuestionModel("Antonym of Ravenous ?","Greedy","Very Hungry","Assuaged"," None of these",3,QuestionModel.CATEGORY_ENGLISH);
        addQuestions(q8);
        QuestionModel q9 = new QuestionModel(" The energy of food is measured in:","Calories","Celsius","Kelvin","None of the above",1,QuestionModel.CATEGORY_G_SCIENCE);
        addQuestions(q9);
        QuestionModel q10 = new QuestionModel("Name an instrument which is used to measure the Density of milk?","Lactometer"," Hydrometer","Barometer","Hygrometer",1,QuestionModel.CATEGORY_G_SCIENCE);
        addQuestions(q10);
        QuestionModel q11 = new QuestionModel("Android is what ?","OS","Window","Anti virus","softwere",4,QuestionModel.CATEGORY_MATH);
        addQuestions(q9);
        QuestionModel q12 = new QuestionModel("Which of the following is also called translator?","Data representation"," MS-DOS"," Operating System","Language Processor",4,QuestionModel.CATEGORY_MATH);
        addQuestions(q10);
    }

    public ArrayList<QuestionModel> getAllQuestions(){

        ArrayList<QuestionModel> questionsList =  new ArrayList<>();

        db = getReadableDatabase();

        String projection[] = {
                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR
        };
        Cursor cursor = db.query(QuestionTable.TABLE_NAME,
                 projection,
                null,
                null,
                 null,
                null,
                null
        );

        if (cursor.moveToFirst()){
            do {
                QuestionModel questionModel = new QuestionModel();
                questionModel.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                questionModel.setOption1(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                questionModel.setOption2(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                questionModel.setOption3(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                questionModel.setOption4(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                questionModel.setAnswerNr(cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));

                questionsList.add(questionModel);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return  questionsList;
    }
    public ArrayList<QuestionModel> getAllQuestionsWithCategory(String category){

        ArrayList<QuestionModel> questionsList =  new ArrayList<>();

        db = getReadableDatabase();

        String projection[] = {
                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR,
                QuestionTable.COLUMN_CATEGORY
        };

        String selection = QuestionTable.COLUMN_CATEGORY + " = ? ";
        String[] selectionArgs = {category};
        Cursor cursor = db.query(QuestionTable.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()){
            do {
                QuestionModel questionModel = new QuestionModel();
                questionModel.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                questionModel.setOption1(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                questionModel.setOption2(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                questionModel.setOption3(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                questionModel.setOption4(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                questionModel.setAnswerNr(cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questionModel.setCategory(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_CATEGORY)));

                questionsList.add(questionModel);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return  questionsList;
    }
}
