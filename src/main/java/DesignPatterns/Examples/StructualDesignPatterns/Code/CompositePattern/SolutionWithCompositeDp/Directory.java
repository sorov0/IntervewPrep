package DesignPatterns.Examples.StructualDesignPatterns.Code.CompositePattern.SolutionWithCompositeDp;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystem{

    List<FileSystem> fileSystemList = new ArrayList<>();

    void add(FileSystem fs){
        fileSystemList.add(fs);
    }

    @Override
    public void printFileName() {

        for (FileSystem fs : fileSystemList){
            fs.printFileName();
        }

    }
}
