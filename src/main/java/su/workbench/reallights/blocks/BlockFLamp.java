package su.workbench.reallights.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
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
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import su.workbench.reallights.init.BlockInit;
import su.workbench.reallights.proxy.CommonProxy;
import su.workbench.reallights.util.handlers.ConfigHandler;
import su.workbench.reallights.util.handlers.ParticleMessageHandler;
import su.workbench.reallights.util.handlers.SoundsHandler;

public class BlockFLamp extends BlockFLampBase 
{
	public BlockFLamp(String name, boolean isOn) 
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
                return FL_LAMP_EAST_AABB;
            case WEST:
                return FL_LAMP_WEST_AABB;
            case SOUTH:
                return FL_LAMP_SOUTH_AABB;
            case NORTH:
                return FL_LAMP_NORTH_AABB;
            case UP:
                return FL_LAMP_UP_AABB;
            case DOWN:
                return FL_LAMP_DOWN_AABB;
        }
	}
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch (state.getValue(FACING))
        {
            case EAST:
            default:
                return FL_LAMP_EAST_AABB;
            case WEST:
                return FL_LAMP_WEST_AABB;
            case SOUTH:
                return FL_LAMP_SOUTH_AABB;
            case NORTH:
                return FL_LAMP_NORTH_AABB;
            case UP:
                return FL_LAMP_UP_AABB;
            case DOWN:
                return FL_LAMP_DOWN_AABB;
        }
    }
	@Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            if (this.isOn && !worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, BlockInit.BLOCK_FLUORESCENT_LAMP.getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);
            }
            else if (!this.isOn && worldIn.isBlockPowered(pos))
            {
            	worldIn.playSound((EntityPlayer) null, pos,SoundsHandler.FLUORESCENT_LAMP_TURN_ON,SoundCategory.BLOCKS, 0.2F, 1.0F);
                worldIn.setBlockState(pos, BlockInit.BLOCK_FLUORESCENT_LAMP_ON.getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);
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
        		world.playSound((EntityPlayer) null, pos,SoundsHandler.FLUORESCENT_LAMP_TURN_ON,SoundCategory.BLOCKS, 0.4F, 1.0F);
        		world.setBlockState(pos, BlockInit.BLOCK_FLUORESCENT_LAMP_ON.getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);
        	}
        	else if(this.isOn&&!(world.isBlockIndirectlyGettingPowered(pos) > 0))
        	{
        		world.setBlockState(pos, BlockInit.BLOCK_FLUORESCENT_LAMP.getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);
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
				world.setBlockState(pos,BlockInit.BLOCK_FLUORESCENT_LAMP_SHATTERED.getDefaultState().withProperty(FACING, blockState.getValue(FACING)), 2);
				if (this.isOn)
				{
			        ParticleMessageHandler particlePacket = new ParticleMessageHandler(pos);
					int i = 0;
						for (i = 1;i < 10;i++) {
							NetworkRegistry.TargetPoint target = new TargetPoint(world.provider.getDimension(), (double)pos.getX() - world.rand.nextGaussian()*1.5D, (double)pos.getY() - world.rand.nextGaussian()*1.5D, (double)pos.getZ() - world.rand.nextGaussian()*1.5D, world.rand.nextGaussian()*20.d);
							CommonProxy.simpleNetworkWrapper.sendToAllAround(particlePacket, target);
						}
				}
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
				world.setBlockState(pos, BlockInit.BLOCK_FLUORESCENT_LAMP_SHATTERED.getDefaultState().withProperty(FACING, state.getValue(FACING)), 2);
			}
		}
	}
}
