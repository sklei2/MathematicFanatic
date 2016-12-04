package swen_anigans.mathematicfanatic;

/**
 * Created by Mitchell on 11/9/2016.
 */

class student
{
    public String name;
    public int rangeMax;
    public int rangeMin;
    public Interest[] interests = new Interest[3];
    public LearningType learningType;
    public boolean canRecess;

    public student(String name, int min, int max, Interest i1, Interest i2, Interest i3, LearningType type){
        this.rangeMax = max;
        this.rangeMin = min;
        this.name = name;
        interests = new Interest[]{i1,i2,i3};
        this.learningType = type;
        canRecess = false;
    }
}
