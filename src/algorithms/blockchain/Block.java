package algorithms.blockchain;

import java.util.Date;

/**
 * 1) Block contains a String hash that will hold our digital signature. The
 * variable previousHash to hold the previous block’s hash andString data to
 * hold our block data.
 * 
 * 2) To generate a digital signature (StringUtil class)
 * 
 * 3) To check the integrity of our blockchain (isChainValid() Boolean method)
 * that will loop through all blocks in the chain and compare the hashes. This
 * method will need to check the hash variable is actually equal to the
 * calculated hash, and the previous block’s hash is equal to the previousHash
 * variable.
 * 
 * 4) Proof of work. The hashcash proof of work system means it takes
 * considerable time and computational power to create new blocks. Hence the
 * attacker would need more computational power than the rest of the peers
 * combined.
 * 
 * 5) Start mining blocks: add an int called nonce to be included in our
 * calculateHash() method, and the much needed mineBlock() method
 * 
 * 6) The mineBlock() method for each new block. The isChainValid() Boolean
 * should also check if each block has a solved ( by mining ) hash.
 * 
 * > Is made up of blocks that store data. 
 * > Has a digital signature that chains your blocks together. 
 * > Requires proof of work mining to validate new blocks.
 * > Can be check to see if data in it is valid and unchanged.
 * 
 * @author wendellopes
 *
 */

public class Block {

	public String hash;
	public String previousHash; 
	private String data; //our data will be a simple message.
	private long timeStamp; //as number of milliseconds since 1/1/1970.
	private int nonce;
	
	//Block Constructor.  
	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		
		this.hash = calculateHash(); //Making sure we do this after we set the other values.
	}
	
	//Calculate new hash based on blocks contents
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				data 
				);
		return calculatedhash;
	}
	
	//Increases nonce value until hash target is reached.
	public void mineBlock(int difficulty) {
		String target = StringUtil.getDificultyString(difficulty); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
}
