package ltd.nanoda.model;

public class File {
    public String getFileName() {
        return fileName;
    }

    public File(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    String fileName;
}
