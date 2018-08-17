package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class PhoneTest {
    @Test
    public void register(){
        Provider provider = new Provider("BeelineKG");
        Phone phone = new PrepaidPhone("Islam", "+996773247208", provider);
        Phone phone1 = new PrepaidPhone("Islam", "+996773247208", provider);
        assertEquals(phone, phone1);
    }

}