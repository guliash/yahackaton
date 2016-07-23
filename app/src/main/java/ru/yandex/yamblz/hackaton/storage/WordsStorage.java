package ru.yandex.yamblz.hackaton.storage;

import java.util.List;

import ru.yandex.yamblz.hackaton.core.Word;

public interface WordsStorage {

    List<Word> getWords();

    List<Word> getWords(String lang);

    Word getRandomWord();

    Word getRandomWord(String lang);

    void persistWord(Word word);

    void persistWords(List<Word> words);
}
