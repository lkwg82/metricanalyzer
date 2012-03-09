package AstHelperTest;

public class ArrayWithCanonicalType
{
  protected void assertClients(int expected) throws Exception {
    org.apache.activemq.broker.Connection[] clients = broker.getBroker().getClients();
    int actual = clients.length;

    assertEquals("Number of clients", expected, actual);
}
}
