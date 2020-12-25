package collection.set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class HashSetTest {

    @Test
    @DisplayName("HashSet 객체의 생성")
    void newHashSet() {
        // load factor
        //  -   컬렉션의 저장공간이 가득 차기 전에 미리 용량을 확보하기 위한 것으로, 
        //      이 값을 0.8로 지정하면, 저장공간의 80%가 채워졌을 때 용량이 두 배로 늘어난다
        HashSet<Integer> hashSet1 = new HashSet<>(); // 기본 생성자로 생성 시 HashSet의 초기용량은 16, load factor는 0.75
        HashSet<Integer> hashSet2 = new HashSet<>(hashSet1); // 주어진 컬렉션을 포함하는 HashSet 생성
        HashSet<Integer> hashSet3 = new HashSet<>(5); // 주어진 값을 초기용량으로 하는 HashSet 생성
        HashSet<Integer> hashSet4 = new HashSet<>(5, 0.8f); // 초기용량과 load factor를 지정하여 생성

        assertThat(hashSet1).isInstanceOf(Set.class);
        assertThat(hashSet2).isInstanceOf(Set.class);
        assertThat(hashSet3).isInstanceOf(Set.class);
        assertThat(hashSet4).isInstanceOf(Set.class);
    }

    @Test
    @DisplayName("HashSet 클래스 add() - 요소 추가 (중복 요소 제외)")
    void add_1() {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(1);
        hashSet.add(5);
        hashSet.add(2);
        hashSet.add(4);
        hashSet.add(3);
        boolean isAdded = hashSet.add(5); // Set 인터페이스를 구현한 클래스이므로 중복된 요소를 저장하지 않는다

        /*
            << HashSet의 중복 저장 방지 >>
            1) 객체를 HashSet에 저장하기 전에 먼저 객체의 hashCode() 메서드로 해시 코드를 얻어낸다
            2) HashSet에 저장되어 있는 기존 객체들의 해시 코드들과 비교한다
            3) 같은 해시 코드가 있을 경우 equals() 메서드로 두 객체를 비교한다
            4) equals() 메서드로부터 true 반환 시 동일한 객체로 판단하고 중복 저장을 하지 않는다
         */

        assertThat(isAdded).isFalse();
        assertThat(hashSet).hasSize(5);
        assertThat(hashSet).doesNotContainSequence(1, 5, 2, 4, 3); // HashSet은 저장순서를 유지하지 않는다
        assertThat(hashSet).containsExactlyInAnyOrder(1, 2, 3, 4, 5);
    }

    @Test
    @DisplayName("HashSet 클래스 add() - 문자열의 중복 저장")
    void add_2() {
        /*
            << HashSet의 문자열 저장 >>
            - 문자열을 HashSet에 저장할 경우, 같은 문자열을 갖는 String 객체는 동일한 객체로 간주되는데
              String 클래스는 같은 문자열일 경우 hashCode(), equals() 메소드 리턴 값이 각각 true가 나오도록 오버라이딩했기 때문이다
         */
        String str1 = new String("Hello");
        String str2 = new String("Hello");
        
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add(str1);
        boolean isAdded = hashSet.add(str2);

        assertThat(str1 == str2).isFalse(); // str1과 str2의 주소는 다르다
        assertThat(str1.equals(str2)).isTrue(); // str1과 str2는 같은 문자열을 가진다
        assertThat(str1.hashCode()).isEqualTo(str2.hashCode()); // 같은 문자열일 경우 해시 코드 값이 동일하다
        assertThat(isAdded).isFalse(); // str1과 같은 문자열을 가진 str2는 중복 요소로 간주하여 저장되지 않는다
        assertThat(hashSet).hasSize(1);
    }
    
    @Test
    @DisplayName("HashSet 클래스 addAll() - 주어진 컬렉션에 저장된 모든 객체를 추가한다")
    void addAll() {
        HashSet<Integer> hashSet1 = new HashSet<>();
        hashSet1.add(1);
        hashSet1.add(2);
        hashSet1.add(3);

        HashSet<Integer> hashSet2 = new HashSet<>();
        hashSet2.add(1);

        HashSet<Integer> hashSet3 = new HashSet<>();
        hashSet3.add(1);
        hashSet3.add(2);
        hashSet3.add(3);

        // Q. 1이 저장된 상태에서 {1, 2, 3}을 저장하려고 한다면?
        boolean isAdded1 = hashSet2.addAll(hashSet1); // 중복 요소를 제외한 나머지 요소들만 추가
        assertThat(isAdded1).isTrue();
        assertThat(hashSet2).hasSize(3);
        assertThat(hashSet2).containsExactlyInAnyOrder(1, 2, 3);

        // Q. {1, 2, 3}이 저장된 상태에서 {1, 2, 3}을 저장하려고 한다면?
        boolean isAdded2 = hashSet3.addAll(hashSet1); // 추가하려는 요소들이 모두 중복된 요소라면 추가 실패
        assertThat(isAdded2).isFalse();
    }

    @Test
    @DisplayName("HashSet 클래스 contains() - 지정된 객체를 포함하고 있는지 확인")
    void contains() {
        HashSet<Integer> hashSet = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            hashSet.add(i);
        }

        assertThat(hashSet.contains(9)).isTrue();
        assertThat(hashSet.contains(10)).isFalse();
    }

    @Test
    @DisplayName("HashSet 클래스 containsAll() - 주어진 컬렉션에 저장된 모든 객체들을 포함하고 있는지 확인")
    void containsAll() {
        HashSet<Integer> hashSet = new HashSet<>();
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            hashSet.add(i);
            arrayList.add(i);
        }

        assertThat(hashSet.containsAll(arrayList)).isTrue();
    }
}
