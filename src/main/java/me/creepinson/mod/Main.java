package me.creepinson.mod;

import org.apache.logging.log4j.Logger;

import me.creepinson.mod.config.ConfigHandler;
import me.creepinson.mod.entity.ModEntities;
import me.creepinson.mod.gui.GuiHandler;
import me.creepinson.mod.packet.EntityDetectorSyncHandler;
import me.creepinson.mod.packet.PacketEntityDetectorSync;
import me.creepinson.mod.packet.PacketUpdateEntityDetectorRange;
import me.creepinson.mod.packet.PacketUpdateEntityWhitelist;
import me.creepinson.mod.packet.UpdateEntityDetectorRangeHandler;
import me.creepinson.mod.packet.UpdateEntityWhitelistHandler;
import me.creepinson.mod.tile.TileEntityEntityDetector;
import me.creepinson.mod.util.CommonProxy;
import me.creepinson.mod.util.Reference;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main {

	private static Logger logger;

	@SidedProxy(clientSide = "me.creepinson.mod.util.ClientProxy", serverSide = "me.creepinson.mod.util.CommonProxy")
	public static CommonProxy proxy;

	@Mod.Instance
	public static Main instance;

	public static final SimpleNetworkWrapper PACKET_INSTANCE = NetworkRegistry.INSTANCE
			.newSimpleChannel(Reference.MODID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();

		// GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);

		ModEntities.registerEntities();
		proxy.registerEntityRenderers();
	}

	@SubscribeEvent
	public static void livingSpawn(EntityJoinWorldEvent event) {
		if (!(event.getEntity() instanceof EntityPlayer)) {
			if (EntityList.getKey(event.getEntity()) != null) {
				ResourceLocation eloc = EntityList.getKey(event.getEntity());
				if (ConfigHandler.mobSpawningControl.containsKey(eloc.toString())) {
					if (!ConfigHandler.mobSpawningControl.get(eloc.toString())) {
						event.setCanceled(true);
					}
				}
			}
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		// Config

		// --

		GameRegistry.registerTileEntity(TileEntityEntityDetector.class,
				new ResourceLocation(Reference.MODID, "tile_entity_detector"));

		// Packets & Guis
		PACKET_INSTANCE.registerMessage(UpdateEntityWhitelistHandler.class, PacketUpdateEntityWhitelist.class, 0,
				Side.SERVER);
		PACKET_INSTANCE.registerMessage(UpdateEntityDetectorRangeHandler.class, PacketUpdateEntityDetectorRange.class,
				1, Side.SERVER);

		PACKET_INSTANCE.registerMessage(EntityDetectorSyncHandler.class, PacketEntityDetectorSync.class,
				2, Side.CLIENT);
		PACKET_INSTANCE.registerMessage(EntityDetectorSyncHandler.class, PacketEntityDetectorSync.class,
				3, Side.SERVER);

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		// --
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
