package org.example.post.domain.content;

import org.example.post.common.DateTimeInfo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateTimeInfoTest {

    @Test
    void givenCreated_whenUpdated_thenTimeAndStateAsrUpdated() throws InterruptedException {
        //given
        DateTimeInfo dateTimeInfo = new DateTimeInfo();
        LocalDateTime localDatetime = dateTimeInfo.getDateTime();

        //then
        sleep(2000);
        dateTimeInfo.updateEditDateTime();

        //when
        assertTrue(dateTimeInfo.isEdited());
        assertNotEquals(localDatetime, dateTimeInfo.getDateTime());
    }
}
