package ltd.nanoda.model;

import java.util.ArrayList;
import java.util.List;

public class FolderCollection {
    public FolderCollection(List<Folder> folders, String type) {
        this.folders = folders;
        this.type = type;
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Folder> folders;
    public String type;

}
