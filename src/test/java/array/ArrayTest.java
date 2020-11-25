package array;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArrayTest {

    @Test
    @DisplayName("배열의 선언과 생성 및 초기화")
    void array_declaration_creation_initialization() {
        // 배열의 선언
        int[] intArray1; // 권장하는 방법
        String strArray1[]; // 가능하지만 추천하지 않는 방법

        // 배열의 생성
        intArray1 = new int[3]; // 길이가 3인 배열
        strArray1 = new String[7]; // 길이가 7인 배열

        // 배열의 초기화
        intArray1[0] = 1; // 첫 번째 배열 요소의 값을 1로 초기화 (인덱스는 0부터 시작)
        strArray1[0] = "one";
        strArray1[1] = "two";

        // 배열의 선언, 생성, 초기화를 동시에 수행
        int[] intArray2 = {1, 2, 3, 4, 5}; // 크키가 5인 int 배열
        // => int[] intArray2 = new int[]{1, 2, 3, 4, 5}
        String[] strArray2 = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"}; // 크기가 10인 String 배열
        // => String[] strArray2 = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"}

        // 배열의 길이는 '.length'를 통해 구할 수 있다
        assertThat(intArray1.length).isEqualTo(3);
        assertThat(strArray1.length).isEqualTo(7);
        assertThat(intArray2.length).isEqualTo(5);
        assertThat(strArray2.length).isEqualTo(10);

        assertThat(intArray1).contains(1).doesNotContain(2); // 첫 번째 int 배열에 1은 존재하지만 2는 없다
        assertThat(strArray1).contains("one").doesNotContain("three"); // 첫 번째 String 배열에 "two"는 존재하지만 "three"는 없다

        // 배열의 길이를 초과하는 인덱스를 사용하려고 할 경우, ArrayIndexOutOfBoundsException 발생
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> intArray1[3] = 10);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> strArray1[7] = "six");
    }

    @Test
    @DisplayName("배열 요소의 초기값")
    void array_element_initial_value() {
        byte[] byteArray = new byte[1];
        short[] shortArray = new short[1];
        int[] intArray = new int[1];
        char[] charArray = new char[1];
        float[] floatArray = new float[1];
        double[] doubleArray = new double[1];
        boolean[] booleanArray = new boolean[1];
        String[] strArray = new String[1];

        // 기본타입 배열 요소의 초기값 = 각 기본타입의 초기값
        assertThat(byteArray[0]).isEqualTo((byte) 0);
        assertThat(shortArray[0]).isEqualTo((short) 0);
        assertThat(intArray[0]).isEqualTo(0);
        assertThat(charArray[0]).isEqualTo('\u0000');
        assertThat(floatArray[0]).isEqualTo(0.0f);
        assertThat(doubleArray[0]).isEqualTo(0.0);
        assertThat(booleanArray[0]).isEqualTo(false);

        // 참조타입 배열 요소의 초기값 = null
        assertThat(strArray[0]).isEqualTo(null);
    }

    @Test
    @DisplayName("배열의 활용")
    void array_exercise() {
        int[] scores = {75, 80, 85, 90, 95};
        int sum = 0;

        for (int score : scores) {
            sum += score;
        }

        assertThat(sum).isEqualTo(425); // 점수의 총합
        assertThat(sum / scores.length).isEqualTo(85); // 평균 점수

    }
}
