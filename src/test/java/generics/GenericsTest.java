package generics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class GenericsTest {

    @Test
    @DisplayName("제네릭스 - 컴파일 시 메서드나 컬렉션 클래스의 타입을 체크한다")
    void generics() {
        /*
            << 제네릭스 (Generics) >>
            - 다양한 타입의 객체들을 다루는 메서드나 컬렉션 클래스에서 컴파일 시 타입 체크를 해주는 기능
            - 객체의 타입을 컴파일 시 체크하기 때문에 객체의 타입 안정성을 높이고 형변환의 번거로움이 줄어든다
         */

        ArrayList<String> stringList = new ArrayList<>();
//        stringList.add(1); // 컴파일 에러
        stringList.add("1");

        assertThat(stringList).hasOnlyElementsOfType(String.class);
    }

    @Test
    @DisplayName("제네릭 클래스의 타입 변수에 대입하기")
    void typeVariable() {
        /*
            << 타입 변수(Type Variable) >>
            - 일반적으로 'Type'의 첫 글자를 따서 T를 사용함
            - 반드시 T를 사용해야 할 필요는 없다 (ArrayList<E>의 경우, 'Element(요소)'의 첫 글자를 따서 E를 사용함)
         */

        Box<String> stringBox = new Box<>(); // String : 대입된 타입(또는 매개변수화된 타입, parameterized type)
        stringBox.setItem("1");
//        stringBox.setItem(1); // 컴파일 에러 
        
        Box<Integer> integerBox = new Box<>();
        integerBox.setItem(1);
//        integerBox.setItem("1"); // 컴파일 에러

        assertThat(stringBox.getItem()).isEqualTo("1");
        assertThat(integerBox.getItem()).isEqualTo(1);
    }

    static class Box<T> {
        // 'Box<T>' : 제네릭 클래스
        // 'Box' : 원시 타입
        // 'T' : 타입 변수 또는 타입 매개변수

        T item;

        public void setItem(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }
    }
    
}
