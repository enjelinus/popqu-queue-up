package com.pqu.reservation.domain.content;

public abstract class Content {
  public String contentText;

  protected Content(String contentText) {
    checkText(contentText);
    this.contentText = contentText;
  }

  public void updateContent(String updateContent) {
    checkText(updateContent);
    this.contentText = updateContent;
  }
  protected abstract void checkText(String contentText);

  public String getContentText() {
    return contentText;
  }
}
