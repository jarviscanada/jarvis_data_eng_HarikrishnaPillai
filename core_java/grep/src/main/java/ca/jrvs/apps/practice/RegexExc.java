package ca.jrvs.apps.practice;

public interface RegexExc {
    /**
     * Checks if the extension .jpg or .jpeg
     * @param filename
     * @return boolean
     */
    public boolean matchJpeg(String filename);
     /**
     * Checks if the IP address is valid or not
     * @param ip
     * @return boolean
     */
     public boolean matchIp(String ip);

    /**
     * Checks if the input is an empty line
     * @param line
     * @return boolean
     */
    public boolean isEmptyLine(String line);
}
