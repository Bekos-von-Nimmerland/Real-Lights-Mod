package su.workbench.reallights.block;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.World;
import net.minecraft.world.IBlockAccess;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.Block;

import su.workbench.reallights.ElementsRealLightsMod;
import su.workbench.reallights.util.handlers.ConfigHandler;

@ElementsRealLightsMod.ModElement.Tag
public class BlockFluorescentLampShattered extends ElementsRealLightsMod.ModElement {
	@GameRegistry.ObjectHolder("real_lights:fluorescent_lamp_shattered")
	public static final Block block = null;
	public BlockFluorescentLampShattered(ElementsRealLightsMod instance) {
		super(instance, 5);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("fluorescent_lamp_shattered"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("real_lights:fluorescent_lamp_shattered", "inventory"));
	}
	public static class BlockCustom extends Block {
		public static final PropertyDirection FACING = BlockDirectional.FACING;
		public BlockCustom() {
			super(Material.REDSTONE_LIGHT);
			setUnlocalizedName("fluorescent_lamp_shattered");
			setSoundType(SoundType.METAL);
			setHardness(0.5F);
			setResistance(5F);
			setLightLevel(0F);
			setLightOpacity(0);
			setCreativeTab(CreativeTabs.REDSTONE);
			this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		}

		@Override
		@javax.annotation.Nullable
		public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
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
		public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
			return ConfigHandler.CAN_PASS_THROUGH;
		}

		@Override
		public boolean isFullCube(IBlockState state) {
			return false;
		}

		@Override
		protected net.minecraft.block.state.BlockStateContainer createBlockState() {
			return new net.minecraft.block.state.BlockStateContainer(this, new IProperty[]{FACING});
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
			return ((EnumFacing) state.getValue(FACING)).getIndex();
		}

		@Override
		public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
				EntityLivingBase placer) {
			return this.getDefaultState().withProperty(FACING, facing);
		}

		@Override
		public boolean isOpaqueCube(IBlockState state) {
			return false;
		}

		@Override
		public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
			return true;
		}
		@Override
		public void onBlockClicked(World world, BlockPos pos, EntityPlayer entity) {
			if (!world.isRemote && ConfigHandler.TOUCH_SHATTERS_HIT) {
			ItemStack stack = entity.getHeldItemMainhand();
			if (stack.isEmpty()){
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();
		world.playSound((EntityPlayer) null, x, y, z,
				(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("real_lights:touch_shatters_hit")),SoundCategory.BLOCKS, 0.75F, 1.0F);
		if (!((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).capabilities.isCreativeMode : false)){
			entity.attackEntityFrom(DamageSource.CACTUS, (float) ConfigHandler.TOUCH_SHATTERS_HIT_HEALTH_LOSS);
		}	
			}}}
		@Override
	    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
	    {
			if(ConfigHandler.TOUCH_SHATTERS_HIT){
				if ((entityIn instanceof EntityPlayer) ? !(((EntityPlayer) entityIn).capabilities.isCreativeMode) : (entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityItem)&& !(entityIn instanceof AbstractSkeleton))) {
					entityIn.attackEntityFrom(DamageSource.CACTUS, (float) ConfigHandler.TOUCH_SHATTERS_HIT_HEALTH_LOSS);
		}
	    }
		}
	}
}