package me.creepinson.mod;

import java.util.ArrayList;
import java.util.List;

import me.creepinson.mod.block.BlockBase;
import me.creepinson.mod.item.ItemBase;
import me.creepinson.mod.item.utility.ItemEntityIdentifier;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModItems {
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item ENTITY_IDENTIFIER = new ItemEntityIdentifier("entity_identifier", CreativeTabs.MISC);

}
