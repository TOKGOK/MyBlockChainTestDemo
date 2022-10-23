package Beans;

import java.util.ArrayList;
import java.util.Date;

public class BlockChain {
    private final int difficult;
    private final double miningReward;
    private static ArrayList<Block> blockChain = new ArrayList<Block>();
    private static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private static ArrayList<Transaction> pendingTrans = new ArrayList<Transaction>();

    public BlockChain() {
        create_Genesis_Block();
        this.difficult = 5;
        this.miningReward = 100;
    }

    //  生成创世区块
    private void create_Genesis_Block(){
        blockChain.add(new Block(new Date(2018,6,11),"",new Transaction()));
    }

    //  获取链上最后一个区块
    public Block getLastBlock(){
        return blockChain.get(blockChain.size() - 1);
    }

    //  添加区块
    public void addBlock(){
        Block block = new Block(new Date(),this.getLastBlock().getHashCode(),new Transaction());
        block.mineBlock(this.difficult);
        blockChain.add(block);
    }
    public void addBlock(Transaction transaction){
        Block block = new Block(new Date(),this.getLastBlock().getHashCode(),transaction);
        block.mineBlock(this.difficult);
        blockChain.add(block);
    }

    //  添加交易
    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
        pendingTrans.add(transaction);
    }

    //  挖取待处理交易
    public void minePendingTransaction(Address miningRewardAddress){
        Block block = new Block(new Date(),this.getLastBlock().getHashCode(),new Transaction());
        block.mineBlock(this.difficult);
        blockChain.add(block);
        pendingTrans.add(new Transaction());
    }

    //  获取钱包余额
    public double getBalanceOfAddress(Address address){
        double balance = 0;
        /*for (Transaction pendingTran : pendingTrans) {
            if (pendingTran.getAddressA().equals(address)) {
                balance -= pendingTran.getTranCoin();
            }
            if (pendingTran.getAddressB().equals(address)) {
                balance += pendingTran.getTranCoin();
            }
        }*/
        return balance;
    }

    //  检测区块链完整性
    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        //loop through blockchain to check hashes:
        for(int i=1; i < blockChain.size(); i++) {
            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.getHashCode().equals(currentBlock.calculate_hash_out()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.getHashCode().equals(currentBlock.getPre_hash()) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }
}
