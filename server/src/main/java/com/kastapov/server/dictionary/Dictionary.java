package com.kastapov.server.dictionary;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * User: kastapov
 * Date: 20.08.2015
 */
public enum Dictionary {

    INSTANCE;

    private ConcurrentMap<String, Set<String>> dictionary = new ConcurrentHashMap<>();

    public void add(String word, Set<String> definitions) {
        Set<String> dictDef = dictionary.get(word);
        if (dictDef == null) {
            final Set<String> newDefinitions = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
            newDefinitions.addAll(definitions);
            dictDef = dictionary.putIfAbsent(word, newDefinitions);
            if (dictDef != null) {
                dictDef.addAll(definitions);
            }
        } else {
            dictDef.addAll(definitions);
        }
    }

    public Set<String> get(String word) {
        return dictionary.get(word);
    }

    public boolean delete(String word, Set<String> definitions) {
        Set<String> dictDef = dictionary.get(word);
        if (dictDef != null) {
            dictDef.removeAll(definitions);
            if (dictDef.isEmpty() || definitions.isEmpty()) {
                dictDef = dictionary.remove(word);

                if (!dictDef.isEmpty()) {
                    add(word, dictDef);
                }
            }
            return true;
        }
        return false;
    }
}
