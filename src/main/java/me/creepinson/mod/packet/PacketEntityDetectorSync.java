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

public class PacketEntityDetectorSync implements IMessage {
	// A default constructor is always required
	public PacketEntityDetectorSync() {
	}

	public List<Class<? extends Entity>> whiteList;
	public BlockPos tilePos;
	public int rangeSliderValue;
	
	public PacketEntityDetectorSync(int rangeSliderValue, List<Class<? extends Entity>> toSend, BlockPos tilePos) {
		this.whiteList = toSend;
		this.rangeSliderValue = rangeSliderValue;
		this.tilePos = tilePos;
	}

	/**
	 *
	 */
	@Override
	public void toBytes(ByteBuf buf) {
		// Writes the int into the buf
		NBTTagCompound tag = new NBTTagCompound();
		NBTTagList l = new NBTTagList();
		for (Class<? extends Entity> ec : whiteList) {
			l.appendTag(new NBTTagString(ec.getName()));
		}
		tag.setTag("list", l);
		ByteBufUtils.writeTag(buf, tag);
		
		
		buf.writeInt(this.rangeSliderValue);

		buf.writeInt(tilePos.getX());
		buf.writeInt(tilePos.getY());
		buf.writeInt(tilePos.getZ());

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		NBTTagList l = tag.getTagList("list", 8);
		whiteList = new ArrayList<Class<? extends Entity>>();
		for(NBTBase sb : l) {
			NBTTagString s = (NBTTagString) sb;
			try {
				whiteList.add((Class<? extends Entity>) Class.forName(s.getString()));
			} catch (ClassNotFoundException e) {
				System.err.println("Encountered an error whilst trying to proccess read data from packet: " + this.getClass().getName().replace("Packet", ""));
				e.printStackTrace();
			}
		}
		
		this.rangeSliderValue = buf.readInt();
		
		this.tilePos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
	}
}