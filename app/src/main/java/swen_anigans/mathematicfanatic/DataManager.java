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
    public static student curStudent;
    protected HashMap<Interest,String> Interests;

    private DataManager(){
        students = new ArrayList<student>();
        Interests = new HashMap<Interest,String>();
    }

    public static DataManager getInstance(){
        if(instance.equals(null)){
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

    public int updateStudent(student st){
        for(student student : students){
            if(student.name.equals(st.name)) {
                students.remove(student);
                students.add(st);
                return 0;
            }
        }
        return -1;
    }

    public student getStudent(String name){
        for(student st : students){
            if(st.name.equals(name)) {
                return st;
            }
        }

        return null;
    }
}
