package biz.company.qa.prototype;

import java.awt.Color;
import java.math.BigDecimal;

public class Product
{

  private final BigDecimal price;
  private final Type       type;
  private final SIZE       size;
  private final Color      color;

  public Product(final BigDecimal price, final Type type, final SIZE size, final Color color)
  {
    this.price = price;
    this.type = type;
    this.size = size;
    this.color = color;
  }

  public BigDecimal getPrice()
  {
    return price;
  }

  public Type getType()
  {
    return type;
  }

  public SIZE getSize()
  {
    return size;
  }

  public Color getColor()
  {
    return color;
  }

}
