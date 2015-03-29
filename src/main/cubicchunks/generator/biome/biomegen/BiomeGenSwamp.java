/*
 *  This file is part of Cubic Chunks, licensed under the MIT License (MIT).
 *
 *  Copyright (c) 2014 Tall Worlds
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package cubicchunks.generator.biome.biomegen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.World;
import cubicchunks.world.Cube;

public class BiomeGenSwamp extends CubeBiomeGenBase {
	
	private static final String __OBFID = "CL_00000185";
	
	protected BiomeGenSwamp(int par1) {
		super(par1);
		this.theBiomeDecorator.treesPerChunk = 2;
		this.theBiomeDecorator.flowersPerChunk = 1;
		this.theBiomeDecorator.deadBushPerChunk = 1;
		this.theBiomeDecorator.mushroomsPerChunk = 8;
		this.theBiomeDecorator.reedsPerChunk = 10;
		this.theBiomeDecorator.clayPerChunk = 1;
		this.theBiomeDecorator.waterlilyPerChunk = 4;
		this.theBiomeDecorator.sandPerChunk2 = 0;
		this.theBiomeDecorator.sandPerChunk = 0;
		this.theBiomeDecorator.grassPerChunk = 5;
		this.waterColorMultiplier = 14745518;
		this.spawnableMonsterList.add(new CubeBiomeGenBase.SpawnListEntry(EntitySlime.class, 1, 1, 1));
	}
	
	public WorldGenAbstractTree checkSpawnTree(Random p_150567_1_) {
		return this.worldGeneratorSwamp;
	}
	
	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_) {
		double var4 = field_150606_ad.func_151601_a((double)p_150558_1_ * 0.0225D, (double)p_150558_3_ * 0.0225D);
		return var4 < -0.1D ? 5011004 : 6975545;
	}
	
	/**
	 * Provides the basic foliage color based on the biome temperature and rainfall
	 */
	public int getBiomeFoliageColor(int p_150571_1_, int p_150571_2_, int p_150571_3_) {
		return 6975545;
	}
	
	public String spawnFlower(Random p_150572_1_, int p_150572_2_, int p_150572_3_, int p_150572_4_) {
		return BlockFlower.field_149859_a[1];
	}
	
	public void replaceBlocks_pre(World world, Random rand, Cube cube, int xAbs, int yAbs, int zAbs, double val) {
		double var9 = field_150606_ad.func_151601_a((double)xAbs * 0.25D, (double)yAbs * 0.25D);
		
		if (var9 > 0.0D) {
			int xRel = xAbs & 15;
			int yRel = yAbs & 15;
			int zRel = zAbs & 15;
			// int height = blocks.length / 256;
			
			for (int y = 16; y >= 0; --y) {
				int loc = (zRel * 16 + xRel) * 16 + yRel;
				
				Block block = cube.getBlock(xRel, yRel, zRel);
				
				if (block == null || block.getMaterial() != Material.air) {
					if (yAbs == 62 && block != Blocks.water) {
						cube.setBlockForGeneration(xRel, yRel, zRel, Blocks.water);
						
						if (var9 < 0.12D) {
							cube.setBlockForGeneration(xRel, yRel + 1, zRel, Blocks.waterlily); // this should always place the lily at a height of 63,
							// and not go into the next cube up which would be bad.
						}
					}
					
					break;
				}
			}
		}
		
		this.replaceBlocks(world, rand, cube, xAbs, yAbs, zAbs, val);
	}
}
