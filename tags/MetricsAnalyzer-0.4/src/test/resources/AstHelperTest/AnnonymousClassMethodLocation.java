package AstHelperTest;

public class AnnonymousClassMethodLocation
{
  public void test()
  {
    Context camelContext = new CamelContext();
    camelContext.addRoutes(new RouteBuilder() {
      @Override
      public void configure() throws Exception {
          from(fromEndpoint).to(toEndpoint);
      }
  });
  }
}
