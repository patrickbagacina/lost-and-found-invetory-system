package com.umpisa.exam.server;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InvalidCredentialsException extends RuntimeException implements GraphQLError {
  private final String errorMessage = "Invalid credentials!";
  public static final int STATUS = 400;

  @Override
  public List<SourceLocation> getLocations() {
    return null;
  }

  @Override
  public ErrorType getErrorType() {
    return null;
  }

  @Override
  public List<Object> getPath() {
    return null;
  }

  @Override
  public Map<String, Object> toSpecification() {
    return null;
  }

  @Override
  public Map<String, Object> getExtensions() {
    Map<String, Object> customAttributes = new LinkedHashMap<>();

    customAttributes.put("errorMessage", this.errorMessage);
    customAttributes.put("status", STATUS);

    return customAttributes;
  }
}
