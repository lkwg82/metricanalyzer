package biz.company.qa.prototype.DB;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserDBTest
{

  UserDB db;

  @BeforeMethod
  public void prepare()
  {
    db = new UserDB();
  }

  @Test
  public void addNewUser() throws Exception
  {
    String user = "user";
    String pass = "asda";

    // precondition
    assertThat(db.getUserTable().containsKey(user), is(false));
    // check
    assertThat(db.addNewUser(user, pass), is(true));
    // postcondtion
    assertThat(db.getUserTable().containsKey(user), is(true));
  }

  @Test
  public void checkLoginNotExisting() throws Exception
  {
    String user = "user";
    String pass = "asda";

    // precondition
    assertThat(db.getUserTable().containsKey(user), is(false));

    // check
    assertThat(db.checkLogin(user, pass), is(false));
  }

  @Test
  public void checkLoginExisting() throws Exception
  {
    String user = "user";
    String pass = "asda";

    // precondition
    assertThat(db.addNewUser(user, pass), is(true));

    // check
    assertThat(db.checkLogin(user, pass), is(true));
  }
}
