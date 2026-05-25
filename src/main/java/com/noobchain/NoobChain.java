package com.noobchain;

import java.util.ArrayList;

// Main class that creates and validates the blockchain.
public class NoobChain {

    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 3;

    public static void main(String[] args) {
        System.out.println("Creating and mining blocks...\n");

        // Genesis block — the first block, has no previous hash
        addBlock(new Block("Genesis Block", "0"));

        addBlock(new Block("Block 1 — Hello blockchain!", 
            blockchain.get(blockchain.size() - 1).hash));

        addBlock(new Block("Block 2 — Sending 50 coins", 
            blockchain.get(blockchain.size() - 1).hash));

        addBlock(new Block("Block 3 — Sending 25 coins", 
            blockchain.get(blockchain.size() - 1).hash));

        System.out.println("\nBlockchain valid: " + isChainValid());
    }

    /**
     * Mines a block and adds it to the chain.
     */
    public static void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }

    /**
     * Validates the entire blockchain by checking:
     * 1. Each block's stored hash matches its recalculated hash
     * 2. Each block's previousHash matches the actual previous block's hash
     */
    public static boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock  = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            // Check the block's hash is still valid
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Hash mismatch on block " + i);
                return false;
            }

            // Check the chain is properly linked
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Chain broken at block " + i);
                return false;
            }

            // Check proof of work
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("Block " + i + " has not been mined");
                return false;
            }
        }
        return true;
    }
}