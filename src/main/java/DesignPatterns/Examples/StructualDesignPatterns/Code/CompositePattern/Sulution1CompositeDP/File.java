package DesignPatterns.Examples.StructualDesignPatterns.Code.CompositePattern.Sulution1CompositeDP;

public class File implements FileSystem {
    String fileName;

    public File(String name){
        this.fileName = name;
    }

    public void printFileName(){
        System.out.println("file name " + fileName);
    }
}
