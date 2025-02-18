package org.example.post.domain.content;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PostContentTest {

  @Test
  void givenContentLengthIsOk_whenCreated_thenReturnTextContent() {
    // given
    String text = "this is a test";

    // when
    PostContent postContent = new PostContent(text);

    // then
    assertEquals(text, postContent.contentText);
  }

  @Test
  void givenContentLengthIsOver_whenCreated_thenReturnThrowError() {
    // given
    String content = "a".repeat(501);

    // when
    // then
    assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
  }

  @ParameterizedTest
  @ValueSource(strings = {"쀍, 닭, 삵, 긁, 슒"})
  void givenContentLengthIsOverAndKorean_whenCreated_thenReturnThrowError(String koreanWord) {
    // given
    String content = koreanWord.repeat(501);

    // when
    // then
    assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
  }

  @Test
  void givenContentLengthIsUnder_whenCreated_thenThrowError() {
    // given
    String content = "a".repeat(4);

    // when, then
    assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
  }
}