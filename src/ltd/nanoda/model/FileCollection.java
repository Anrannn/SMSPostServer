package ltd.nanoda.model;

import java.util.ArrayList;
import java.util.List;

public class FileCollection {
    public List<File> files;
    public String type;

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FileCollection(List<File> files, String type) {
        this.files = files;
        this.type = type;
    }
}
