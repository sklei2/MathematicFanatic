package swen_anigans.mathematicfanatic;

import java.util.HashMap;

/**
 * Created by Sean on 11/14/2016.
 *
 * Contains images and item text that the
 * help class would need to display
 */

public class HelpImageSupplier implements IHelpSupplier
{
    private static HashMap<Integer, HashMap<Integer, Integer>> images;
    private static HashMap<Integer, String> itemText;

    public HelpImageSupplier()
    {
        initImages();
        initText();
    }

    @Override
    public Integer getImageResource(int a, int b)
    {
        if(images.containsKey(b))
        {
            return images.get(b).get(a);
        }
        else
        {
            return R.drawable.image_not_found;
        }
    }

    @Override
    public String getItemText(int a) {
        return itemText.get(a);
    }

    private void initImages()
    {
        images = new HashMap<Integer, HashMap<Integer, Integer>>();

        // Init the hashmaps that will contain the images
        HashMap<Integer, Integer> threes = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> eights = new HashMap<Integer, Integer>();

        // Init the hashmaps with image resources
        threes.put(1,R.drawable.three_01);
        threes.put(2,R.drawable.three_02);
        threes.put(3,R.drawable.three_03);
        threes.put(4,R.drawable.three_04);
        threes.put(5,R.drawable.three_05);
        threes.put(6,R.drawable.three_06);
        threes.put(7,R.drawable.three_07);
        threes.put(8,R.drawable.three_08);
        threes.put(9,R.drawable.three_09);
        threes.put(10,R.drawable.three_10);
        threes.put(11,R.drawable.three_11);
        threes.put(12,R.drawable.three_12);

        eights.put(1,R.drawable.eight_01);
        eights.put(2,R.drawable.eight_02);
        eights.put(3,R.drawable.eight_03);
        eights.put(4,R.drawable.eight_04);
        eights.put(5,R.drawable.eight_05);
        eights.put(6,R.drawable.eight_06);
        eights.put(7,R.drawable.eight_07);
        eights.put(8,R.drawable.eight_08);
        eights.put(9,R.drawable.eight_09);
        eights.put(10,R.drawable.eight_10);
        eights.put(11,R.drawable.eight_11);
        eights.put(12,R.drawable.eight_12);

        // add the initialized sub hashes into
        // the larger hashmaps
        images.put(3, threes);
        images.put(8, eights);
    }

    private void initText()
    {
        itemText = new HashMap<Integer, String>();

        // add the text to the hashmap.
        itemText.put(3, "balloons");
        itemText.put(8, "grapes");
    }
}
