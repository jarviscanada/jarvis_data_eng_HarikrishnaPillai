package ca.jrvs.apps.practice;
import java.util.Scanner;

public class RegexExcImp implements RegexExc {


    //Methods
    @Override
    public boolean matchJpeg(String filename) {
        return filename.matches("([^\\s]+(\\.(?i)(jpe?g))$)");
    }

    @Override
    public boolean matchIp(String ip) {
        return ip.matches("\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}");
    }

    @Override
    public boolean isEmptyLine(String line) {
        return line.matches("^\\s*$");
    }
}

