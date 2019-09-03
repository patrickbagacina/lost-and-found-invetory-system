package com.umpisa.exam.server.services;

import com.umpisa.exam.server.jpa.JPAUtil;

import java.util.function.*;

public abstract class Service {
  public static <T> T transaction(Supplier<T> closure) {
    return JPAUtil.transaction(closure);
  }

  public void transaction(Runnable closure) {
    JPAUtil.transaction(closure);
  }
}