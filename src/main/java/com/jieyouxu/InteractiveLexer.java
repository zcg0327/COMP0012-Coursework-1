package com.jieyouxu;

import java.io.*;

public class InteractiveLexer {
    public static void main(String[] args) {
        System.out.println(":: Lexer ::");

        String file = "src/main/resources/open-test-cases/p-seq.s";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Lexer lexer = new Lexer(reader);
            printTokens(lexer);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("I/O Exception.");
        }
    }

    private static void printTokens(Lexer lexer) throws IOException {
        int nextToken;
        do {
            nextToken = lexer.next_token().sym;
            System.out.println(sym.terminalNames[nextToken]);
        } while (nextToken != sym.EOF);
    }
}
