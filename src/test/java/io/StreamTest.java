package io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamTest {

    @Test
    @DisplayName("InputStream과 OutputStream 1")
    void inputStream_outputStream_1() {
        byte[] inputSource = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputSource);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        int data;
        while ((data = inputStream.read()) != -1) { // read()의 반환값을 data에 저장한 뒤, 값이 -1인지 비교
            outputStream.write(data);
        }

        byte[] outputSource = outputStream.toByteArray(); // 스트림의 내용을 byte 배열로 반환

        assertThat(outputSource).hasSize(10);
        assertThat(outputSource).contains(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    @Test
    @DisplayName("InputStream과 OutputStream 2")
    void inputStream_outputStream_2() {
        byte[] inputSource = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        byte[] temp = new byte[10];

        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputSource);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // read(byte[] b, int off, int len)
        //  : 최대 len개의 byte를 읽어서, 배열 b의 지정된 위치(off)부터 저장한다
        inputStream.read(temp, 0, temp.length); // 최대 10개의 byte를 읽어서 temp 배열의 첫 번째 위치부터 저장

        // write(byte[] b, int off, int len)
        //  : 주어진 배열 b에 저장된 내용 중에서 off번째부터 len개만큼을 읽어서 쓰기
        outputStream.write(temp, 5, 3); // temp 배열의 6번째(temp[5])부터 3개만큼 읽어서 출력 소스에 쓰기

        byte[] outputSource = outputStream.toByteArray();

        assertThat(outputSource).hasSize(3);
        assertThat(outputSource).contains(5, 6, 7);
    }

    @Test
    @DisplayName("InputStream과 OutputStream 3")
    void inputStream_outputStream_3() {
        byte[] inputSource = {0, 1, 2, 3, 4};
        byte[] temp = new byte[2];
        byte[] outputSource = null;

        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputSource);
        ByteArrayOutputStream outputsStream = new ByteArrayOutputStream();

        try {
            while (inputStream.available() > 0) {
                // read(byte[] b) : 배열 b의 크기만큼 읽어서 배열을 채우고, 읽어 온 데이터의 수를 반환
                inputStream.read(temp); // inputSource 배열로부터 temp 배열의 크기만큼 읽어서 채운다
                
                // write(byte[] b) : 주어진 배열 b에 저장된 모든 내용을 출력 소스에 쓴다
                outputsStream.write(temp); // temp 배열에 저장된 모든 내용을 쓴다

                outputSource = outputsStream.toByteArray();
            }
        } catch (IOException e) {
            //
        }

        // ** outputSource 배열 요소의 변화
        // temp = [0, 1], outputSource = [0, 1]
        // temp = [2, 3], outputSource = [0, 1, 2, 3]
        // temp = [4, 3], outputSource = [0, 1, 2, 3, 4, 3]
        // -> temp에 담긴 내용을 지우고 쓰지 않고 기존 내용 위에 덮어 쓴다
        assertThat(outputSource).contains(0, 1, 2, 3, 4, 3);
    }

    @Test
    @DisplayName("InputStream과 OutputStream 4")
    void inputStream_outputStream_4() {
        byte[] inputSource = {0, 1, 2, 3, 4};
        byte[] temp = new byte[2];
        byte[] outputSource = null;

        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputSource);
        ByteArrayOutputStream outputsStream = new ByteArrayOutputStream();

        try {
            while (inputStream.available() > 0) {
                int len = inputStream.read(temp);
                outputsStream.write(temp, 0, len); // temp 배열의 첫 번째 요소부터 읽어 온 데이터의 수만큼 쓰기
                outputSource = outputsStream.toByteArray();
            }
        } catch (IOException e) {
            //
        }

        // ** outputSource 배열 요소의 변화
        // temp = [0, 1], len = 2, outputSource = [0, 1]
        // temp = [2, 3], len = 2, outputSource = [0, 1, 2, 3]
        // temp = [4, 3], len = 1, outputSource = [0, 1, 2, 3, 4]
        assertThat(outputSource).containsExactly(0, 1, 2, 3, 4);
    }
}
