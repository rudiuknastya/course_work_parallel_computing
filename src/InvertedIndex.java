import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InvertedIndex {
    private Map<String, Set<Integer>> map = new HashMap<>();
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
