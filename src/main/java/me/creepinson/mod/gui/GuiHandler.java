/**
 * @author Creepinson
 * 
 */
package me.creepinson.mod.gui;

import me.creepinson.mod.tile.TileEntityEntityDetector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity te = world.getTileEntity(pos);

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		if (ID == 0) {
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof TileEntityEntityDetector) {
				TileEntityEntityDetector tile = (TileEntityEntityDetector) te;
				return new GuiEntityDetector(tile);
			}
		}
		return null;
	}
}