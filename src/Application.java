import java.io.File;
import java.util.*;

public class Application {
    private Map<Integer, String> documentsAndIds = new HashMap<>();
    private IndexFiller indexFiller = new IndexFiller();
    private FilesReader filesReader = new FilesReader();

    public void createInvertedIndex(int threadsQuantity) {
        List<File> files = filesReader.getFilesFromDirectory();
        IndexThread threads[] = new IndexThread[threadsQuantity];
        int size = files.size();
        double s = (double) size / (double) threadsQuantity;
        int k = size - (int) ((double) (threadsQuantity - 1) * s);
        int j = 0;
        for (int i = 0; i < threadsQuantity; i++) {
            threads[i] = new IndexThread(indexFiller, j, (int) (i * s + k), files, documentsAndIds);
            j = (int) ((double) i * s + k);
        }
        for (IndexThread thread : threads) {
            thread.start();
        }
        for (IndexThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}