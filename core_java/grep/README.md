## Introduction
The GREP Java program is the java implementation of the much popular 'grep' UNIX command. The program scans through files that are in directory and looks for a Regex Pattern (Regular Expression). Once matched copies all those matching lines and saves it to text file that can be specified when calling the program.

- The Language used from this program is Java along with Java 8 features like Streams and Lambda
- The package and dependency management tool used is Maven
- A docker image is also built for the purpose of execution

## Quick Start
The Application can be run in Docker or as jar with either of these commands the arguments must follow the order stated below:-
##### Docker
```docker pull hari77/grep```

```docker run --rm -v `pwd`/data:/data -v `pwd`/out:/out hari77/grep [Regex Pattern] [Directory] [outFile]```
##### JAR file
``` mvn clean package ```

``` java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepLambda [regex Pattern]      [Directory] [outFile] ```

``` java -jar target/grep-1.0_SNAPSHOT.jar [regex Pattern] [Directory] [outFile] ```

## Implemenation

### Pseudocode
The whole application is designed to follow a high level process flow. Each functionality is divided into methods much like Modular programming. The First methods looks for files recursively and passes it to another method which scans each for regular expression and finally matched patterns are written into outfile.
``` 
matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines) 
```

### Performance Issue
The performance issue that application faces is that it might throw ```java.lang.OutOfMemoryError``` when small heap memory is allocated.This is commonly known as memory leak. In other words, the performance of this application depends on file to be scanned.Since it stores each line into an arraylist which linearly increases heap memory needed.

### Test
The repository contains a text file in ```./data``` folder. When this directory is passed as an argument for our application produces same set of output lines which was produced when egrep UNIX command was run

### Deployment
A DockerFile was created from using ```openjdk:8-alpine``` which also copied JAR file created as a result maven build using UberJAR plugin.Running the docker container is specified above and image is available on DockerHub.

```
cat > Dockerfile << EOF
FROM openjdk:8-alpine
COPY target/grep*.jar /usr/local/app/grep/lib/grep.jar
ENTRYPOINT ["java","-jar","/usr/local/app/grep/lib/grep.jar"]
EOF 
```

Package the App

```mvn clean package```

Build Image

```docker build -t ${docker_user}/grep .```

# Improvement
- Instead of storing each lines into a list if the pattern matching process happens simultaneously along with reading each line it can decrease the need of heap memory
- Instead of passing inputs as arguments the application can be made interactive making another process for User Interface
- Inputs can be validated