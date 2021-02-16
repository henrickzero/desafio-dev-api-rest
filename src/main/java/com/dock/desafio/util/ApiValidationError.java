package com.dock.desafio.util;

/**
 * @author luiz henrique
 *
 */
class ApiValidationError extends ApiSubError {
   private String object;
   private String field;
   private Object rejectedValue;
   private String message;

   ApiValidationError(String message) {
      this.message = message;
   }
   
   ApiValidationError(String object, String message) {
       this.object = object;
       this.message = message;
   }
}