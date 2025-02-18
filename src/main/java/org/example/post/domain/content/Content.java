package org.example.post.domain.content;

import org.example.post.domain.common.DatetimeInfo;

public abstract class Content {

  String contentText;
  final DatetimeInfo dateTimeInfo;

  public Content(String contentText) {
    checkText(contentText);
    this.contentText = contentText;
    this.dateTimeInfo = new DatetimeInfo();
  }

  public void updateContent(String updateContent) {
    checkText(updateContent);
    this.contentText = updateContent;
    this.dateTimeInfo.updateEditDatetime();
  }

  protected abstract void checkText(String contentText);

  public String getContentText() {
    return contentText;
  }
}
