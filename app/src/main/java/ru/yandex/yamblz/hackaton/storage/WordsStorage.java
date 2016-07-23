package ru.yandex.yamblz.hackaton.storage;

import java.util.List;

import ru.yandex.yamblz.hackaton.core.Word;

public interface WordsStorage {

    List<Word> getWords();

    void persistWord(Word word);

    void persistWords(List<Word> words);
}
