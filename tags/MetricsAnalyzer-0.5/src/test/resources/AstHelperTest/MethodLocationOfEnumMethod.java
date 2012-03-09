package AstHelperTest;

public enum MethodLocationOfEnumMethod
{
  A()
  {
    @Override
    private void method()
    {
    };
  };

  public abstract void method();

  private void test()
  {
  };
  
  String name = "x";
}
