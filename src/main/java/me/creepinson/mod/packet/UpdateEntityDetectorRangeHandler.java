package me.creepinson.mod.packet;

import me.creepinson.mod.tile.TileEntityEntityDetector;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

// The params of the IMessageHandler are <REQ, REPLY>
// This means that the first param is the packet you are receiving, and the second is the packet you are returning.
// The returned packet can be used as a "response" from a sent packet.
public class UpdateEntityDetectorRangeHandler implements IMessageHandler<PacketUpdateEntityDetectorRange, IMessage> {
  // Do note that the default constructor is required, but implicitly defined in this case

  @Override public IMessage onMessage(PacketUpdateEntityDetectorRange message, MessageContext ctx) {
    // This is the player the packet was sent to the server from
    WorldServer world = ctx.getServerHandler().player.getServerWorld();
    TileEntity t = world.getTileEntity(message.tilePos);
    if(t instanceof TileEntityEntityDetector) {
    	TileEntityEntityDetector tile = (TileEntityEntityDetector) t;
    	tile.radiusRange = message.rangeSliderValue;
    	//world.setTileEntity(message.tilePos, tile);
    }
    // No response packet
    return null;
  }
}