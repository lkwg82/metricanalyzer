package biz.company.qa.prototype;

import java.awt.Color;
import java.math.BigDecimal;

public class ProductBuilder
{
  static class Builder
  {
    private Color      color = Color.CYAN;
    private SIZE       size  = SIZE.L;
    private Type       type  = Type.HOSE;
    private BigDecimal price = BigDecimal.ZERO;

    public Builder color(final Color color)
    {
      this.color = color;
      return this;
    }

    public Builder type(final Type type)
    {
      this.type = type;
      return this;
    }

    public Builder size(final SIZE size)
    {
      this.size = size;
      return this;
    }

    public Builder price(final BigDecimal price)
    {
      this.price = price;
      return this;
    }

    public Product build()
    {
      return new Product(price, type, size, color);
    }

  }

  public static Builder builder()
  {
    return new Builder();
  }

}
