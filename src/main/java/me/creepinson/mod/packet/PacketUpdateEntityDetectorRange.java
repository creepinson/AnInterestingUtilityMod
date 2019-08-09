package me.creepinson.mod.packet;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketUpdateEntityDetectorRange implements IMessage {
	// A default constructor is always required
	public PacketUpdateEntityDetectorRange() {
	}

	public int rangeSliderValue;
	public BlockPos tilePos;

	public PacketUpdateEntityDetectorRange(int rangeSliderValue, BlockPos tilePos) {
		this.rangeSliderValue = rangeSliderValue;
		this.tilePos = tilePos;
	}

	/**
	 *
	 */
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.rangeSliderValue);

		buf.writeInt(tilePos.getX());
		buf.writeInt(tilePos.getY());
		buf.writeInt(tilePos.getZ());

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.rangeSliderValue = buf.readInt();
		
		this.tilePos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
	}
}