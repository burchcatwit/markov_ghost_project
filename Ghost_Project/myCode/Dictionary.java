package myCode;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Dictionary extends professorCode.AbstractDictionary {

    Set<String> set = new HashSet<String>();

    public Dictionary(String path, myCode.FileManager fileManager) throws IOException {
        super(path, fileManager);
        set = getAllWords();
    }

    @Override
    public int countWordsThatStartWith(String prefix, int size, boolean ignoreCase) throws IllegalArgumentException {
        return getWordsThatStartWith(prefix, size, ignoreCase).size();
    }

    @Override
    public boolean containsWordsThatStartWith(String prefix, int size, boolean ignoreCase) throws IllegalArgumentException {
        return !getWordsThatStartWith(prefix, size, ignoreCase).isEmpty();
    }

    @Override
    public Set<String> getWordsThatStartWith(String prefix, int size, boolean ignoreCase) throws IllegalArgumentException {
        Set<String> mySet = new HashSet<String>();
        for (String st : set){
            if(ignoreCase) {
                if(st.toUpperCase().startsWith(prefix.toUpperCase()) && st.length() >= size)
                    mySet.add(st);
            } else{
                if(st.startsWith(prefix) && st.length() >= size)
                    mySet.add(st);
            }
        }
        return mySet;
    }
}