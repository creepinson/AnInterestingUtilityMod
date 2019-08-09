package me.creepinson.mod.util;

import me.creepinson.mod.entity.EntityTrojanHorse;
import me.creepinson.mod.entity.renderer.RenderTrojanHorse;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
	
	@Override
	public void registerEntityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityTrojanHorse.class, new IRenderFactory<EntityTrojanHorse>()
		{
			@Override
			public Render<? super EntityTrojanHorse> createRenderFor(RenderManager manager) 
			{
				return new RenderTrojanHorse(manager);
			}
		});
	}
	
}
