package array;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArraysTest {

    @Test
    @DisplayName("Arrays 클래스 fill() - 배열 채우기")
    void fill() {
        int[] array = new int[5];
        Arrays.fill(array, 3);

        assertThat(array).containsExactly(3, 3, 3, 3, 3);
    }

    @Test
    @DisplayName("Arrays 클래스 setAll() - 배열 채우기(람다식 전달)")
    void setAll() {
        int[] array = new int[5];
        Arrays.setAll(array, (i -> (int)(Math.random() * 2))); // 0 또는 1

        for (int i : array) {
            assertThat(i).isLessThanOrEqualTo(1);
        }
    }

    @Test
    @DisplayName("Arrays 클래스 binarySearch() - 이진 검색")
    void binarySearch() {
        /*
            The array must be sorted (as by the sort(int[]) method) prior to making this call.
            If it is not sorted, the results are undefined.
            If the array contains multiple elements with the specified value,
            there is no guarantee which one will be found.

            ->  binarySearch()를 호출하기 전에 반드시 배열이 정렬된 상태여야 한다
                배열이 정렬된 상태가 아니라면 결과값을 알 수 없다
                검색할 요소가 배열에 여러 개가 존재할 경우 어떤 요소의 위치가 반환될 지는 보장되지 않는다
         */
        int[] array = {5, 3, 4, 2, 1};
        assertThat(Arrays.binarySearch(array, 1)).isNotEqualTo(4);

        Arrays.sort(array); // 배열을 정렬 -> {1, 2, 3, 4, 5}
        assertThat(Arrays.binarySearch(array, 2)).isEqualTo(1);
        assertThat(Arrays.binarySearch(array, 7)).isLessThan(0); // 찾는 요소가 없을 경우 음수 반환
    }

    @Test
    @DisplayName("Arrays 클래스 deepEquals() - 다차원 배열의 비교")
    void equals() {
        /*
            Returns true if the two specified arrays are deeply equal to one another.
            Unlike the equals(Object[],Object[]) method,
            this method is appropriate for use with nested arrays of arbitrary depth.

            -> equals()는 1차원 배열, deepEquals()는 다차원 배열의 비교 시 사용한다
         */
        int[][] array1 = {{1, 2}, {3, 4}};
        int[][] array2 = {{1, 2}, {3, 4}};

        assertThat(Arrays.deepEquals(array1, array2)).isTrue();
    }

    @Test
    @DisplayName("Arrays 클래스 asList() - 배열을 List로 변환")
    void asList() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        assertThat(list).hasSize(5);

        // <주의> asList()에 의해 반환된 List의 크기는 변경할 수 없다
        // 즉, 추가 또는 삭제가 불가능하지만 저장된 내용은 변경할 수 있다
        assertThatThrownBy(() -> list.add(6)).isInstanceOf(UnsupportedOperationException.class);

        // Q. 크기를 변경할 수 있는 List가 필요한 경우에는?
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        assertThat(arrayList.add(6)).isTrue(); // 요소 추가 가능
        assertThat(arrayList).hasSize(6);
    }
}
