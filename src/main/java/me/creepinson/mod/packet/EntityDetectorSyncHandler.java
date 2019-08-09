package me.creepinson.mod.packet;

import me.creepinson.mod.Main;
import me.creepinson.mod.tile.TileEntityEntityDetector;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

// The params of the IMessageHandler are <REQ, REPLY>
// This means that the first param is the packet you are receiving, and the second is the packet you are returning.
// The returned packet can be used as a "response" from a sent packet.
public class EntityDetectorSyncHandler implements IMessageHandler<PacketEntityDetectorSync, IMessage> {
	// Do note that the default constructor is required, but implicitly defined in
	// this case

	@Override
	public IMessage onMessage(PacketEntityDetectorSync message, MessageContext ctx) {
		// This is the player the packet was sent to the server from
		if (ctx.side == Side.SERVER) {
			WorldServer world = ctx.getServerHandler().player.getServerWorld();
			TileEntity t = world.getTileEntity(message.tilePos);
			if (t instanceof TileEntityEntityDetector) {
				TileEntityEntityDetector tile = (TileEntityEntityDetector) t;

				tile.radiusRange = message.rangeSliderValue;
				tile.entityWhiteList = message.whiteList;

				world.setTileEntity(message.tilePos, tile);

				t = world.getTileEntity(message.tilePos);
				tile = (TileEntityEntityDetector) t;

				Main.PACKET_INSTANCE.sendToAllAround(
						new PacketEntityDetectorSync(tile.radiusRange, tile.entityWhiteList, tile.getPos()),
						new TargetPoint(tile.getWorld().provider.getDimension(), message.tilePos.getX(),
								message.tilePos.getY(), message.tilePos.getZ(), 100));

			}
		} else {
			World world = Minecraft.getMinecraft().player.world;
			TileEntity t = world.getTileEntity(message.tilePos);
			if (t instanceof TileEntityEntityDetector) {
				TileEntityEntityDetector tile = (TileEntityEntityDetector) t;

				tile.radiusRange = message.rangeSliderValue;
				tile.entityWhiteList = message.whiteList;

				world.setTileEntity(message.tilePos, tile);

				t = world.getTileEntity(message.tilePos);
				tile = (TileEntityEntityDetector) t;

			}
		}
		// No response packet
		return null;
	}
}