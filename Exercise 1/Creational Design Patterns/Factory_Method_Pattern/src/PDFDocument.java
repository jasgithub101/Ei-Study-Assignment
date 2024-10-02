public class PDFDocument implements Document {
    private String filePath;

    public PDFDocument(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void open() {
        System.out.println("Opening PDF document: " + filePath);
        // Add code to open the PDF document
    }
}