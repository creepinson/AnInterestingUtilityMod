/**
 * @author Creepinson
 * 
 */
package me.creepinson.mod.item.utility;

import me.creepinson.mod.item.ItemBase;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;

public class ItemEntityIdentifier extends ItemBase {

	/**
	 * @param name
	 * @param mat
	 * @param tab
	 */
	public ItemEntityIdentifier(String name, CreativeTabs tab) {
		super(name, tab);
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target,
			EnumHand hand) {
		
		if(!player.world.isRemote && EntityList.getKey(target)  != null) {
			player.sendMessage(new TextComponentString("Entity Resource Location Identifier: " + EntityList.getKey(target).toString()));
		}
		
		return true;
	}

}
