package ca.jrvs.apps.grep;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);
  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args) {
    if (args.length != 3) {

      throw new IllegalArgumentException("Usage: JavaGrep regex rootPath outFile");
    }
    BasicConfigurator.configure();

    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);
    try {
      javaGrepImp.process();
    } catch (Exception ex) {

      javaGrepImp.logger.error("Main process Failed", ex);
    }
  }

  @Override
  public void process() throws IOException {
    List<String> lines = new ArrayList<>();
    for (File newFiles : listFiles(getRootPath())) {
      for (String line : readLines(newFiles)) {
        if (containsPattern(line)) {
          lines.add(line);
        }
      }
    }
    writeToFile(lines);
  }

  @Override
  public List<File> listFiles(String rootDir) {
    File dirPath = new File(rootDir);
    File[] fileList = dirPath.listFiles();
    if (fileList == null) {
      try {
        throw new FileNotFoundException("ERROR: Root Path is Empty.");
      } catch (FileNotFoundException e) {
        logger.error("FileNotFoundException", e);
      }
    }
    List<File> result = new ArrayList<>();
    for (File file : fileList)
      if (file.isFile()) {
        result.add(file);
      } else {
        String subDirName = file.getAbsolutePath();
        /*collect all files in the directory. */
        result.addAll(listFiles(subDirName));
      }

    return result;
  }

  public List<String> readLines(File inputFile) throws FileNotFoundException {
    List<String> lineList = new ArrayList<>();
    try {

      BufferedReader buffReader =
          new BufferedReader(
              new FileReader(inputFile)); // creates a buffering character input stream
      String line;
      while ((line = buffReader.readLine()) != null) {
        lineList.add(line);
      }
      buffReader.close();

    } catch (IOException e) {

      logger.error("InputOutput Exception", e);
    }
    return lineList;
  }

  @Override
  public boolean containsPattern(String line) {
    return line.matches(getRegex());
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(getOutFile()));
    for (String eachLine : lines) {
      writer.write(eachLine + System.lineSeparator());
    }
    writer.close();
  }

  public String getRegex() {
    return regex;
  }

  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  public String getRootPath() {
    return rootPath;
  }

  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }
}
