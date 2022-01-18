package ca.jrvs.apps.grep;

import org.apache.log4j.BasicConfigurator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class JavaGrepLambda extends JavaGrepImp {

  public static void main(String[] args) {
    BasicConfigurator.configure();
    if (args.length != 3) {
      throw new IllegalArgumentException("Usage: JavaGrep regex rootPath outFile");
    }

    JavaGrepLambda javaGrep = new JavaGrepLambda();
    javaGrep.setRegex(args[0]);
    javaGrep.setOutFile(args[2]);
    javaGrep.setRootPath(args[1]);

    try {
      javaGrep.process();
    } catch (Exception f) {
      throw new RuntimeException("FAILED", f);
    }
  }

  @Override
  public List<File> listFiles(String rootDir) {
    try {
      Stream<File> filenames =
          Files.walk(Paths.get(rootDir))
                  .filter(Files::isRegularFile)
                  .map(Path::toFile);

      return filenames.collect(Collectors.toList());
    } catch (IOException e) {
      throw new IllegalArgumentException("Try a Different Directory");
    }
  }

  @Override
  public List<String> readLines(File inputFile) throws FileNotFoundException {
    List<String> list = new ArrayList<>();

    try {
      Stream<String> stream = Files.lines(inputFile.toPath());
      {
        list = stream.collect(Collectors.toList());
      }
    } catch (FileNotFoundException e) {
     throw new FileNotFoundException("File is missing");
    } catch (IOException e) {
      logger.error("IOException", e);
    }
    return list;
  }
}
