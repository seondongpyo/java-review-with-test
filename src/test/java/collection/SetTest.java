package collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SetTest {

    @Test
    @DisplayName("Set 인터페이스에 정의된 메서드")
    void set() {
        Set<String> memberSet = new HashSet<>();
        
        // add : 지정된 객체를 저장
        memberSet.add("홍길동");
        memberSet.add("고길동");
        memberSet.add("김길동");
        memberSet.add("송길동");
        memberSet.add("홍길동"); // Set 인터페이스는 중복을 허용하지 않으므로 저장되지 않음

        Set<String> newMemberSet = new HashSet<>();
        newMemberSet.add("고길동");
        newMemberSet.add("홍길동");

        assertThat(memberSet).hasSize(4);
        assertThat(memberSet.contains("김길동")).isTrue(); // contains : 지정된 객체가 컬렉션에 포함되어 있는지 확인
        assertThat(memberSet.containsAll(newMemberSet)).isTrue(); // containsAll : 지정된 컬렉션 객체들이 컬렉션에 포함되어 있는지 확인
        assertThat(memberSet.isEmpty()).isFalse(); // isEmpty : 컬렉션이 비어 있는지 확인
        assertThat(memberSet.remove("김길동")).isTrue(); // remove : 지정된 객체가 컬렉션에서 삭제되면 true 반환
        assertThat(memberSet.remove("박길동")).isFalse(); // remove : 컬렉션에 존재하지 않는 객체를 삭제하려고 하면 false 반환

        // retainAll : 지정된 컬렉션에 포함된 객체만을 남기고, 다른 객체들은 컬렉션에서 삭제
        // 이 작업으로 인해 컬렉션에 변화가 있으면 true, 없으면 false를 반환
        assertThat(memberSet.retainAll(newMemberSet)).isTrue(); // (홍길동, 고길동, 김길동, 송길동) -> (홍길동, 고길동)
        assertThat(memberSet).hasSize(2).doesNotContain("김길동", "송길동"); // 김길동, 송길동은 삭제됨

        newMemberSet.clear(); // clear : 컬렉션의 모든 객체를 삭제
        assertThat(newMemberSet.size()).isZero(); // size : 컬렉션에 저장된 객체의 개수를 반환
    }
}
