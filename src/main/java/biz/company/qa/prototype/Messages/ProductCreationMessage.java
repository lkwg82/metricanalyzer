package biz.company.qa.prototype.Messages;

import biz.company.qa.prototype.Product;

public class ProductCreationMessage extends TokenMessageAbstract
{
  private ProductCreationMessage(final String token)
  {
    super(token);
  }

  public static ProductCreationMessage create(final String token, final Product product)
  {
    ProductCreationMessage message = new ProductCreationMessage(token);
    message.setBody(product);
    return message;
  }

  public Product getProduct()
  {
    return getBody(Product.class);
  }

}
