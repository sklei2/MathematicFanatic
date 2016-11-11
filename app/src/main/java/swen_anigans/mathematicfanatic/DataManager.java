package swen_anigans.mathematicfanatic;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by Mitchell on 11/9/2016.
 */

public class DataManager
{
    private static DataManager instance;

    protected ArrayList<student> students;
    protected student curStudent;
    protected HashMap<Interest,String> Interests;

    private DataManager(){
        students = new ArrayList<student>();
        Interests = new HashMap<Interest,String>();
    }

    public static DataManager getInstance(){
        if(instance == (null)){
            instance = new DataManager();
        }
        return instance;
    }

    public int setCurStudent(String student){
        for(student st : students){
            if(st.name.equals(student)) {
                curStudent = st;
                return 0;
            }
        }

        return -1;
    }

    public int addStudent(student st){
        for(student student : students){
            if(student.name.equals(st.name)) {
                return -1;
            }
        }
        students.add(st);
        return 0;
    }

    public int removeStudent(String name){
        for(student st : students){
            if(st.name.equals(name)) {
                students.remove(st);
                return 0;
            }
        }

        return -1;
    }


    public student getStudent(String name){
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).name.equals(name)) {
                return students.get(i);
            }
        }

        return null;
    }
}
