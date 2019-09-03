package com.umpisa.exam.server.fetchers;

import com.umpisa.exam.server.utils.StdResponse;

public class BaseDataFetcher {
  public StdResponse success() {
    return new StdResponse(
      200,
      "OK",
      "OK"
    );
  }
}
