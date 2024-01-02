import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class IndexFiller {
    private InvertedIndex invertedIndex;

    public void setInvertedIndex(InvertedIndex invertedIndex) {
        this.invertedIndex = invertedIndex;
    }

    public InvertedIndex getInvertedIndex() {
        return invertedIndex;
    }

    public void fillInvertedIndex(File file, Integer documentId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                String[] lineWords = line.split("[^a-zA-Z0-9]+");
                for (String word : lineWords) {
                    invertedIndex.addWordAndDocumentId(word.toLowerCase(), documentId);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
