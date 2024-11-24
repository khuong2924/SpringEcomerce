package khuong.com.midterm_java.config;



import khuong.com.midterm_java.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
  public static List<Product> cart;

  static {
    cart = new ArrayList<Product>();
  }
}
