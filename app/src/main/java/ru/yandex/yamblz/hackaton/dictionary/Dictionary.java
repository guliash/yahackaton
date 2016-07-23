package ru.yandex.yamblz.hackaton.dictionary;

import android.support.annotation.Nullable;

import java.util.List;

public interface Dictionary {

    /**
     * Возвращает список направлений перевода, поддерживаемых сервисом.
     * @return направления перевода
     */
    List<String> getLangs();

    /**
     * Осуществляет поиск слова или фразы в словаре и возвращает автоматически сформированную словарную статью.
     * @param lang направление перевода (например, "en-ru")
     * @param text слово или фраза, которую ищем
     * @param ui язык интерфейса пользователя
     * @param flags опции поиска
     * @return статья
     */
    DicResult lookup(String lang, String text, @Nullable String ui, int flags);

}
