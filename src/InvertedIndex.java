import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InvertedIndex {
    private Map<String, Set<String>> map = new HashMap<>();
    public void addWordAndFile(String word, String filePath){
        if(map.containsKey(word)){
            map.get(word).add(filePath);
        } else {
            Set<String> files = new HashSet<>();
            files.add(filePath);
            map.put(word,files);
        }
    }
    public Set<String> getFilesByWord(String word){
        return map.get(word);
    }
}
