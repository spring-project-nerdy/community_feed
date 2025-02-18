package org.example.post.domain.common;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DateTimeInfoTest {

  @Test
  void givenCreated_whenUpdated_thenTimeAndStateArsUpdated() throws InterruptedException {
    // given
    DatetimeInfo datetimeInfo = new DatetimeInfo();
    LocalDateTime localDateTime = datetimeInfo.getDatetime();


    // when
    datetimeInfo.updateEditDatetime();

    // then
    assertTrue(datetimeInfo.isEdited());
    assertNotEquals(localDateTime, datetimeInfo.getDatetime());
  }
}
