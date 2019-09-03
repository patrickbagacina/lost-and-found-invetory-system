package com.umpisa.exam.server.models;

import com.umpisa.exam.server.CategoryNotFoundException;

public enum Category {
  ELECTRONICS("Electronics");

  private final String value;
  Category(String value) { this.value = value; }
  public String getText() {
    return this.value;
  }

  public static Category fromString(String text) {
    for (Category t: Category.values()) {
      if(t.value.equalsIgnoreCase(text))
        return t;
    }

    throw new CategoryNotFoundException();
  }
}
