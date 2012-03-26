package biz.company.qa.infrastrastructure.designer;

import biz.company.qa.infrastrastructure.api.CompanyAPI;
import biz.company.qa.infrastrastructure.api.CompanyAPIFactory;
import biz.company.qa.infrastrastructure.api.CompanyAPIHelper;
import biz.company.qa.pageobjects.CONFIG_KEYS;
import biz.company.qa.pageobjects.MigrationPage;
import biz.company.qa.pageobjects.PageAbstract;
import biz.company.qa.pageobjects.SeleniumContext;
import biz.company.qa.pageobjects.utils.ICondition;
import biz.company.api.ProductTypeDTO;
import biz.company.api.ProductTypeStockStateDTO;

/**
 * see {@link https://spaces.companomat.net/display/PROJ/ConfOmat+External+API+Specification} for more information on
 * the flash api
 * 
 
 */
public class CNG extends Designer {

  private final CompanyAPIHelper apiHelper;
  private final SeleniumContext context;

  public CNG(final SeleniumContext context, final String flashObjectId) {
    super(context.getSelenium(), flashObjectId);

    this.context = context;
    CompanyAPI api = CompanyAPIFactory.createInstance(context.getConfiguration(),
        CONFIG_KEYS.STANDARD_SHOP_ID);
    apiHelper = new CompanyAPIHelper(api);
  }

  @Override
  public boolean isUIStarted() {
    return Boolean.valueOf(call("isLoaded"));
  }

  @Override
  public int getProductTypeID() {
    return Integer.valueOf(callAsync("getProductTypeId"));
  }

  @Override
  public int getProductTypeColorID() {
    throw new RuntimeException("Not yet implemented");
  }

  @Override
  public int getProductTypeSizeID() {
    throw new RuntimeException("Not yet implemented");
  }

  @Override
  public int getArticleQuantity() {
    throw new RuntimeException("Not yet implemented");
  }

  @Override
  public void setArticleQuantity(final int quantity) {
    throw new RuntimeException("Not yet implemented");
  }

  @Override
  public void setDefaultProductTypeSize() {
    int productTypeId = getProductTypeID();

    ProductTypeDTO productType = apiHelper.findProductTypeById(productTypeId);
    ProductTypeStockStateDTO stockstate = apiHelper.findFirstAvailableStockstate(productType);

    call("setSizeId", stockstate.getSize().getId());
  }

  @Override
  public void sendArticleToServer() {

    final MiniBasket minibasket = new MiniBasket(new MigrationPage(context));
    final int count = minibasket.getArticleCount();
    call("startAddToBasket");

    context.getSeleniumHelper().waitFor().specialConditionElseTimeout(new ICondition() {

      @Override
      public String getErrorMessage() {
        return "timeout: no increased article count";
      }

      @Override
      public boolean check() {
        return minibasket.getArticleCount() > count;
      }
    });

    call("gotoCheckout");
  }

  @Override
  public void createProduct() {
    throw new RuntimeException("Not yet implemented");
  }

  class MiniBasket extends PageAbstract{

    public MiniBasket(final PageAbstract page)
    {
      super(page);
    }

    public int getArticleCount()
    {
      return 0;
    }

    @Override
    public void validate()
    {
      // TODO Auto-generated method stub
    }

  }
}
