package collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CollectionsTest {

    @Test
    @DisplayName("Collections 클래스 - 변경불가(읽기전용) 컬렉션 만들기")
    void synchronizedCollection() {
        List<Integer> unmodifiableList = Collections.unmodifiableList(new ArrayList<>());

        assertThrows(UnsupportedOperationException.class, () -> {
            unmodifiableList.add(1); // 변경불가 상태인 컬렉션에 요소를 저장하려고 할 경우
        });
    }

    @Test
    @DisplayName("Collections 클래스 - 싱글톤(단 하나의 객체만을 저장하는) 컬렉션 만들기")
    void singletonCollection() {
        String str = "Singleton";
        List<String> singletonList = Collections.singletonList(str);

        assertThrows(UnsupportedOperationException.class, () -> {
            singletonList.add("another string"); // 싱글톤 컬렉션에 요소를 저장하려고 할 경우
        });
    }
}
