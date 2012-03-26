package biz.company.qa.prototype.DB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import biz.company.qa.prototype.Product;

public class ProductDB
{
  private final Map<String, List<Product>> productTable = new HashMap<String, List<Product>>();

  public void addProduct(final String user, final Product product)
  {
    if (!productTable.containsKey(user))
    {
      productTable.put(user, new ArrayList<Product>());
    }
    productTable.get(user).add(product);
  }
}
