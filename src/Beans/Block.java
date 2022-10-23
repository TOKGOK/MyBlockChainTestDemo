package Beans;

import Tools.StringUtil;

import java.util.ArrayList;
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
    private long timestamp;
    private String pre_hash;
    private String hashCode;
    private long nonce;
    private Transaction transactions;

    // 主函数，用于功能测试
    public static void main(String[] args) {
        long date = new Date().getTime();
        System.out.println(date);

    }

    public String calculate_hash_out(){
        //  将区块信息拼接后得到区块的hash值
        //  raw_str = self.previous_hash + str(self.timestamp) + json.dumps(self.transactions, ensure_ascii=False, cls=TransactionEncoder) + str(self.nonce)
        String strTemp = "";
        String hashCode;
        int i = 0;
        strTemp = this.pre_hash + String.valueOf(this.timestamp) + this.transactions.toString() + String.valueOf(this.nonce);
        hashCode = StringUtil.applySha256(strTemp);
        for(i = 0;i < hashCode.length();i++){
            if (hashCode.charAt(i) != '0'){
                break;
            }
        }
        return hashCode;
    }

    private void calculate_hash(){
        //  将区块信息拼接后得到区块的hash值
        //  raw_str = self.previous_hash + str(self.timestamp) + json.dumps(self.transactions, ensure_ascii=False, cls=TransactionEncoder) + str(self.nonce)
        String strTemp = "";
        String hashCode;
        int i = 0;
        strTemp = this.pre_hash + String.valueOf(this.timestamp) + this.transactions.toString() + String.valueOf(this.nonce);
        hashCode = StringUtil.applySha256(strTemp);
        for(i = 0;i < hashCode.length();i++){
            if (hashCode.charAt(i) != '0'){
                break;
            }
        }
        this.hashCode =  hashCode;
    }

    public Block(long timestamp, String pre_hash, Transaction transactions ) {
        this.timestamp = timestamp;
        this.pre_hash = pre_hash;
        this.transactions = transactions;
        this.nonce = 0;
        this.calculate_hash();
    }

    public Block(Date timestamp, String pre_hash, Transaction transactions) {
        this.timestamp = timestamp.getTime();
        this.pre_hash = pre_hash;
        this.transactions = transactions;
        this.nonce = 0;
        this.calculate_hash();
    }

    //  挖矿
    public void mineBlock(int diff){
        // diff 挖矿难度
        String target = new String(new char[diff]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!this.hashCode.substring( 0, diff).equals(target)) {
            nonce ++;
            this.calculate_hash();
        }
        System.out.println("Block Mined!!! : " + this.hashCode);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp.getTime();
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

    public Transaction getTransactions() {
        return transactions;
    }

    public void setTransactions(Transaction transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Block{" +
                "timestamp=" + timestamp +
                ", pre_hash='" + pre_hash + '\'' +
                ", hashCode='" + hashCode + '\'' +
                ", nonce=" + nonce +
                ", transactions='" + transactions.toString() + '\'' +
                '}';
    }

}
