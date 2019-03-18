package com.jieyouxu.lexer.integration_test;

import com.jieyouxu.lexer.LexerAssertions;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.fail;

public class IntegrationTestRunner {
    private String baseDirectory;

    private Path path;

    public IntegrationTestRunner(String baseDirectory) {
        this.baseDirectory = Objects.requireNonNull(baseDirectory);
    }

    public void runTest(String fileName, List<Integer> expectedSymbols) {
        Objects.requireNonNull(fileName);
        Objects.requireNonNull(expectedSymbols);

        buildFullPath(fileName);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            LexerAssertions.assertSymbolsMatch(reader, expectedSymbols);
        } catch (IOException e) {
            fail(e.toString());
        }
    }

    private void buildFullPath(String fileName) {
        Objects.requireNonNull(fileName);
        path = Paths.get(baseDirectory + fileName);
    }
}
