package array;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

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
//        intArray1[3] = 10; // ArrayIndexOutOfBoundsException 예외 : 배열의 길이(최대 인덱스는 2)를 초과하는 인덱스(3)를 사용하려고 할 경우 발생
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

    @Test
    @DisplayName("배열 변수의 복사")
    void array_copy_elements() {
        int[] intArray1 = {1, 2, 3, 4, 5};
        int[] intArray2 = intArray1; // 배열은 참조형이기 때문에, 2개의 배열 변수가 하나의 같은 주소를 가리킴
        intArray2[0] = 6; // intArray2[0]을 변경하면 intArray1[0]도 변경

        assertThat(intArray1[0]).isEqualTo(6);
    }

    @Test
    @DisplayName("배열의 복사 - System.arraycopy()")
    void array_copy_by_arraycopy() {
        /*
            System.arraycopy()
                - 배열의 복사만을 위해 만들어진 메서드, 성능이 가장 좋음
                - 배열의 길이를 마음대로 늘릴 수 있음
                - 매개변수
                    src - the source array. (복사할 데이터가 있는 원본 배열)
                    srcPos - starting position in the source array. (복사를 시작할 인덱스 번호 (몇 번째 데이터부터 복사할 것인지))
                    dest - the destination array. (복사된 데이터를 저장할 목적 배열)
                    destPos - starting position in the destination data. (목적 배열에서 저장을 시작할 인덱스 번호)
                    length - the number of array elements to be copied. (복사할 배열 요소의 갯수)
         */

        int[] originArray = {1, 2, 3, 4, 5};
        int[] copiedArray = new int[10];
        System.arraycopy(originArray, 0, copiedArray, 3, originArray.length);
        // originArray 배열의 첫 번째(인덱스 0) 위치부터 5개의 배열 요소를 복사하여 copiedArray 배열의 네 번째(인덱스 3) 위치부터 저장
        // => copiedArray = {0, 0, 0, 1, 2, 3, 4, 5, 0, 0}

        assertThat(copiedArray[0]).isEqualTo(0);
        assertThat(copiedArray[1]).isEqualTo(0);
        assertThat(copiedArray[2]).isEqualTo(0);
        assertThat(copiedArray[3]).isEqualTo(originArray[0]);
        assertThat(copiedArray[4]).isEqualTo(originArray[1]);
        assertThat(copiedArray[5]).isEqualTo(originArray[2]);
        assertThat(copiedArray[6]).isEqualTo(originArray[3]);
        assertThat(copiedArray[7]).isEqualTo(originArray[4]);
        assertThat(copiedArray[8]).isEqualTo(0);
        assertThat(copiedArray[9]).isEqualTo(0);
    }

    @Test
    @DisplayName("배열의 복사 - Arrays.copyOf()")
    void array_copy_by_copyOf() {
        /*
            Arrays.copyOf()
                original - the array to be copied (복사될 원본 배열)
                newLength - the length of the copy to be returned (복사로 만들어질 배열의 길이)

            Arrays.copyOfRange()
                original - the array from which a range is to be copied (복사될 원본 배열)
                from - the initial index of the range to be copied, inclusive (복사할 범위의 시작 인덱스)
                to - the final index of the range to be copied, exclusive. (This index may lie outside the array.) (복사할 범위의 마지막 인덱스, 포함 안 됨)
         */
        int[] originArray = {1, 2, 3, 4, 5};
        int[] copiedArray1 = Arrays.copyOf(originArray, 7);
        // copiedArray1 = {1, 2, 3, 4, 5, 0, 0}

        int[] copiedArray2 = Arrays.copyOfRange(originArray, 0, 3); // <주의> 인덱스 0부터 3까지 배열 요소 4개가 복사되는 게 아니다!
        // copiedArray2 = {1, 2, 3} (!= {1, 2, 3, 4})

        assertThat(copiedArray1[0]).isEqualTo(originArray[0]);
        assertThat(copiedArray1[1]).isEqualTo(originArray[1]);
        assertThat(copiedArray1[2]).isEqualTo(originArray[2]);
        assertThat(copiedArray1[3]).isEqualTo(originArray[3]);
        assertThat(copiedArray1[4]).isEqualTo(originArray[4]);
        assertThat(copiedArray1[5]).isEqualTo(0);
        assertThat(copiedArray1[6]).isEqualTo(0);

        assertThat(copiedArray2.length).isEqualTo(3).isNotEqualTo(4);
        assertThat(copiedArray2[0]).isEqualTo(originArray[0]);
        assertThat(copiedArray2[1]).isEqualTo(originArray[1]);
        assertThat(copiedArray2[2]).isEqualTo(originArray[2]);
    }

    @Test
    @DisplayName("배열의 복사 - clone()")
    void array_copy_by_clone() {
        /*
            clone()
                - 객체의 복사(클로닝)를 위한 메서드
                - 객체가 유지하고 있는 상태값들을 그대로 복사한다
                - Cloneable 인터페이스를 구현한 클래스만 복제가 가능하다
                - 배열 복사 시, 이전 배열과 같은 길이의 배열밖에 만들 수 없다
          */

        int[] originArray = {1, 2, 3, 4, 5};
        int[] clonedArray = originArray.clone();

        assertThat(originArray[0]).isEqualTo(clonedArray[0]);
        assertThat(originArray[1]).isEqualTo(clonedArray[1]);
        assertThat(originArray[2]).isEqualTo(clonedArray[2]);
        assertThat(originArray[3]).isEqualTo(clonedArray[3]);
        assertThat(originArray[4]).isEqualTo(clonedArray[4]);
    }

    @Test
    @DisplayName("배열의 복사 - 반복문 활용")
    void array_copy_by_iteration() {
        int[] originArray = {1, 2, 3, 4, 5};
        int[] copiedArray = new int[5];

        for (int i = 0; i < originArray.length; i++) {
            copiedArray[i] = originArray[i];
        }

        assertThat(originArray[0]).isEqualTo(copiedArray[0]);
        assertThat(originArray[1]).isEqualTo(copiedArray[1]);
        assertThat(originArray[2]).isEqualTo(copiedArray[2]);
        assertThat(originArray[3]).isEqualTo(copiedArray[3]);
        assertThat(originArray[4]).isEqualTo(copiedArray[4]);
    }
}
