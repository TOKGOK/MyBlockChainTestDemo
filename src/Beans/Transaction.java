package Beans;

public class Transaction {
    private Address addressA;
    private Address addressB;
    private double tranCoin;

    public Transaction(Address addressA, Address addressB, double tranCoin) {
        this.addressA = addressA;
        this.addressB = addressB;
        this.tranCoin = tranCoin;
    }

    public Address getAddressA() {
        return addressA;
    }

    public void setAddressA(Address addressA) {
        this.addressA = addressA;
    }

    public Address getAddressB() {
        return addressB;
    }

    public void setAddressB(Address addressB) {
        this.addressB = addressB;
    }

    public double getTranCoin() {
        return tranCoin;
    }

    public void setTranCoin(double tranCoin) {
        this.tranCoin = tranCoin;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "addressA='" + addressA.toString() + '\'' +
                ", addressB='" + addressB.toString() + '\'' +
                ", tranCoin=" + tranCoin +
                '}';
    }
}
