package su.workbench.reallights.blocks;

import su.workbench.reallights.Main;
import su.workbench.reallights.init.BlockInit;
import su.workbench.reallights.init.ItemInit;
import su.workbench.reallights.util.interfaces.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBase extends Block implements IHasModel
{
	protected static final AxisAlignedBB FL_LAMP90_NORTH_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.8D, 0.75D, 1.0D, 1.0D);
    protected static final AxisAlignedBB FL_LAMP90_SOUTH_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.0D, 0.75D, 1.0D, 0.2D);
    protected static final AxisAlignedBB FL_LAMP90_WEST_AABB = new AxisAlignedBB(0.8D, 0.0D, 0.25D, 1.0D, 1.0D, 0.75D);
    protected static final AxisAlignedBB FL_LAMP90_EAST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.25D, 0.2D, 1.0D, 0.75D);
    protected static final AxisAlignedBB FL_LAMP90_UP_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.0D, 0.75D, 0.2D, 1.0D);
    protected static final AxisAlignedBB FL_LAMP90_DOWN_AABB = new AxisAlignedBB(0.25D, 0.8D, 0.0D, 0.75D, 1.0D, 1.0D);
    protected static final AxisAlignedBB FL_LAMP_NORTH_AABB = new AxisAlignedBB(0.0D, 0.25D, 0.8D, 1.0D, 0.75D, 1.0D);
    protected static final AxisAlignedBB FL_LAMP_SOUTH_AABB = new AxisAlignedBB(0.0D, 0.25D, 0.0D, 1.0D, 0.75D, 0.2D);
    protected static final AxisAlignedBB FL_LAMP_WEST_AABB = new AxisAlignedBB(0.8D, 0.25D, 0.0D, 1.0D, 0.75D, 1.0D);
    protected static final AxisAlignedBB FL_LAMP_EAST_AABB = new AxisAlignedBB(0.0D, 0.25D, 0.0D, 0.2D, 0.75D, 1.0D);
    protected static final AxisAlignedBB FL_LAMP_UP_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.25D, 1.0D, 0.2D, 0.75D);
    protected static final AxisAlignedBB FL_LAMP_DOWN_AABB = new AxisAlignedBB(0.0D, 0.8D, 0.25D, 1.0D, 1.0D, 0.75D);
	protected static final AxisAlignedBB CAGED_LIGHT_BULB_NORTH_AABB = new AxisAlignedBB(0.3D, 0.3D, 0.5D, 0.7D, 0.7D, 1.0D);
    protected static final AxisAlignedBB CAGED_LIGHT_BULB_SOUTH_AABB = new AxisAlignedBB(0.3D, 0.3D, 0.0D, 0.7D, 0.7D, 0.5D);
    protected static final AxisAlignedBB CAGED_LIGHT_BULB_WEST_AABB = new AxisAlignedBB(0.5D, 0.3D, 0.3D, 1.0D, 0.7D, 0.7D);
    protected static final AxisAlignedBB CAGED_LIGHT_BULB_EAST_AABB = new AxisAlignedBB(0.0D, 0.3D, 0.3D, 0.5D, 0.7D, 0.7D);
    protected static final AxisAlignedBB CAGED_LIGHT_BULB_UP_AABB = new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.5D, 0.7D);
    protected static final AxisAlignedBB CAGED_LIGHT_BULB_DOWN_AABB = new AxisAlignedBB(0.3D, 0.5D, 0.3D, 0.7D, 1.0D, 0.7D);
    protected static final AxisAlignedBB LIGHT_BULB_NORTH_AABB = new AxisAlignedBB(0.3D, 0.3D, 0.5D, 0.7D, 0.7D, 1.0D);
    protected static final AxisAlignedBB LIGHT_BULB_SOUTH_AABB = new AxisAlignedBB(0.3D, 0.3D, 0.0D, 0.7D, 0.7D, 0.5D);
    protected static final AxisAlignedBB LIGHT_BULB_WEST_AABB = new AxisAlignedBB(0.5D, 0.3D, 0.3D, 1.0D, 0.7D, 0.7D);
    protected static final AxisAlignedBB LIGHT_BULB_EAST_AABB = new AxisAlignedBB(0.0D, 0.3D, 0.3D, 0.5D, 0.7D, 0.7D);
    protected static final AxisAlignedBB LIGHT_BULB_UP_AABB = new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.5D, 0.7D);
    protected static final AxisAlignedBB LIGHT_BULB_DOWN_AABB = new AxisAlignedBB(0.3D, 0.5D, 0.3D, 0.7D, 1.0D, 0.7D);
    protected static final AxisAlignedBB LIGHT_BULB_SHATTERED_NORTH_AABB = new AxisAlignedBB(0.3D, 0.3D, 0.7D, 0.7D, 0.7D, 1.0D);
    protected static final AxisAlignedBB LIGHT_BULB_SHATTERED_SOUTH_AABB = new AxisAlignedBB(0.3D, 0.3D, 0.0D, 0.7D, 0.7D, 0.3D);
    protected static final AxisAlignedBB LIGHT_BULB_SHATTERED_WEST_AABB = new AxisAlignedBB(0.7D, 0.3D, 0.3D, 1.0D, 0.7D, 0.7D);
    protected static final AxisAlignedBB LIGHT_BULB_SHATTERED_EAST_AABB = new AxisAlignedBB(0.0D, 0.3D, 0.3D, 0.3D, 0.7D, 0.7D);
    protected static final AxisAlignedBB LIGHT_BULB_SHATTERED_UP_AABB = new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.3D, 0.7D);
    protected static final AxisAlignedBB LIGHT_BULB_SHATTERED_DOWN_AABB = new AxisAlignedBB(0.3D, 0.7D, 0.3D, 0.7D, 1.0D, 0.7D);
    protected static final AxisAlignedBB RED_LAMP_NORTH_AABB = new AxisAlignedBB(0.3D, 0.3D, 0.6D, 0.7D, 0.7D, 1.0D);
    protected static final AxisAlignedBB RED_LAMP_SOUTH_AABB = new AxisAlignedBB(0.3D, 0.3D, 0.0D, 0.7D, 0.7D, 0.4D);
    protected static final AxisAlignedBB RED_LAMP_WEST_AABB = new AxisAlignedBB(0.6D, 0.3D, 0.3D, 1.0D, 0.7D, 0.7D);
    protected static final AxisAlignedBB RED_LAMP_EAST_AABB = new AxisAlignedBB(0.0D, 0.3D, 0.3D, 0.4D, 0.7D, 0.7D);
    protected static final AxisAlignedBB RED_LAMP_UP_AABB = new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.4D, 0.7D);
    protected static final AxisAlignedBB RED_LAMP_DOWN_AABB = new AxisAlignedBB(0.3D, 0.6D, 0.3D, 0.7D, 1.0D, 0.7D);
    protected static final AxisAlignedBB VOXEL_LIGHT_BULB_NORTH_AABB = new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 1.0D);
    protected static final AxisAlignedBB VOXEL_LIGHT_BULB_SOUTH_AABB = new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 1.0D);
    protected static final AxisAlignedBB VOXEL_LIGHT_BULB_WEST_AABB = new AxisAlignedBB(0.0D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
    protected static final AxisAlignedBB VOXEL_LIGHT_BULB_EAST_AABB = new AxisAlignedBB(0.0D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
    protected static final AxisAlignedBB VOXEL_LIGHT_BULB_UP_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
    protected static final AxisAlignedBB VOXEL_LIGHT_BULB_DOWN_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
    protected static final AxisAlignedBB VOXEL_LIGHT_BULB_SHATTERED_NORTH_AABB = new AxisAlignedBB(0.25D, 0.25D, 0.5D, 0.75D, 0.75D, 1.0D);
    protected static final AxisAlignedBB VOXEL_LIGHT_BULB_SHATTERED_SOUTH_AABB = new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 0.5D);
    protected static final AxisAlignedBB VOXEL_LIGHT_BULB_SHATTERED_WEST_AABB = new AxisAlignedBB(0.5D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
    protected static final AxisAlignedBB VOXEL_LIGHT_BULB_SHATTERED_EAST_AABB = new AxisAlignedBB(0.0D, 0.25D, 0.25D, 0.5D, 0.75D, 0.75D);
    protected static final AxisAlignedBB VOXEL_LIGHT_BULB_SHATTERED_UP_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
    protected static final AxisAlignedBB VOXEL_LIGHT_BULB_SHATTERED_DOWN_AABB = new AxisAlignedBB(0.25D, 0.5D, 0.25D, 0.75D, 1.0D, 0.75D);
    
	public BlockBase(String name, Material material) 
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.REDSTONE);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(name));
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}
	
	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) 
	{
		return false;
	}
	@Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
}
