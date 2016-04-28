package rtg.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class TreeRTGCocosNucifera extends TreeRTG
{
	private static int leavesLength = 133;
	private static int[] leaves = new int[]{
		1, 0, 0,
		2, 0, 0,
		3, -1, 0,
		3, -2, 0,
		-1, 0, 0,
		-2, 0, 0,
		-3, -1, 0,
		-3, -2, 0,
		0, 0, 1,
		0, 0, 2,
		0, -1, 3,
		0, -2, 3,	
		0, 0, -1,
		0, 0, -2,
		0, -1, -3,
		0, -2, -3,	
		0, 1, 0,
		1, 1, 1,
		-1, 1, -1,
		-1, 1, 1,
		1, 1, -1,
		1, 2, 0,
		-1, 2, 0,
		0, 2, 1, 
		0, 2, -1,
		2, 3, 0,
		3, 3, 0,
		4, 2, 0, 
		-2, 3, 0,
		-3, 3, 0,
		-4, 2, 0, 
		0, 3, 2,
		0, 3, 3,
		0, 2, 4, 
		0, 3, -2,
		0, 3, -3,
		0, 2, -4, 
		2, 2, -2,
		-2, 2, 2,
		-2, 2, -2, 
		2, 2, 2,
		3, 2, -3,
		-3, 2, 3,
		-3, 2, -3, 
		3, 2, 3
	};

	private static int cocoasLength = 16;
	private static int[] cocoas = new int[]{
		2, 0, -2, 1,
		1, 1, -2, 0,
		0, 0, -2, -1,
		3, -1, -2, 0
	};
    
    public TreeRTGCocosNucifera()
    {
    	super();
    	
    	this.logBlock = Blocks.log;
    	this.logMeta = (byte)3;
    	this.leavesBlock = Blocks.leaves;
    	this.leavesMeta = (byte)3;
    	this.trunkSize = 8;
    	this.crownSize = 7;
    }
	
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) 
	{
    	Block b = world.getBlock(x, y - 1, z);
    	
    	if(b != Blocks.grass && b != Blocks.dirt && b != Blocks.sand)
    	{
    		return false;
    	}
    	
    	double horDir = getRandomDir(rand);
    	float verDir = 0.3f + rand.nextFloat() * 0.4f;
    	float length = (float)(this.trunkSize + this.crownSize);
    	float posX = (float)x;
    	float posY = (float)y;
    	float posZ = (float)z;
    	float c = 0f;
    	float loss = 0f;
    	
		if(verDir < 0f)
		{
			verDir = -verDir;
		}

		float velY = 1f - verDir;
		
		if(verDir > 1f)
		{
			verDir = 1f - (verDir - 1f);
		}
		
		float velX = (float)Math.cos(horDir * Math.PI / 180D) * verDir;
		float velZ = (float)Math.sin(horDir * Math.PI / 180D) * verDir;
		
		while(c < length)
		{
			world.setBlock((int)posX, (int)posY, (int)posZ, this.logBlock, this.logMeta + 12, 2);
			
			if(c < length - 3)
			{
				loss = Math.abs(velX) + Math.abs(velZ);
				posX += velX *= 0.9f;
				posZ += velZ *= 0.9f;
				loss = loss - (Math.abs(velX) + Math.abs(velZ));
				posY += velY += loss;
			}
			else
			{
				posY += velY;
			}
			
			c += 1f;
		}
		
		x = (int)posX;
		y = (int)posY - 1;
		z = (int)posZ;
		
    	for(int j = 0; j < leavesLength; j+=3)
    	{
    		world.setBlock(x + leaves[j], y + leaves[j + 1], z + leaves[j + 2], this.leavesBlock, this.leavesMeta, 2);
    	}
    	
    	for(int k = 0; k < cocoasLength; k+=4)
    	{
    		if(rand.nextInt(20) == 0)
    		{
    			world.setBlock(x + cocoas[k + 1], y + cocoas[k + 2], z + cocoas[k + 3], Blocks.cocoa, cocoas[k + 0] + 8, 2);
    		}
    	}
    	
		return true;
	}
	
	public double getRandomDir(Random rand)
	{
		switch(rand.nextInt(4))
		{
			case 0: return -180D;
			case 1: return -90D;
			case 2: return 0D;
			case 3: return 90D;
		}
		return 0D;
	}
}
