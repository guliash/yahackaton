package ru.yandex.yamblz.hackaton.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Helper {

    public static String getTranslation(DicResult result) {
        return result.getArticles().get(0).getTranslations().get(0).getText();
    }

    public static char[] shuffle(char[] array) {
        Random random = new Random();
        for(int i = 0; i < array.length - 1; i++) {
            int next = i + random.nextInt(array.length - i - 1);
            swap(array, i, next);
        }
        return array;
    }

    private static void swap(char[] arr, int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
