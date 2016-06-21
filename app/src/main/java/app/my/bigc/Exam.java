package app.my.bigc;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Chinni on 10-06-2016.
 */
public class Exam implements java.io.Serializable{
    String id,title,total,correctt,wrong,skiped,status,started,ended;
    JSONArray jsonArray;
    ArrayList<Question> questions;
    JSONObject jsonObject;
    Context context;
    String append ;
    Exam(JSONObject jsonObject,Context context){
        this.jsonObject = jsonObject;
        this.context=context;
        append = Settings.get_append(context);
        questions=new ArrayList<>();
        try {
            id=jsonObject.getString("exam_id");
            title=jsonObject.getString("title");
            started = jsonObject.getString("started");
            ended = jsonObject.getString("ended");
            status = jsonObject.getString("status");
            jsonArray=jsonObject.getJSONArray("questions");

            for (int i=0;i<jsonArray.length();i++){
                Question question=new Question(jsonArray.getJSONObject(i));
                questions.add(question);
            }
            total=String.valueOf(questions.size());
            correctt =String.valueOf(jsonObject.getInt("correct_cnt"));
            wrong = String.valueOf(jsonObject.getInt("wrong_cnt"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public  class Question implements java.io.Serializable{
        String id,que,ans1,ans2,ans3,ans4,correct,result;
        Question(JSONObject json_question){
            try {
                id=json_question.getString("question_id");
                que=json_question.getString("question"+append);
                ans1=json_question.getString("answer1"+append);
                ans2=json_question.getString("answer2"+append);
                ans3=json_question.getString("answer3"+append);
                ans4=json_question.getString("answer4"+append);
                correct=json_question.getString("correct");
                result =json_question.getString("result");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
