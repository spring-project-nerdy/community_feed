package org.example.post.domain.content;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CommentContentTest {

  @Test
  void givenContentLengthIsOk_whenCreateCommentContent_thenReturnTextContext() {
    // given
    String contentText = "this is a tes content";

    // when
    CommentContent content = new CommentContent(contentText);

    // then
    assertEquals(contentText, content.getContentText());
  }

  @Test
  void givenContentLengthIsOver_whenCreateCommentContent_thenThrowError() {
    // given
    String content = "a".repeat(101);

    // when, then
    assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
  }

  @ParameterizedTest
  @ValueSource(strings = {"쀍", "닭", "굵"})
  void givenContentLengthIsOverAndKorean_whenCreateCommentContent_thenThrowError(String koreanContent) {
    // given
    String content = koreanContent.repeat(101);

    // when, then
    assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
  }

  @ParameterizedTest
  @NullAndEmptySource
  void givenContentLengthIsEmptyAndNull_whenCreateCommentContent_thenThrowError(String content) {
    assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
  }
}