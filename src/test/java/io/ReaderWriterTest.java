package io;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ReaderWriterTest {

    @Test
    @DisplayName("FileReader와 FileWriter 테스트")
    void fileReader_fileWriter() {
        String fileName = "./file/text/text.txt";
        String newFileName = "./file/text/writer_text.txt";

        try {
            FileReader fileReader = new FileReader(fileName);
            FileWriter fileWriter = new FileWriter(newFileName);

            int data;
            while ((data = fileReader.read()) != -1) {
                fileWriter.write(data);
            }

            fileWriter.close();
            fileReader.close();

        } catch (IOException e) {
            //
        }

        assertThat(new File(newFileName)).hasContent("Hello World!");
    }

    @Test
    @DisplayName("FileInputStream vs FileReader")
    void fileInputStream_vs_fileReader() {
        String fileName = "./file/text/hangul_text.txt";
        StringBuilder strFromFileInputStream = new StringBuilder();
        StringBuilder strFromFileReader = new StringBuilder();

        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            FileReader fileReader = new FileReader(fileName);

            int data;

            // 1. FileInputStream
            while ((data = fileInputStream.read()) != -1) {
                strFromFileInputStream.append((char) data);
            }
            fileInputStream.close();

            // 2. FileReader
            while ((data = fileReader.read()) != -1) {
                strFromFileReader.append((char) data);
            }
            fileReader.close();

        } catch (IOException e) {
            //
        }

        assertThat(strFromFileInputStream.toString()).isNotEqualTo("안녕하세요");
        assertThat(strFromFileReader.toString()).isEqualTo("안녕하세요");
    }
}
