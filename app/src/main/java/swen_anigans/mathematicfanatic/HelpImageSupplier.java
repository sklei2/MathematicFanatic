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
    public String getItemText(int b) {
        return itemText.get(b);
    }

    private void initImages()
    {
        images = new HashMap<Integer, HashMap<Integer, Integer>>();

        // Init the hashmaps that will contain the images
        HashMap<Integer, Integer> ones = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> twos = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> threes = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> fours = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> fives = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> sixes = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> sevens = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> eights = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> nines = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> tens = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> elevens = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> twelves = new HashMap<Integer, Integer>();

        // Init the hashmaps with image resources
        ones.put(1,R.drawable.one_01);
        ones.put(2,R.drawable.one_02);
        ones.put(3,R.drawable.one_03);
        ones.put(4,R.drawable.one_04);
        ones.put(5,R.drawable.one_05);
        ones.put(6,R.drawable.one_06);
        ones.put(7,R.drawable.one_07);
        ones.put(8,R.drawable.one_08);
        ones.put(9,R.drawable.one_09);
        ones.put(10,R.drawable.one_10);
        ones.put(11,R.drawable.one_11);
        ones.put(12,R.drawable.one_12);

        twos.put(1,R.drawable.two_01);
        twos.put(2,R.drawable.two_02);
        twos.put(3,R.drawable.two_03);
        twos.put(4,R.drawable.two_04);
        twos.put(5,R.drawable.two_05);
        twos.put(6,R.drawable.two_06);
        twos.put(7,R.drawable.two_07);
        twos.put(8,R.drawable.two_08);
        twos.put(9,R.drawable.two_09);
        twos.put(10,R.drawable.two_10);
        twos.put(11,R.drawable.two_11);
        twos.put(12,R.drawable.two_12);

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

        fours.put(1,R.drawable.four_01);
        fours.put(2,R.drawable.four_02);
        fours.put(3,R.drawable.four_03);
        fours.put(4,R.drawable.four_04);
        fours.put(5,R.drawable.four_05);
        fours.put(6,R.drawable.four_06);
        fours.put(7,R.drawable.four_07);
        fours.put(8,R.drawable.four_08);
        fours.put(9,R.drawable.four_09);
        fours.put(10,R.drawable.four_10);
        fours.put(11,R.drawable.four_11);
        fours.put(12,R.drawable.four_12);

        fives.put(1,R.drawable.five_01);
        fives.put(2,R.drawable.five_02);
        fives.put(3,R.drawable.five_03);
        fives.put(4,R.drawable.five_04);
        fives.put(5,R.drawable.five_05);
        fives.put(6,R.drawable.five_06);
        fives.put(7,R.drawable.five_07);
        fives.put(8,R.drawable.five_08);
        fives.put(9,R.drawable.five_09);
        fives.put(10,R.drawable.five_10);
        fives.put(11,R.drawable.five_11);
        fives.put(12,R.drawable.five_12);

        sixes.put(1,R.drawable.six_01);
        sixes.put(2,R.drawable.six_02);
        sixes.put(3,R.drawable.six_03);
        sixes.put(4,R.drawable.six_04);
        sixes.put(5,R.drawable.six_05);
        sixes.put(6,R.drawable.six_06);
        sixes.put(7,R.drawable.six_07);
        sixes.put(8,R.drawable.six_08);
        sixes.put(9,R.drawable.six_09);
        sixes.put(10,R.drawable.six_10);
        sixes.put(11,R.drawable.six_11);
        sixes.put(12,R.drawable.six_12);

        sevens.put(1,R.drawable.seven_01);
        sevens.put(2,R.drawable.seven_02);
        sevens.put(3,R.drawable.seven_03);
        sevens.put(4,R.drawable.seven_04);
        sevens.put(5,R.drawable.seven_05);
        sevens.put(6,R.drawable.seven_06);
        sevens.put(7,R.drawable.seven_07);
        sevens.put(8,R.drawable.seven_08);
        sevens.put(9,R.drawable.seven_09);
        sevens.put(10,R.drawable.seven_10);
        sevens.put(11,R.drawable.seven_11);
        sevens.put(12,R.drawable.seven_12);

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

        nines.put(1,R.drawable.nine_01);
        nines.put(2,R.drawable.nine_02);
        nines.put(3,R.drawable.nine_03);
        nines.put(4,R.drawable.nine_04);
        nines.put(5,R.drawable.nine_05);
        nines.put(6,R.drawable.nine_06);
        nines.put(7,R.drawable.nine_07);
        nines.put(8,R.drawable.nine_08);
        nines.put(9,R.drawable.nine_09);
        nines.put(11,R.drawable.nine_10);
        nines.put(11,R.drawable.nine_11);
        nines.put(12,R.drawable.nine_12);

        tens.put(1,R.drawable.ten_01);
        tens.put(2,R.drawable.ten_02);
        tens.put(3,R.drawable.ten_03);
        tens.put(4,R.drawable.ten_04);
        tens.put(5,R.drawable.ten_05);
        tens.put(6,R.drawable.ten_06);
        tens.put(7,R.drawable.ten_07);
        tens.put(8,R.drawable.ten_08);
        tens.put(9,R.drawable.ten_09);
        tens.put(10,R.drawable.ten_10);
        tens.put(11,R.drawable.ten_11);
        tens.put(12,R.drawable.ten_12);

        elevens.put(1,R.drawable.eleven_01);
        elevens.put(2,R.drawable.eleven_02);
        elevens.put(3,R.drawable.eleven_03);
        elevens.put(4,R.drawable.eleven_04);
        elevens.put(5,R.drawable.eleven_05);
        elevens.put(6,R.drawable.eleven_06);
        elevens.put(7,R.drawable.eleven_07);
        elevens.put(8,R.drawable.eleven_08);
        elevens.put(9,R.drawable.eleven_09);
        elevens.put(10,R.drawable.eleven_10);
        elevens.put(11,R.drawable.eleven_11);
        elevens.put(12,R.drawable.eleven_12);

        twelves.put(1,R.drawable.twelve_01);
        twelves.put(2,R.drawable.twelve_02);
        twelves.put(3,R.drawable.twelve_03);
        twelves.put(4,R.drawable.twelve_04);
        twelves.put(5,R.drawable.twelve_05);
        twelves.put(6,R.drawable.twelve_06);
        twelves.put(7,R.drawable.twelve_07);
        twelves.put(8,R.drawable.twelve_08);
        twelves.put(9,R.drawable.twelve_09);
        twelves.put(10,R.drawable.twelve_10);
        twelves.put(11,R.drawable.twelve_11);
        twelves.put(12,R.drawable.twelve_12);

        // add the initialized sub hashes into
        // the larger hashmaps
        images.put(1, ones);
        images.put(2, twos);
        images.put(3, threes);
        images.put(4, fours);
        images.put(5, fives);
        images.put(6, sixes);
        images.put(7, sevens);
        images.put(8, eights);
        images.put(9, nines);
        images.put(10, tens);
        images.put(11, elevens);
        images.put(12, twelves);
    }

    private void initText()
    {
        itemText = new HashMap<Integer, String>();

        // add the text to the hashmap.
        itemText.put(1, "If there is 1 fish in a bowl, and there are _ bowls, how many fish are there?");
        itemText.put(2, "If there are 2 wheels on a bike, and there are _ bikes, how many wheels are there?");
        itemText.put(3, "If there are 3 balloons in a bunch, and there are _ bunches, how many balloons are there?");
        itemText.put(4, "If there are 4 pieces in a puzzle, and there are _ puzzles, how many pieces are there?");
        itemText.put(5, "If there are 5 fingers on a hand, and there are _ hands, how many fingers are there?");
        itemText.put(6, "If there are 6 whiskers on a cat, and there are _ cats, how many whiskers are there?");
        itemText.put(7, "If there are 7 apples in a basket, and there are _ baskets, how many apples are there?");
        itemText.put(8, "If there are 8 grapes in a bunch, and there are _ bunches, how many grapes are there?");
        itemText.put(9, "If there are 9 dots on a ladybug, and there are _ ladybugs, how many dots are there?");
        itemText.put(10, "If there are 10 rays on a sun, and there are _ suns, how many rays are there?");
        itemText.put(11, "If there are 11 hearts in a jar, and there are _ jars, how many hearts are there?");
        itemText.put(12, "If there are 12 seeds on a watermelon, and there are _ watermelons, how many seeds are there?");
    }
}
