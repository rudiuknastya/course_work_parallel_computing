import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class InvertedIndex {
    private final Map<String, Set<Integer>> map = new ConcurrentHashMap<>();
    public void addWordAndDocumentId(String word, Integer documentId){
        if(map.containsKey(word)){
            map.get(word).add(documentId);
        } else {
            Set<Integer> documents = new HashSet<>();
            documents.add(documentId);
            map.put(word,documents);
        }
    }
    public Set<Integer> getDocumentIdsByWord(String word){
        return map.get(word);
    }
}
