package com.telran.gardenshop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Status {
  AWAITING_PAYMENT("AWAITING_PAYMENT"),
  PENDING("PENDING"),
  SHIPPED("SHIPPED"),
  DELIVERED("DELIVERED"),
  CANCELLED("CANCELLED");

  private String displayName;

  public static @NotNull Status fromString(String status) {
    try {
      return Status.valueOf(status.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid status value: " + status);
    }
  }
}

