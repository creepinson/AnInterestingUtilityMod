package me.creepinson.mod.block;

import java.util.Random;

import me.creepinson.mod.Main;
import me.creepinson.mod.tile.TileEntityEntityDetector;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author Creepinson
 * 
 */

public class BlockEntityDetector extends BlockBase implements ITileEntityProvider {

	public static final PropertyBool EMIT = PropertyBool.create("emit");

	/**
	 * @param name
	 * @param mat
	 * @param tab
	 */
	public BlockEntityDetector(String name, Material mat, CreativeTabs tab) {
		super(name, mat, tab);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityEntityDetector();
	}

	@Override
	public boolean canProvidePower(IBlockState state) {
		return true;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player.isSneaking() && player.world.isRemote) {
			player.openGui(Main.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
			return true;
		}
		return false;
	}

	@Override
	public boolean shouldCheckWeakPower(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te,
			ItemStack stack) {
		worldIn.removeTileEntity(pos);
		super.harvestBlock(worldIn, player, pos, state, te, stack);
	}

	@Override
	public int getStrongPower(IBlockState state, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		if (state.getValue(EMIT)) {
			System.out.println("TEST");
			return 15;
		} else {
			return 0;
		}
	}

	@Override
	public int getWeakPower(IBlockState state, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {

		if (state.getValue(EMIT)) {
			return 15;
		} else {
			return 0;
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { EMIT });
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(EMIT) ? 1 : 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(EMIT, meta == 1);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityEntityDetector();
	}

}
