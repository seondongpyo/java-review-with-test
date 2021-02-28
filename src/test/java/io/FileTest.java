package io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FileTest {

    @Test
    @DisplayName("새로운 File 생성 및 확인")
    void file_1() {
        File file = new File("./file/text/create_new_file.txt");
        String fileName = file.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

        assertThat(file.isFile()).isTrue();
        assertThat(file.isDirectory()).isFalse();
        assertThat(file.exists()).isTrue();
        assertThat(fileName).isEqualTo("create_new_file.txt");
        assertThat(fileExtension).isEqualTo("txt");
    }

    @Test
    @DisplayName("특정 디렉토리에 포함된 파일과 디렉토리 목록 조회")
    void file_2() {
        File directory = new File("./file/text");

        File[] files = directory.listFiles();
        List<String> fileNames
                = Arrays.stream(files).map(File::getName).collect(Collectors.toList());

        assertThat(directory).isDirectory();
        assertThat(fileNames.stream().allMatch(file -> file.endsWith("txt"))).isTrue();
    }

    @Test
    @DisplayName("파일의 생성과 삭제")
    void file_3() {
        boolean isNewFileCreated = false;
        boolean isNewFileDeleted = false;
        String newFileName = null;
        String newFileExtension = null;

        try {
            File newFile = new File("./file/text/new_file.txt");
            isNewFileCreated = newFile.createNewFile();
            newFileName = newFile.getName();
            newFileExtension = newFileName.substring(newFileName.lastIndexOf(".") + 1);
            isNewFileDeleted = newFile.delete();

        } catch (IOException e) {
            //
        }

        assertThat(isNewFileCreated).isTrue();
        assertThat(newFileName).isEqualTo("new_file.txt");
        assertThat(newFileExtension).isEqualTo("txt");
        assertThat(isNewFileDeleted).isTrue();
    }
}
