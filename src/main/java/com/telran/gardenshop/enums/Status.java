package com.telran.gardenshop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

}

