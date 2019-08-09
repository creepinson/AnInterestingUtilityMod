package me.creepinson.mod.config;

import java.util.HashMap;
import java.util.Map;

import me.creepinson.mod.util.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Reference.MODID, name = Reference.NAME)
public class ConfigHandler {
	@Config.Comment({ "Here you can add a mob spawning rule to disable mob spawning",
			"using the format 'minecraft:pig', ", "being the identifier for the mob you want to use. ", "Just add a comma on the end of the line before the line you are adding. ", "Then copy and paste the line below the previous one, and change 'minecraft:pig' to the RESOURCELOCATION/identifier of your mob. ", "Just make sure that the last line in the mob spawning control section does not have a comma!" })
	public static final Map<String, Boolean> mobSpawningControl = new HashMap<>();

	static {
		mobSpawningControl.put("minecraft:pig", true);
	}
	
	@Mod.EventBusSubscriber(modid = Reference.MODID)
	private static class EventHandler {

		/**
		 * Inject the new values and save to the config file when the config has been
		 * changed from the GUI.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(Reference.MODID)) {
				ConfigManager.sync(Reference.MODID, Config.Type.INSTANCE);
			}
		}
	}
}