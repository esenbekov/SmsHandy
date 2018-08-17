package models;

/**
 * created by Islam
 */

public class PrepaidPhone extends Phone{
    private double costForSms = 5;

    public PrepaidPhone(String name, String number, Provider provider) {
        super(name, number, provider);
    }


    @Override
    public boolean canSendMessage() {
        if (this.getBalance()- costForSms >= 0){
            return true;
        }else
            return false;
    }

    @Override
    public void payForMessage(){
        this.setBalance(getBalance()-costForSms);
    }


}
