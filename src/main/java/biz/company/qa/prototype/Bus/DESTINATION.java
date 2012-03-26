package biz.company.qa.prototype.Bus;

public enum DESTINATION
{
  API_SYNC
  {
    @Override
    String getURI()
    {
      return "direct:api";
    }

    @Override
    boolean isSync()
    {
      return true;
    }
  },
  DB_SYNC
  {
    @Override
    String getURI()
    {
      return "direct:db";
    }

    @Override
    boolean isSync()
    {
      return true;
    }
  };

  abstract String getURI();

  abstract boolean isSync();
}
