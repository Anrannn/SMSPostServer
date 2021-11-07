package ltd.nanoda.Util;

import ltd.nanoda.model.FileCollection;
import ltd.nanoda.model.Folder;
import ltd.nanoda.model.FolderCollection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileEachUtil {
    public  Collection  each(String path){
        File file = new File(path);
        File[] files =  file.listFiles();
        if (files.length<=0) {
            return null;
        }

        List<Folder> folderList = new ArrayList<>();
        List<ltd.nanoda.model.File> fileList = new ArrayList<>();


        //遍历文件夹
        for (File f:files){
            //是文件夹
            if (f.isDirectory()){
                //格式化映射路径
                String[] strs = f.getPath().split("/");
                StringBuffer mapperPath = new StringBuffer();
                for (int i = 3;i<strs.length;i++){
                    mapperPath.append("/"+strs[i]);
                }
                System.out.println("mapper"+mapperPath);

                Folder folder = new Folder(f.getName(),mapperPath.toString());
                folderList.add(folder);
                //是文件
            }else {
                String[] strs = f.getPath().split("/");
                StringBuffer mapperPath = new StringBuffer();
                for (int i = 3;i<strs.length;i++){
                    mapperPath.append("/"+strs[i]);

                }
                ltd.nanoda.model.File file1 = new ltd.nanoda.model.File(mapperPath.toString(),f.getName());
                fileList.add(file1);
            }
        }
        //将列表对象封装
        FileCollection fileCollection =new FileCollection(fileList,"file");
        FolderCollection folderCollection = new FolderCollection(folderList,"folder");

        return new Collection(fileCollection,folderCollection,"OK");
    }

    /**
     * 返回两个参数
     */
    public  class Collection{

        FileCollection fileCollection;
        FolderCollection folderCollection;
        String type;

        public FileCollection getFileCollection() {
            return fileCollection;
        }

        public String getType() {
            return type;
        }

        public FolderCollection getFolderCollection() {
            return folderCollection;
        }

        public Collection(FileCollection fileCollection, FolderCollection folderCollection,String type) {
            this.fileCollection = fileCollection;
            this.folderCollection = folderCollection;
            this.type=type;
        }
    }

}
