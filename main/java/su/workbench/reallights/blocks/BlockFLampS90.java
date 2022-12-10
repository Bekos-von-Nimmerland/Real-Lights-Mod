package su.workbench.reallights.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import su.workbench.reallights.util.handlers.ConfigHandler;
import su.workbench.reallights.util.handlers.SoundsHandler;

public class BlockFLampS90 extends BlockFLampBase 
{
	public BlockFLampS90(String name, boolean isOn) 
	{
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
                return FL_LAMP90_EAST_AABB;
            case WEST:
                return FL_LAMP90_WEST_AABB;
            case SOUTH:
                return FL_LAMP90_SOUTH_AABB;
            case NORTH:
                return FL_LAMP90_NORTH_AABB;
            case UP:
                return FL_LAMP90_UP_AABB;
            case DOWN:
                return FL_LAMP90_DOWN_AABB;
        }
	}
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch (state.getValue(FACING))
        {
            case EAST:
            default:
                return FL_LAMP90_EAST_AABB;
            case WEST:
                return FL_LAMP90_WEST_AABB;
            case SOUTH:
                return FL_LAMP90_SOUTH_AABB;
            case NORTH:
                return FL_LAMP90_NORTH_AABB;
            case UP:
                return FL_LAMP90_UP_AABB;
            case DOWN:
                return FL_LAMP90_DOWN_AABB;
        }
    }
	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer entity)
	{
		if(ConfigHandler.TOUCH_SHATTERS_HIT&&!world.isRemote)
		{
			ItemStack stack = entity.getHeldItemMainhand();
			world.playSound((EntityPlayer) null, pos,SoundsHandler.TOUCH_SHATTERS_HIT,SoundCategory.BLOCKS, 0.75F, 1.0F);
			if (stack.isEmpty() && !((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).capabilities.isCreativeMode : true))
			{
				entity.attackEntityFrom(DamageSource.CACTUS, (float) ConfigHandler.TOUCH_SHATTERS_HIT_HEALTH_LOSS);
			}	
		}
	}
	@Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entityIn)
    {
		if(ConfigHandler.TOUCH_SHATTERS_HIT&&!world.isRemote)
		{
			if ((entityIn instanceof EntityPlayer) ? !(((EntityPlayer) entityIn).capabilities.isCreativeMode) : (entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityItem)&& !(entityIn instanceof AbstractSkeleton)))
			{
				entityIn.attackEntityFrom(DamageSource.CACTUS, (float) ConfigHandler.TOUCH_SHATTERS_HIT_HEALTH_LOSS);
			}
		}
	}
}
