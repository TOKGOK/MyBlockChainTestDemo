package Beans;

import Tools.Sha256;

import java.util.Date;
import java.util.Objects;

public class Block {

    /*
     *
     *  timestamp   时间戳
     *  pre_hash    前一个区块的哈希值
     *  hashCode    当前区块的哈希值
     *  nonce       随机数
     *  transactions    当前区块保存的数据
     */
    private Date timestamp;
    private String pre_hash;
    private String hashCode;
    private long nonce;
    private String transactions;
    private int preZeros;

    // 主函数，用于功能测试
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);

    }

    private void calculate_hash(){
        //  将区块信息拼接后得到区块的hash值
        //  raw_str = self.previous_hash + str(self.timestamp) + json.dumps(self.transactions, ensure_ascii=False, cls=TransactionEncoder) + str(self.nonce)
        String strTemp = "";
        String hashCode;
        int i = 0;
        strTemp = this.pre_hash + this.timestamp + this.transactions + String.valueOf(this.nonce);
        hashCode = Sha256.getSHA256StrJava(strTemp);
        for(i = 0;i < hashCode.length();i++){
            if (hashCode.charAt(i) != '0'){
                break;
            }
        }
        this.preZeros = i;
        this.hashCode = hashCode;
    }

    public Block(Date timestamp, String pre_hash, String transactions) {
        this.timestamp = timestamp;
        this.pre_hash = pre_hash;
        this.transactions = transactions;
        this.nonce = 0;
        this.calculate_hash();
    }

    public void mineBlock(int diff){
        // diff 挖矿难度
        while(this.preZeros < diff){
            this.nonce ++;
            this.calculate_hash();
        }
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getPre_hash() {
        return pre_hash;
    }

    public void setPre_hash(String pre_hash) {
        this.pre_hash = pre_hash;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public String getTransactions() {
        return transactions;
    }

    public void setTransactions(String transactions) {
        this.transactions = transactions;
    }

    public int getPreZeros() {
        return preZeros;
    }

    @Override
    public String toString() {
        return "Block{" +
                "timestamp=" + timestamp +
                ", pre_hash='" + pre_hash + '\'' +
                ", hashCode='" + hashCode + '\'' +
                ", nonce=" + nonce +
                ", transactions='" + transactions + '\'' +
                '}';
    }

}
