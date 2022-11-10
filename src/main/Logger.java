package main;

import java.io.*;

public class Logger {

    public static int getFileSize(File f) throws IOException {
        var br = new BufferedReader(new FileReader(f));
        int count = 0;
        while(br.readLine() != null) {count = count + 1;}
        br.close();
        return count;
    }

    public static void writeNewLine(File f, String line) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(f, true));
            out.println(line);
            out.close();
        } catch(IOException e) {
            System.out.println("File cant be written");
        }
    }

    public static void createDirectory(String name) {
        File f = new File(name);
        f.mkdir();
        for(File file : f.listFiles()) {file.delete();}
    }
}
