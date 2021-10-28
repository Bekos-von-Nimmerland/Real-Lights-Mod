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
import net.minecraft.util.Mirror;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Enchantments;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
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
import su.workbench.reallights.util.procedure.ProcedureVoxelLightBulbBlockHitWithItem;
import su.workbench.reallights.util.procedure.ProcedureVoxelLightBulbRedstoneOn;

import java.util.Map;
import java.util.HashMap;

@ElementsRealLightsMod.ModElement.Tag
public class BlockVoxelLightBulb extends ElementsRealLightsMod.ModElement {
	@GameRegistry.ObjectHolder("real_lights:voxel_light_bulb_off")
	public static final Block block = null; 
	public BlockVoxelLightBulb(ElementsRealLightsMod instance) {
		super(instance, 12);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("voxel_light_bulb_off"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation("real_lights:voxel_light_bulb_off", "inventory"));
	}
	public static class BlockCustom extends Block {
		public static final PropertyDirection FACING = BlockDirectional.FACING;
		public BlockCustom() {
			super(Material.REDSTONE_LIGHT);
			setUnlocalizedName("voxel_light_bulb_off");
			setSoundType(SoundType.METAL);
			setHardness(0.5F);
			setResistance(5F);
			setLightLevel(0F);
			setLightOpacity(0);
			setCreativeTab(CreativeTabs.REDSTONE);
			this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		}

		@SideOnly(Side.CLIENT)
		@Override
		public BlockRenderLayer getBlockLayer() {
			return BlockRenderLayer.TRANSLUCENT;
		}

		@Override
		@javax.annotation.Nullable
		public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
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
		public void neighborChanged(IBlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos fromPos) {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			if (world.isBlockIndirectlyGettingPowered(new BlockPos(x, y, z)) > 0) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					ProcedureVoxelLightBulbRedstoneOn.executeProcedure($_dependencies);
				}
			} else {
			}
		}
		@Override
		public void onBlockClicked(World world, BlockPos pos, EntityPlayer entity) {
			if (!world.isRemote) {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			ItemStack stack = entity.getHeldItemMainhand();
			if (stack.isEmpty() || EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) > 0) {}else if(ConfigHandler.LAMP_BREAK_BY_ITEMS){
			{Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				ProcedureVoxelLightBulbBlockHitWithItem.executeProcedure($_dependencies);}}
		}
		}
		@Override
	    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
	    {
			if(ConfigHandler.LAMP_BREAK_BY_PROJECTILES){
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
	        if (!worldIn.isRemote && (entityIn instanceof EntityArrow || entityIn instanceof EntityThrowable || entityIn instanceof EntityFireball))
	        {
	        	{Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", worldIn);
				ProcedureVoxelLightBulbBlockHitWithItem.executeProcedure($_dependencies);}
	        }
	    }}
	}
}
