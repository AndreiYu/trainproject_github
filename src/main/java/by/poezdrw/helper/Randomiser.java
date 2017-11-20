package by.poezdrw.helper;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;


public class Randomiser {

    public static WebElement getRandomWebElementValue(List<WebElement> list) {
        Random r = new Random();
        int randomValue;
        if (list.size() == 1) { //if only 1 element is available
            randomValue = 0;
        }
        else {
            randomValue = r.nextInt(list.size()); //Getting a random value that is between 0 and (list's size)-1
        }
        return list.get(randomValue);
    }

}
