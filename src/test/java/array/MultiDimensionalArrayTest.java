package array;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MultiDimensionalArrayTest {

    @Test
    @DisplayName("2차원 배열의 선언과 생성 및 초기화")
    void twoDimensionalArray() {
        // 선언
        int[][] array1;
        int array2[][];
        int[] array3[];

        // 생성
        array1 = new int[2][3];
        array2 = new int[3][4];
        array3 = new int[5][6];

        // 초기화
        array1[0][0] = 1;
        array2[0][1] = 2;
        array3[0][2] = 3;

        // 선언과 생성을 동시에
//        int[][] array = new int[1][2];

        // 선언과 생성 및 초기화를 동시에
        int[][] array4 = new int[][]{{1, 2, 3}, {4, 5, 6}};
        int[][] array5 = {{1, 2, 3, 4}, {5, 6, 7, 8}};

        assertThat(array1).hasSize(2).contains(new int[3]); // array1은 길이가 3인 배열 2개를 포함하고 있다
        assertThat(array2).hasSize(3).contains(new int[4]); // array2는 길이가 4인 배열 3개를 포함하고 있다
        assertThat(array3).hasSize(5).contains(new int[6]); // array3은 길이가 6인 배열 5개를 포함하고 있다
        assertThat(array1[0]).hasSize(3).contains(1); // array1의 첫 번째 배열에는 1을 포함하고 있다
        assertThat(array2[1]).hasSize(4).contains(2); // array2의 두 번째 배열에는 2를 포함하고 있다
        assertThat(array3[2]).hasSize(6).contains(3); // array3의 세 번째 배열에는 3을 포함하고 있다
        assertThat(array4[0][1]).isEqualTo(2); // array4의 첫 번째 배열의 두 번째(인덱스가 1인) 배열 요소는 2
        assertThat(array5[1][2]).isEqualTo(7); // array5의 두 번째 배열의 세 번째(인덱스가 2인) 배열 요소는 7
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> array4[1][3] = 7);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> array5[0][4] = 5);
    }
}
