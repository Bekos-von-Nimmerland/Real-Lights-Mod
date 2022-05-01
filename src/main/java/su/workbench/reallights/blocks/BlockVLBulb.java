package su.workbench.reallights.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import su.workbench.reallights.init.BlockInit;
import su.workbench.reallights.util.handlers.ConfigHandler;
import su.workbench.reallights.util.handlers.SoundsHandler;

public class BlockVLBulb extends BlockLBulbBase{

	public BlockVLBulb(String name, boolean isOn) {
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
                return VOXEL_LIGHT_BULB_EAST_AABB;
            case WEST:
                return VOXEL_LIGHT_BULB_WEST_AABB;
            case SOUTH:
                return VOXEL_LIGHT_BULB_SOUTH_AABB;
            case NORTH:
                return VOXEL_LIGHT_BULB_NORTH_AABB;
            case UP:
                return VOXEL_LIGHT_BULB_UP_AABB;
            case DOWN:
                return VOXEL_LIGHT_BULB_DOWN_AABB;
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
                return VOXEL_LIGHT_BULB_EAST_AABB;
            case WEST:
                return VOXEL_LIGHT_BULB_WEST_AABB;
            case SOUTH:
                return VOXEL_LIGHT_BULB_SOUTH_AABB;
            case NORTH:
                return VOXEL_LIGHT_BULB_NORTH_AABB;
            case UP:
                return VOXEL_LIGHT_BULB_UP_AABB;
            case DOWN:
                return VOXEL_LIGHT_BULB_DOWN_AABB;
        }
    }
	@Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            if (this.isOn && !worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, BlockInit.BLOCK_VOXEL_LIGHT_BULB.getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);
            }
            else if (!this.isOn && worldIn.isBlockPowered(pos))
            {
            	worldIn.playSound((EntityPlayer) null, pos,SoundsHandler.BULB_LAMP_TURN_ON,SoundCategory.BLOCKS, 0.5F, 1.0F);
                worldIn.setBlockState(pos, BlockInit.BLOCK_VOXEL_LIGHT_BULB_ON.setLightLevel((float) worldIn.isBlockIndirectlyGettingPowered(pos)/15).getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);
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
			world.setBlockState(pos, BlockInit.BLOCK_VOXEL_LIGHT_BULB_ON.setLightLevel((float) world.isBlockIndirectlyGettingPowered(pos)/15).getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);
        	}
        	else if(this.isOn&&!(world.isBlockIndirectlyGettingPowered(pos) > 0))
        	{
			world.setBlockState(pos, BlockInit.BLOCK_VOXEL_LIGHT_BULB.getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);
        	}
        }
	}
	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer entity)
	{
		if (!world.isRemote)
		{
			IBlockState blockState = world.getBlockState(pos);
			ItemStack stack = entity.getHeldItemMainhand();
			if (stack.isEmpty() && ConfigHandler.ELECTRIC_SHOCK && this.isOn)
			{
				world.playSound((EntityPlayer) null, pos,SoundsHandler.ELECTRICSHOCK,SoundCategory.BLOCKS, 0.75F, 1.0F);
				if (!((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).capabilities.isCreativeMode : true))
				{
					entity.attackEntityFrom(DamageSource.LIGHTNING_BOLT, (float) ConfigHandler.ELECTRIC_SHOCK_HEALTH_LOSS);
				}
			}
			else if(ConfigHandler.LAMP_BREAK_BY_ITEMS&&!(EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) > 0)&&!stack.isEmpty())
			{
				world.playSound((EntityPlayer) null, pos,(this.isOn?SoundsHandler.LAMP_ON_BREAK:SoundsHandler.LAMP_OFF_BREAK),SoundCategory.BLOCKS, 1.0F, 1.0F);
				world.setBlockState(pos,BlockInit.BLOCK_VOXEL_LIGHT_BULB_SHATTERED.getDefaultState().withProperty(FACING, blockState.getValue(FACING)), 2);
			}
		}
	}
	@Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entityIn)
    {
		if(ConfigHandler.LAMP_BREAK_BY_PROJECTILES&&!world.isRemote)
		{
			if (entityIn instanceof EntityArrow || entityIn instanceof EntityThrowable || entityIn instanceof EntityFireball)
			{
				world.playSound((EntityPlayer) null, pos,(this.isOn?SoundsHandler.LAMP_ON_BREAK:SoundsHandler.LAMP_OFF_BREAK),SoundCategory.BLOCKS, 1.0F, 1.0F);
				world.setBlockState(pos, BlockInit.BLOCK_VOXEL_LIGHT_BULB_SHATTERED.getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);
			}
		}
		else if(ConfigHandler.TOUCH_HEAT_HIT&&!world.isRemote&&this.isOn)
		{
			if ((entityIn instanceof EntityPlayer) ? !(((EntityPlayer) entityIn).capabilities.isCreativeMode) : (entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityItem)&& !(entityIn instanceof AbstractSkeleton)))
			{
				entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, (float) ConfigHandler.TOUCH_HEAT_HIT_HEALTH_LOSS);
			}
		}
	}
}
