package models;

import app.Main;

import java.util.*;

/**
 * created by Islam
 */

public class Provider {
    private Main main;
    private String name;
    private Map<String, Phone> abonentsList;
    private static ArrayList<Provider> providersList = new ArrayList<>();

    public Provider(String name) {
        this.name = name;
        this.abonentsList = new HashMap<>();
        providersList.add(this);
    }

    public Provider findProviderFor(String number){
        for (Provider provider: providersList){
            if (provider.getAbonentsList().containsKey(number)){
                return provider;
            }
        }
        return null;
    }

    public boolean sendMessage(Message message){
        Provider toProvider = this.findProviderFor(message.getTo());
        if (toProvider != null){
            Phone toPhone = toProvider.getAbonentsList().get(message.getTo());
            toPhone.getReceivedMessagesList().add(message);
            Phone fromPhone = this.getAbonentsList().get(message.getFrom());
            fromPhone.getSendMessagesList().add(message);
            fromPhone.payForMessage();
            return true;
        }else
            return false;
    }

    public String getName() {
         return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Phone> getAbonentsList() {
        return abonentsList;
    }

    public ArrayList<Provider> getProvidersList() {
        return providersList;
    }

    public void register(Phone phone){
        abonentsList.put(phone.getNumber(), phone);
    }

    public void register(Provider provider){
        providersList.add(provider);
    }

    public void unregister(Phone phone){
        abonentsList.remove(phone.getNumber(), phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return (name != null && name.equals(provider.getName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime*result+name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "name='" + name + '\'' +
                ", abonentsList=" + abonentsList +
                '}';
    }
}
