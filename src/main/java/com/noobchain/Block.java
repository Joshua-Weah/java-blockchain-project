package com.noobchain;

import java.util.Date;

// Represents a single block in the blockchain.
public class Block {

    public String hash;         // This block's unique fingerprint
    public String previousHash; // The previous block's hash (links the chain)
    public String data;         // Data stored in this block
    public long timeStamp;      // Time of block creation in milliseconds
    public int nonce;           // Number incremented during proof-of-work mining

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    /**
     * Calculates this block's hash based on its contents.
     * Any change to the block's data will produce a different hash.
     */
    public String calculateHash() {
        return StringUtil.applySha256(
            previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data
        );
    }

    /**
     * Proof-of-work mining: repeatedly increments nonce until the hash
     * starts with the required number of leading zeros (the difficulty).
     */
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block mined! Hash: " + hash);
    }
}