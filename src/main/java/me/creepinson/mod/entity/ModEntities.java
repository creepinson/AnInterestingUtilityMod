/**
 * @author Creepinson
 * 
 */
package me.creepinson.mod.entity;

import me.creepinson.mod.Main;
import me.creepinson.mod.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {

	private static int id;

	public static void registerEntities()
	{
		registerEntity("trojan_horse", EntityTrojanHorse.class, id++, 50, 11437146, 000000);
	}
	
	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id, Main.instance, range, 1, true, color1, color2);
	}
	
}
