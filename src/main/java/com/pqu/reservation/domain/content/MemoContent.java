package com.pqu.reservation.domain.content;

public class MemoContent extends Content {
  private static final int MIN_POST_LEN = 5;
  private static final int MAX_POST_LEN = 500;

  public MemoContent(String content) {
    super(content);
  }

  @Override
  protected void checkText(String contentText) {
    if (contentText == null || contentText.isEmpty()) {
      throw new IllegalArgumentException();
    }

    if (contentText.length() < MIN_POST_LEN) {
      throw new IllegalArgumentException();
    }

    if (contentText.length() > MAX_POST_LEN) {
      throw new IllegalArgumentException();
    }
  }
}
