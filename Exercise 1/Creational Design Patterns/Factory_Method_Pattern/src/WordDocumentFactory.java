public class WordDocumentFactory implements DocumentFactory {
    @Override
    public Document createDocument(String filePath) {
        return new WordDocument(filePath);
    }
}