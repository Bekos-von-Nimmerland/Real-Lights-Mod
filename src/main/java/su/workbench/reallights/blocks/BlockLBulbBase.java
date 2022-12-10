package su.workbench.reallights.blocks;

import java.util.Random;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import su.workbench.reallights.util.handlers.ConfigHandler;
import su.workbench.reallights.util.handlers.SoundsHandler;

public class BlockLBulbBase extends BlockBase 
{
	public static final PropertyDirection FACING = BlockDirectional.FACING;
	protected final boolean isOn;
	public BlockLBulbBase(String name, boolean isOn) 
	{
		super(name, Material.REDSTONE_LIGHT);
		this.isOn = isOn;
		setSoundType(SoundType.GLASS);
		setHardness(0.5F);
		setResistance(5F);
		setLightOpacity(0);
		setCreativeTab(CreativeTabs.REDSTONE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.EAST));
	}
		@Override
		public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
			return ConfigHandler.CAN_PASS_THROUGH;
		}
		@Override
		public boolean isFullCube(IBlockState state) {
			return false;
		}
		@Override
		public boolean isOpaqueCube(IBlockState state) {
			return false;
		}
		@Override
		protected BlockStateContainer createBlockState() {
			return new BlockStateContainer(this, new IProperty[]{FACING});
		}
		@Override
		public IBlockState withRotation(IBlockState state, Rotation rot) {
			return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
		}
		@Override
		public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
			return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
		}
		@Override
		public IBlockState getStateFromMeta(int meta) {
			return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
		}
		@Override
		public int getMetaFromState(IBlockState state) {
			return ((EnumFacing)state.getValue(FACING)).getIndex();
		}
		@Override
		public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
			return this.getDefaultState().withProperty(FACING, facing);
		}
		@Override
		public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
			return true;
		}
		@Override
	    @SideOnly(Side.CLIENT)
	    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	    {
	            if (this.isOn)
	            {
	            	if (rand.nextDouble() < 0.1D)
		            {
	            		worldIn.playSound((EntityPlayer) null, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D,SoundsHandler.BUZZ_LAMP,SoundCategory.BLOCKS, 0.35F, 1.0F);
		            }
	            }
	    }
}
