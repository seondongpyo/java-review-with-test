package classes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleDateFormatTest {

    @Test
    @DisplayName("형식화 클래스 - SimpleDateFormat")
    void simpleDateFormat() {
        // 날짜 생성 : 2020년 11월 18일 23시 21분 30초
        Date date = new Date(2020 - 1900, 11, 18, 23, 21, 30);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // y(연도), M(월), d(일)
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss:SSS"); // H(시간, 0~23), m(분), s(초), S(1/1000초)
        SimpleDateFormat upperD = new SimpleDateFormat("2020년 올해의 D번째 날");
        SimpleDateFormat lowerD = new SimpleDateFormat("2020년 12월의 d번째 날");
        SimpleDateFormat upperW = new SimpleDateFormat("2020년 12월의 W번째 주");
        SimpleDateFormat lowerW = new SimpleDateFormat("2020년 올해의 w번째 주");
        SimpleDateFormat fe = new SimpleDateFormat("2020년 12월의 F번째 E요일");

        assertThat(dateFormat.format(date)).isEqualTo("2020-12-18");
        assertThat(timeFormat.format(date)).isEqualTo("23:21:30:000");
        assertThat(upperD.format(date)).isEqualTo("2020년 올해의 353번째 날");
        assertThat(lowerD.format(date)).isEqualTo("2020년 12월의 18번째 날");
        assertThat(upperW.format(date)).isEqualTo("2020년 12월의 3번째 주");
        assertThat(lowerW.format(date)).isEqualTo("2020년 올해의 51번째 주");
        assertThat(fe.format(date)).isEqualTo("2020년 12월의 3번째 금요일");
    }
}
