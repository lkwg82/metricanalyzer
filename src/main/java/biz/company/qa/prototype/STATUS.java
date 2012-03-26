package biz.company.qa.prototype;

public enum STATUS
{
  OK
  {
    @Override
    public int getCode()
    {
      return 200;
    }
  },
  NOT_OK
  {
    @Override
    public int getCode()
    {
      return 400;
    }
  },
  ERROR
  {
    @Override
    public int getCode()
    {
      return -1;
    };
  },
  UNDEF
  {
    @Override
    public int getCode()
    {
      return 0;
    };
  };

  public abstract int getCode();

  public static STATUS parse(final Integer code)
  {
    if (code == null)
    {
      return null;
    }
    else
    {
      for (STATUS s : values())
      {
        if ((s.getCode() != 0) && (code.intValue() == s.getCode()))
        {
          return s;
        }
      }
      return STATUS.UNDEF;
    }
  }
}
