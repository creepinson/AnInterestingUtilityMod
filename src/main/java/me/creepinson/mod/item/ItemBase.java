package me.creepinson.mod.item;

import me.creepinson.mod.Main;
import me.creepinson.mod.ModItems;
import me.creepinson.mod.util.ClientProxy;
import me.creepinson.mod.util.interfaces.IHasModel;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {
	
	public ItemBase(String name, Material mat) {
		this(name, mat, CreativeTabs.BUILDING_BLOCKS);
		
		
		ModItems.ITEMS.add(this);
	}

	public ItemBase(String name, Material mat, CreativeTabs tab) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
	}

	@Override
	public void registerModel() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

	
	
}
