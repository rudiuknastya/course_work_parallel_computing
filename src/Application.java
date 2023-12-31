import java.io.File;
import java.util.*;

public class Application {
    private Map<Integer, String> documentsAndIds = new HashMap<>();
    private IndexFiller indexFiller = new IndexFiller();
    private FilesReader filesReader = new FilesReader();
    public void createInvertedIndex(int threadsQuantity) {
        List<File> files = filesReader.getFilesFromDirectory();
        indexFiller.setInvertedIndex(new InvertedIndex());
        IndexThread threads[] = new IndexThread[threadsQuantity];
        int size = files.size();
        double s = (double) size / (double) threadsQuantity;
        int k = size - (int) ((double) (threadsQuantity - 1) * s);
        int j = 0;
        for (int i = 0; i < threadsQuantity; i++) {
            threads[i] = new IndexThread(indexFiller, j, (int) (i * s + k), files, documentsAndIds);
            j = (int) ((double) i * s + k);
        }
        long start = System.nanoTime();
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
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("Thread numbers: "+ threadsQuantity +" Time = " + timeElapsed / 1000000);
    }
    private void printDocuments(String wordsToFind) {
        String[] words = wordsToFind.split(" ");
        Set<Integer> documentIds = indexFiller.getInvertedIndex().getDocumentIdsByWord(words[0].toLowerCase());
        if (words.length != 1) {
            for (int i = 1; i < words.length; i++) {
                Set<Integer> documentIdsByWord = indexFiller.getInvertedIndex().getDocumentIdsByWord(words[i].toLowerCase());
                documentIds.retainAll(documentIdsByWord);
            }
        }
        if (documentIds == null || documentIds.isEmpty()) {
            System.out.println("Документів не знайдено");
        } else {
            System.out.println("Знайдені документи: ");
            documentIds.forEach((documentId) -> System.out.println(documentsAndIds.get(documentId)));
        }
    }

    public void launch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть кількість потоків для заповнення інвертованого індексу: ");
        int threadsQuantity = scanner.nextInt();
        scanner.nextLine();
        createInvertedIndex(threadsQuantity);
        System.out.println("Введіть слово/слова для пошуку або слово 'Стоп' для зупинки: ");
        String wordsToFind = scanner.nextLine();
        while(!wordsToFind.equals("Стоп")) {
            printDocuments(wordsToFind);
            System.out.println("Введіть слово/слова для пошуку або слово 'Стоп' для зупинки: ");
            wordsToFind = scanner.nextLine();
        }
    }
}
