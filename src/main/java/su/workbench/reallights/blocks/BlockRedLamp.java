package su.workbench.reallights.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import su.workbench.reallights.init.BlockInit;
import su.workbench.reallights.util.handlers.SoundsHandler;

public class BlockRedLamp extends BlockLBulbBase
{
	public BlockRedLamp(String name, boolean isOn) {
		super(name, isOn);
	}
	@Override
	@javax.annotation.Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
        switch (blockState.getValue(FACING))
        {
            case EAST:
            default:
                return RED_LAMP_EAST_AABB;
            case WEST:
                return RED_LAMP_WEST_AABB;
            case SOUTH:
                return RED_LAMP_SOUTH_AABB;
            case NORTH:
                return RED_LAMP_NORTH_AABB;
            case UP:
                return RED_LAMP_UP_AABB;
            case DOWN:
                return RED_LAMP_DOWN_AABB;
        }
	}
	@Override
	@javax.annotation.Nullable
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch (state.getValue(FACING))
        {
            case EAST:
            default:
                return RED_LAMP_EAST_AABB;
            case WEST:
                return RED_LAMP_WEST_AABB;
            case SOUTH:
                return RED_LAMP_SOUTH_AABB;
            case NORTH:
                return RED_LAMP_NORTH_AABB;
            case UP:
                return RED_LAMP_UP_AABB;
            case DOWN:
                return RED_LAMP_DOWN_AABB;
        }
    }
	@Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            if (this.isOn && !worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, BlockInit.BLOCK_RED_LAMP.getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);
            }
            else if (!this.isOn && worldIn.isBlockPowered(pos))
            {
            	worldIn.playSound((EntityPlayer) null, pos,SoundsHandler.BULB_LAMP_TURN_ON,SoundCategory.BLOCKS, 0.5F, 1.0F);
                worldIn.setBlockState(pos, BlockInit.BLOCK_RED_LAMP_ON.setLightLevel((float) worldIn.isBlockIndirectlyGettingPowered(pos)/15).getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);
            }
        }
    }
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos fromPos)
	{
        if (!world.isRemote)
        {
        	if (!this.isOn&&(world.isBlockIndirectlyGettingPowered(pos) > 0))
        	{
			world.playSound((EntityPlayer) null, pos,SoundsHandler.BULB_LAMP_TURN_ON,SoundCategory.BLOCKS, 0.5F, 1.0F);
			world.setBlockState(pos, BlockInit.BLOCK_RED_LAMP_ON.setLightLevel((float) world.isBlockIndirectlyGettingPowered(pos)/15).getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);
        	}
        	else if(this.isOn&&!(world.isBlockIndirectlyGettingPowered(pos) > 0))
        	{
			world.setBlockState(pos, BlockInit.BLOCK_RED_LAMP.getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);
        	}
        }
	}
}
