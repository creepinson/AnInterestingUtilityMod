package me.creepinson.mod.item;

import me.creepinson.mod.Main;
import me.creepinson.mod.ModItems;
import me.creepinson.mod.util.ClientProxy;
import me.creepinson.mod.util.Reference;
import me.creepinson.mod.util.interfaces.IHasModel;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {
	
	public ItemBase(String name) {
		this(name, CreativeTabs.BUILDING_BLOCKS);
		
	}

	public ItemBase(String name, CreativeTabs tab) {
		setUnlocalizedName(name);
		setRegistryName(Reference.MODID, name);
		setCreativeTab(tab);
		
		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModel() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

	
	
}
