package Beans;

import java.util.Date;

public class BlockChain {
    private int difficulity;
    private double miningReward;
    private Block[] blocks;
    private Transaction[] pendingTran;
    private Transaction transactions[];
    private int chainLen;
    private int transLen;
    private int transStar;
    private int pendTranLen;

    public static void main(String[] args) {
        BlockChain blockChain = new BlockChain();
        Address address1 = new Address("address1");
        Address address2 = new Address("address2");
        Address address3 = new Address("address3");

        blockChain.addTransaction(new Transaction(address1,address2,100));
        blockChain.addTransaction(new Transaction(address2,address1,50));
        blockChain.minePendingTransaction(address3);

        System.out.println(blockChain.getBalanceOfAddress(address1));
        System.out.println(blockChain.getBalanceOfAddress(address2));
        System.out.println(blockChain.getBalanceOfAddress(address3));
        System.out.println("-----------------------------------------------------");

        blockChain.minePendingTransaction(address2);

        System.out.println(blockChain.getBalanceOfAddress(address1));
        System.out.println(blockChain.getBalanceOfAddress(address2));
        System.out.println(blockChain.getBalanceOfAddress(address3));
        System.out.println("-----------------------------------------------------");
        for (int i = 0;i <blockChain.chainLen;i++){
            System.out.println(blockChain.blocks[i].getHashCode());
            System.out.println(blockChain.blocks[i].getPreZeros());
        }
    }

    public BlockChain() {
        this.blocks = new Block[9999];
        this.pendingTran = new Transaction[9999];
        this.transactions = new Transaction[9999];
        create_Genesis_Block();
        this.difficulity = 5;
        this.miningReward = 100;
        this.transLen = 0;
        this.transStar = 0;
        this.pendTranLen = 0;
    }

    //  生成创世区块
    private void create_Genesis_Block(){
        this.blocks[0] = new Block(new Date(2018,6,11),"","");
        this.chainLen = 1;
    }

    //  获取链上最后一个区块
    public Block getLastBlock(){
        return this.blocks[this.chainLen - 1];
    }

    //  添加区块
    public void addBlock(){
        Block block = new Block(new Date(),this.getLastBlock().getHashCode(),"");
        block.mineBlock(this.difficulity);
        this.blocks[this.chainLen] = block;
        this.chainLen ++;
    }

    //  添加交易
    public void addTransaction(Transaction transaction){
        this.transactions[this.transLen++] = transaction;
        this.pendingTran[this.pendTranLen++] = transaction;
    }

    //  挖取待处理交易
    public void minePendingTransaction(Address miningRewardAddress){
        Block block = new Block(new Date(),this.getLastBlock().getHashCode(),"");
        block.mineBlock(this.difficulity);
        this.blocks[this.chainLen++] = block;
        this.pendingTran[this.pendTranLen++] = new Transaction(new Address(""),miningRewardAddress,this.miningReward);
    }

    //  获取钱包余额
    public double getBalanceOfAddress(Address address){
        double balance = 0;
        for (int i = 0;i < this.pendTranLen;i++){
            if (pendingTran[i].getAddressA().equals(address)){
                balance -= pendingTran[i].getTranCoin();
            }
            if (pendingTran[i].getAddressB().equals(address)){
                balance += pendingTran[i].getTranCoin();
            }
        }
        return balance;
    }
}
