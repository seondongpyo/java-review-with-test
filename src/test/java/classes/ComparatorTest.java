package classes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

class ComparatorTest {

    @Test
    @DisplayName("배열 정렬 시 Comparator 미지정 : 저장하는 객체에 구현된 내용에 따라 정렬된다")
    void sort_without_comparator() {
        String[] strArr = {"cat", "Dog", "tiger", "Lion", "bear"};

        // Arrays.sort() : Comparator를 지정해주지 않으면 저장하는 객체에 구현된 내용에 따라 정렬된다
        Arrays.sort(strArr); // 사전 순으로 정렬 (공백, 숫자, 대문자, 소문자 순으로 정렬)

        assertThat(strArr).containsExactly("Dog", "Lion", "bear", "cat", "tiger");
    }

    @Test
    @DisplayName("배열 정렬 시 Comparator 지정 : 지정한 Comparator에 따라 정렬된다")
    void sort_with_comparator_ascending() {
        String[] strArr = {"cat", "Dog", "tiger", "Lion", "bear"};
        Arrays.sort(strArr, String.CASE_INSENSITIVE_ORDER); // 대소문자 구분 없이 정렬

        assertThat(strArr).isSortedAccordingTo(String.CASE_INSENSITIVE_ORDER);
        assertThat(strArr).containsExactly("bear", "cat", "Dog", "Lion", "tiger");
    }

    @Test
    @DisplayName("배열 정렬 시 Comparator를 지정하여 내림차순 정렬하기")
    void sort_with_comparator_descending() {
        String[] strArr = {"cat", "Dog", "tiger", "Lion", "bear"};
        Integer[] intArr = {3, 5, 2, 1, 4};

        Arrays.sort(strArr, Collections.reverseOrder()); // 내림차순 정렬
        Arrays.sort(intArr, Comparator.reverseOrder()); // 내림차순 정렬

        assertThat(strArr).containsExactly("tiger", "cat", "bear", "Lion", "Dog");
        assertThat(intArr).containsExactly(5, 4, 3, 2, 1);
    }
}
