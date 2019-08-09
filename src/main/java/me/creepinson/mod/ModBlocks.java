package me.creepinson.mod;

import java.util.ArrayList;
import java.util.List;

import me.creepinson.mod.block.BlockEntityDetector;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class ModBlocks {
	
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block ENTITY_DETECTOR = new BlockEntityDetector("entity_detector", Material.IRON, CreativeTabs.REDSTONE);

}
