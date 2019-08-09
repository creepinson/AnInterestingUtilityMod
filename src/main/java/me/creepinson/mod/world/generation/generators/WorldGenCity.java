/**
 * @author Creepinson
 * 
 */
package me.creepinson.mod.world.generation.generators;

import java.util.Random;

import me.creepinson.mod.util.Reference;
import me.creepinson.mod.util.interfaces.IStructure;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class WorldGenCity extends WorldGenerator implements IStructure {

	public static final String ROAD_CROSS = "city/road_cross";
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) 
	{
		this.generateStructure(worldIn, position);
		return true;
	}
	
	public static void generateStructure(World world, BlockPos pos)
	{
		MinecraftServer mcServer = world.getMinecraftServer();
		TemplateManager manager = worldServer.getStructureTemplateManager();
		
		genTemplate(ROAD_CROSS, pos, world, mcServer, manager, settings);
		
	}
	
	public static void genTemplate(String structure,  BlockPos pos, World world, MinecraftServer mcServer, TemplateManager manager, PlacementSettings settings) {
		ResourceLocation location = new ResourceLocation(Reference.MODID, structure);
		Template template = manager.get(mcServer, location);
		
		if(template != null)
		{
			IBlockState state = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, state, state, 3);
			template.addBlocksToWorldChunk(world, pos, settings);
		}
	}
}
