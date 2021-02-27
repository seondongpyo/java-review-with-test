package io;

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

    @Test
    @DisplayName("StringReader와 StringWriter")
    void stringReader_stringWriter() {
        String str = "Hello World!";

        StringReader stringReader = new StringReader(str);
        StringWriter stringWriter = new StringWriter();

        int data = 0;
        try {
            while ((data = stringReader.read()) != -1) {
                stringWriter.write(data);
            }

        } catch (IOException e) {
            //
        }

        assertThat(stringWriter.toString()).isEqualTo(str);
    }

    @Test
    @DisplayName("BufferedReader와 BufferedWriter")
    void bufferedReader_bufferedWriter() {
        File file = new File("./file/text/hangul_text.txt");
        StringBuilder resultStr = new StringBuilder();

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                resultStr.append(line);
            }

            bufferedReader.close();

        } catch (IOException e) {
            //
        }

        assertThat(resultStr.toString()).isEqualTo("안녕하세요");
    }
}
