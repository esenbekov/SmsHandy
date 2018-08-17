package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProviderTest {

    @Test
    public void getName() {
        Provider provider = new Provider("O!");
        assertEquals("O!", provider.getName());
    }

    @Test
    public void register() {
        Provider provider = new Provider("O!");
        Phone phone = new PrepaidPhone("Ислам", "+996702247208", provider);
        provider.register(phone);
        assertEquals(provider.getAbonentsList().get(phone.getNumber()), phone);
    }

    @Test
    public void equalProvider(){
        Provider provider = new Provider("O!");
        Provider provider1 = new Provider("O!");
        assertEquals(provider, provider1);
    }
}