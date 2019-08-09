package me.creepinson.mod.block;

import me.creepinson.mod.Main;
import me.creepinson.mod.ModBlocks;
import me.creepinson.mod.ModItems;
import me.creepinson.mod.util.Reference;
import me.creepinson.mod.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel {

	public BlockBase(String name, Material mat) {
		this(name, mat, CreativeTabs.BUILDING_BLOCKS);
	}

	public BlockBase(String name, Material mat, CreativeTabs tab) {
		super(mat);
		setUnlocalizedName(name);
		setRegistryName(Reference.MODID, name);
		setCreativeTab(tab);
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModel() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
}
