import java.io.File;
import java.util.List;
import java.util.Map;

public class IndexThread extends Thread{
    private IndexFiller indexFiller;
    private int start;
    private int end;
    private List<File> files;
    private Map<Integer,String> documentsAndIds;
    public IndexThread(IndexFiller indexFiller, int start, int end, List<File> files, Map<Integer,String> documentsAndIds){
        this.indexFiller = indexFiller;
        this.start = start;
        this.end = end;
        this.files = files;
        this.documentsAndIds = documentsAndIds;
    }
    @Override
    public void run() {
        for(int i = start; i < end; i++){
            documentsAndIds.put(i,files.get(i).getPath());
            indexFiller.fillInvertedIndex(files.get(i), i);
        }
    }
}
