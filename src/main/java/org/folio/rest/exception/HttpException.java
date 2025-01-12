package org.folio.rest.exception;

import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
import org.folio.rest.jaxrs.model.Error;
import org.folio.rest.jaxrs.model.Errors;
import org.folio.rest.util.ErrorCodes;

public class HttpException extends RuntimeException {
  private static final long serialVersionUID = 8109197948434861504L;

  private final int code;
  private final transient Errors errors;

  public HttpException(int code, String message) {
    super(StringUtils.isNotEmpty(message) ? message : ErrorCodes.GENERIC_ERROR_CODE.getDescription());
    this.code = code;
    this.errors = new Errors()
      .withErrors(Collections.singletonList(new Error().withCode(ErrorCodes.GENERIC_ERROR_CODE.getCode()).withMessage(message)))
      .withTotalRecords(1);
  }

  public HttpException(int code, ErrorCodes errCodes) {
    super(errCodes.getDescription());
    this.errors = new Errors()
      .withErrors(Collections.singletonList(new Error().withCode(errCodes.getCode()).withMessage(errCodes.getDescription())))
      .withTotalRecords(1);
    this.code = code;
  }

  public HttpException(int code, Error error) {
    this.code = code;
    this.errors = new Errors()
      .withErrors(Collections.singletonList(error))
      .withTotalRecords(1);
  }

  public int getCode() {
    return code;
  }

  public Errors getErrors() {
    return errors;
  }
}
