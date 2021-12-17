package ca.jrvs.apps.grep;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.log4j.BasicConfigurator;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args) throws Exception {
    if (args.length != 3) {
      throw new IllegalArgumentException("Wrong Syntax! USE: JavaGrep regex rootPath outFile");
    }

    // Using default Logger configuration
    BasicConfigurator.configure();
    JavaGrepImp GrepImp = new JavaGrepImp();

    // Store the arguments using setters
    GrepImp.setRegex(args[0]);
    GrepImp.setRootPath(args[1]);
    GrepImp.setOutFile(args[2]);

    GrepImp.process();
  }

  @Override
  public void process() throws IOException { // Top level workflow
    List<String> MatchedLines = new ArrayList<String>();
    for (File file : listFiles(rootPath)) {
      for (String temp : readLines(file))
        if (containsPattern(temp)) {
          MatchedLines.add(temp);
        }
      if (MatchedLines.size() != 0) {
        try {
          this.writeToFile(MatchedLines);
        } catch (IOException e) {
          throw new IOException("Writing was not Executed");
        }
      } else {
        logger.warn("Try again with another String");
      }
    }
  }

  @Override
  public List<File> listFiles(String rootDir) {
    try {
      File f = new File(rootDir);
      ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
      return files;
    } catch (IllegalArgumentException ex) {
      System.out.println("Argument problem"); // Exception
      return null;
    }
  }

  @Override
  public List<String> readLines(File inputFile) {
    List<String> Lines = new ArrayList<String>();
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(inputFile));
      String line = reader.readLine();
      while (line != null) {
        Lines.add(line);
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace(); //
    }

    return Lines;
  }

  @Override
  public boolean containsPattern(String line) {
    return line.matches(this.regex);
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    File fileOut = new File(this.outFile);
    FileOutputStream fos = new FileOutputStream(fileOut);

    BufferedWriter BuffWrite = new BufferedWriter(new OutputStreamWriter(fos));
    for (String temp: lines) {
          try {
            BuffWrite.write(temp);
            BuffWrite.newLine();
          }catch (IOException e) {
            e.printStackTrace();
          }
    } BuffWrite.close();
  }

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }
}

