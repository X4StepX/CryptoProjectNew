package com.example.eduard.cryptoprojectnew.libraryChain;

import javafx.util.Pair;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by joerimmer on 2017-03-06.
 */
public class Blockchain
{
    private ArrayList<Pair<Block, BigInteger>> Chain;

    public Blockchain()
    {
        Chain = new ArrayList<Pair<Block, BigInteger>>(); // block-signature pairs
    }

    public Block GetCurrentBlock()
    {
        return Chain.get(Chain.size()-1).getKey();
    }

    public ArrayList<Pair<Block, BigInteger>> ReadChain()
    {
        return (ArrayList<Pair<Block,BigInteger>>) Chain.clone();
    }

    public void AddTransaction(Transaction tx, RSA rsa)
    {
        // if chain is empty, add a fresh block
        if(Chain.isEmpty())
        {
            Chain.add(new Pair<>(new Block(null), null));
        }

        Block block = GetCurrentBlock();

        // check if we need a new block
        if(block.Length() >= 50)
        {
            BigInteger signature = rsa.sign(block);
            Pair<Block, BigInteger> signedBlock = new Pair<Block, BigInteger>(block, signature);

            Chain.remove(block);
            Chain.add(signedBlock);
            Chain.add(new Pair<Block, BigInteger>(new Block(signature), null));

            block = GetCurrentBlock();
        }

        block.AddTransaction(tx, rsa);
    }
}
