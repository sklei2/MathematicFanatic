package swen_anigans.mathematicfanatic;

/**
 * Created by Sean on 11/14/2016.
 *
 * This is going to be a common interface for classes that
 * will provide images and text for the help activity class.
 *
 */

public interface IHelpSupplier
{
    public Integer getImageResource(int a, int b);
    public String getItemText(int a);
}
