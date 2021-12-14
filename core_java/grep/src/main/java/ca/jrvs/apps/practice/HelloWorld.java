package ca.jrvs.apps.practice;
class HelloWorld {

    // Your program begins with a call to main().
    //   // Prints "Hello, World" to the terminal window.
    public static void main(String arg[]) {
        RegexExc regex = new RegexExcImp();
        String intext = "name.jpeg";
        System.out.println(regex.matchJpeg(intext));
    }
}
