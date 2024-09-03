package DesignPatterns.Examples.StructualDesignPatterns.Code.CompositePattern.SolutionWithCompositeDp;

public class Client {

    public static void main(String[] args) {

        Directory parentDir = new Directory();
        FileSystem fileSystem = new File();

        parentDir.add(fileSystem);

        Directory childDir = new Directory();
        FileSystem fileSystem1 = new File();
        childDir.add(fileSystem1);

        parentDir.add(childDir);

        parentDir.printFileName();


    }
}
