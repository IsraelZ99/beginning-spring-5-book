package org.book.chapter2;

import java.io.PrintStream;

public class HelloWorldGreeter implements Greeter {

    private PrintStream printStream = System.out;

    @Override
    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void greet() {
        printStream.print("Hello, World!");
    }
}
