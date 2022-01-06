package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ca.jrvs.apps.practice.RegexExcImp;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.log4j.BasicConfigurator;


class JavaGrepLambda extends JavaGrepImp {
    private final Logger logger = LoggerFactory.getLogger(RegexExcImp.class);
    public static void main(String[] args) {
        BasicConfigurator.configure();
        if (args.length != 3) {
            return;
        }

        JavaGrepLambda javaGrep = new JavaGrepLambda();
        javaGrep.setRegex(args[2]);
        javaGrep.setOutFile(args[0]);
        javaGrep.setRootPath(args[1]);

        try {
            javaGrep.process();
        } catch (Exception f) {
            javaGrep.logger.error(f.getMessage());
            throw new RuntimeException("METHOD FAILED", f);
        }
    }
    @Override
    public List<File> listFiles(String rootDir) {
        Path path = Paths.get(rootDir);
        try {
            return Files.walk(path)
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("METHOD FAILED: ", e);
        }
    }

    @Override
    public List<String> readLines(File inputFile) {
        List<String> result;
        try (Stream<String> lines = Files.lines(Paths.get(String.valueOf(inputFile)))) {
            result = lines.collect(Collectors.toList());
        } catch (Exception e) {
            result = null;
        }
        return result;
    }
}

