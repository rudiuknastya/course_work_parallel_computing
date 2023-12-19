import java.io.File;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        FilesReader filesReader = new FilesReader();
        List<File> files = filesReader.getFilesFromDirectory();
        System.out.println(files.size());
        IndexFiller indexFiller = new IndexFiller();
        for(File file: files){
            indexFiller.fillInvertedIndex(file);
        }
    }
}