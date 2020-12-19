package collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ListTest {

    @Test
    @DisplayName("List 인터페이스에 정의된 메서드")
    void list() {
        List<String> memberList = new ArrayList<>();

        // add : 데이터 추가
        memberList.add("홍길동");
        memberList.add("홍길동");
        assertThat(memberList).hasSize(2);

        // add(index, element) : 지정된 위치에 객체 또는 컬렉션에 포함된 객체들을 추가
        // 지정된 위치 및 뒤에 존재하던 기존 데이터들의 순서는 하나씩 뒤로 밀림
        memberList.add(0, "김길동");

        assertThat(memberList).hasSize(3);
        assertThat(memberList.get(0)).isEqualTo("김길동").isNotEqualTo("홍길동"); // get : 지정된 위치에 있는 객체를 반환
        assertThat(memberList.indexOf("홍길동")).isEqualTo(1).isNotEqualTo(2); // indexOf : 지정된 객체를 첫 번째 요소부터 순방향으로 탐색하여 존재할 경우 해당 위치를 반환
        assertThat(memberList.indexOf("고길동")).isEqualTo(-1); // indexOf : 지정된 객체를 찾지 못할 경우 -1 반환
        assertThat(memberList.lastIndexOf("홍길동")).isEqualTo(2); // lastIndexOf : 지정된 객체를 마지막 요소부터 역방향으로 탐색하여 존재할 경우 해당 위치를 반환
        assertThat(memberList.lastIndexOf("송길동")).isEqualTo(-1); // lastIndexOf : 지정된 객체를 찾지 못할 경우 -1 반환

        // set(index, element) : 지정된 위치에 존재하는 기존 객체를 매개변수로 전달한 객체로 변경
        // 현재 리스트의 인덱스를 벗어난 인덱스를 전달하면 IndexOutOfBoundsException 발생
        memberList.set(2, "박길동"); // (김길동, 홍길동, 홍길동) -> (김길동, 홍길동, 박길동)
        assertThat(memberList.get(2)).isEqualTo("박길동").isNotEqualTo("홍길동");
        assertThatThrownBy(() -> { memberList.set(3, "노길동"); }).isInstanceOf(IndexOutOfBoundsException.class);

        // subList(fromIndex, toIndex) : 지정된 범위에 있는 객체를 반환 (마지막 인덱스에 해당하는 객체는 포함되지 않음)
        List<String> fromZeroToOneIndexMemberList = memberList.subList(0, 2);
        assertThat(fromZeroToOneIndexMemberList).contains("김길동").contains("홍길동").doesNotContain("박길동");

        // remove(index) : 지정된 위치에 있는 객체를 삭제하고 삭제한 객체를 반환
        String removedMember = memberList.remove(2); // 리스트에서 '박길동' 삭제
        assertThat(memberList).hasSize(2);
        assertThat(removedMember).isEqualTo("박길동");
    }
}
