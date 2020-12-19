package collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class MapTest {

    @Test
    @DisplayName("Map 인터페이스")
    void map() {
        Map<Integer, String> memberMap = new HashMap<>();
        memberMap.put(1, "홍길동"); // put(key, value) : Map에 값 객체를 키 객체에 연결하여 저장
        memberMap.put(2, "김길동");
        memberMap.put(3, "이길동");
        memberMap.put(4, "박길동");

        Map<Integer, String> newMemberMap = new HashMap<>();
        newMemberMap.put(5, "송길동");
        newMemberMap.put(6, "나길동");
        newMemberMap.put(7, "강길동");

        assertThat(memberMap.containsKey(1)).isTrue(); // containsKey : Map에 지정된 키 객체와 일치하는 키 객체가 있는지 확인
        assertThat(memberMap.containsKey(5)).isFalse(); // Map에 '5'라는 키 객체는 존재하지 않음
        assertThat(memberMap.containsValue("홍길동")).isTrue(); // containsValue : Map에 지정된 값 객체와 일치하는 값 객체가 있는지 확인
        assertThat(memberMap.containsValue("최길동")).isFalse(); // Map에 '최길동'이라는 값 객체는 존재하지 않음
        assertThat(memberMap.get(3)).isEqualTo("이길동"); // get(index) : 지정한 키 객체에 대응하는 값 객체를 반환
        assertThat(memberMap.get(5)).isEqualTo(null); // 지정된 키 객체에 대응하는 값 객체가 없으면 null 반환

        // keySet : Map에 저장된 모든 키 객체를 반환 (Map에서 키는 중복을 허용하지 않기 때문에 Set 인터페이스로 반환)
        assertThat(memberMap.keySet()).isInstanceOf(Set.class);

        // values : Map에 저장된 모든 값 객체를 반환 (Map에서 값은 중복을 허용하기 때문애 Collection 인터페이스로 반환)
        assertThat(memberMap.values()).contains("홍길동", "김길동", "이길동", "박길동");

        assertThat(memberMap.remove(2)).isEqualTo("김길동"); // remove(key) : 지정된 키 객체에 대응하는 값 객체를 삭제 후 삭제된 값 객체를 반환
        assertThat(memberMap.size()).isEqualTo(3).isNotEqualTo(4); // size : Map에 저장된 키 - 값 쌍의 개수를 반환
        
        // putAll : 지정된 Map의 모든 키 - 값 쌍을 추가
        memberMap.putAll(newMemberMap); // (홍, 김, 이) -> (홍, 김, 이, 송, 나, 강)
        assertThat(memberMap).hasSize(6);
        assertThat(memberMap).containsValues("송길동", "나길동", "강길동");
        assertThat(memberMap).containsKeys(5, 6, 7);
    }
}
