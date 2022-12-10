package su.workbench.reallights.init;

import java.util.ArrayList;
import java.util.List;
import su.workbench.reallights.blocks.BlockCLBulb;
import su.workbench.reallights.blocks.BlockCPVSLBulb;
import su.workbench.reallights.blocks.BlockCVSLBulb;
import su.workbench.reallights.blocks.BlockFLamp;
import su.workbench.reallights.blocks.BlockFLamp90;
import su.workbench.reallights.blocks.BlockFLampC;
import su.workbench.reallights.blocks.BlockFLampC90;
import su.workbench.reallights.blocks.BlockFLampS;
import su.workbench.reallights.blocks.BlockFLampS90;
import su.workbench.reallights.blocks.BlockLBulb;
import su.workbench.reallights.blocks.BlockLBulbS;
import su.workbench.reallights.blocks.BlockPVSLBulb;
import su.workbench.reallights.blocks.BlockPVSLBulbS;
import su.workbench.reallights.blocks.BlockRedLamp;
import su.workbench.reallights.blocks.BlockVLBulb;
import su.workbench.reallights.blocks.BlockVLBulbS;
import su.workbench.reallights.blocks.BlockVSLBulb;
import su.workbench.reallights.blocks.BlockVSLBulbS;
import net.minecraft.block.Block;

public class BlockInit 
{	
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	//Fluorescent Lamps
	public static final Block BLOCK_FLUORESCENT_LAMP = new BlockFLamp("fluorescent_lamp", false);
	public static final Block BLOCK_FLUORESCENT_LAMP_ON = new BlockFLamp("fluorescent_lamp_on", true);
	public static final Block BLOCK_FLUORESCENT_LAMP_90 = new BlockFLamp90("fluorescent_lamp_90", false);
	public static final Block BLOCK_FLUORESCENT_LAMP_ON_90 = new BlockFLamp90("fluorescent_lamp_on_90", true);
	public static final Block BLOCK_FLUORESCENT_LAMP_CAGED = new BlockFLampC("fluorescent_lamp_caged", false);
	public static final Block BLOCK_FLUORESCENT_LAMP_CAGED_ON = new BlockFLampC("fluorescent_lamp_caged_on", true);
	public static final Block BLOCK_FLUORESCENT_LAMP_CAGED_90 = new BlockFLampC90("fluorescent_lamp_caged_90", false);
	public static final Block BLOCK_FLUORESCENT_LAMP_CAGED_ON_90 = new BlockFLampC90("fluorescent_lamp_caged_on_90", true);
	public static final Block BLOCK_FLUORESCENT_LAMP_SHATTERED = new BlockFLampS("fluorescent_lamp_shattered", false);
	public static final Block BLOCK_FLUORESCENT_LAMP_SHATTERED_90 = new BlockFLampS90("fluorescent_lamp_shattered_90", false);
	//Incandescent Lamps
	public static final Block BLOCK_LIGHT_BULB = new BlockLBulb("light_bulb_off", false).setLightLevel(0.0F);
	public static final Block BLOCK_LIGHT_BULB_ON = new BlockLBulb("light_bulb_on", true);
	public static final Block BLOCK_LIGHT_BULB_SHATTERED = new BlockLBulbS("light_bulb_shattered", false).setLightLevel(0.0F);
	public static final Block BLOCK_VOXEL_LIGHT_BULB = new BlockVLBulb("voxel_light_bulb_off", false).setLightLevel(0.0F);
	public static final Block BLOCK_VOXEL_LIGHT_BULB_ON = new BlockVLBulb("voxel_light_bulb_on", true);
	public static final Block BLOCK_VOXEL_LIGHT_BULB_SHATTERED = new BlockVLBulbS("voxel_light_bulb_shattered", false).setLightLevel(0.0F);
	public static final Block BLOCK_VOXEL_SMALL_LIGHT_BULB = new BlockVSLBulb("voxel_small_light_bulb_off", false).setLightLevel(0.0F);
	public static final Block BLOCK_VOXEL_SMALL_LIGHT_BULB_ON = new BlockVSLBulb("voxel_small_light_bulb_on", true);
	public static final Block BLOCK_VOXEL_SMALL_LIGHT_BULB_SHATTERED = new BlockVSLBulbS("voxel_small_light_bulb_shattered", false).setLightLevel(0.0F);
	public static final Block BLOCK_PENDANT_VOXEL_SMALL_LIGHT_BULB = new BlockPVSLBulb("pendant_voxel_small_light_bulb_off", false).setLightLevel(0.0F);
	public static final Block BLOCK_PENDANT_VOXEL_SMALL_LIGHT_BULB_ON = new BlockPVSLBulb("pendant_voxel_small_light_bulb_on", true);
	public static final Block BLOCK_PENDANT_VOXEL_SMALL_LIGHT_BULB_SHATTERED = new BlockPVSLBulbS("pendant_voxel_small_light_bulb_shattered", false).setLightLevel(0.0F);
	public static final Block BLOCK_CAGED_VOXEL_SMALL_LIGHT_BULB = new BlockCVSLBulb("caged_voxel_small_light_bulb_off", false).setLightLevel(0.0F);
	public static final Block BLOCK_CAGED_VOXEL_SMALL_LIGHT_BULB_ON = new BlockCVSLBulb("caged_voxel_small_light_bulb_on", true);
	public static final Block BLOCK_CAGED_PENDANT_VOXEL_SMALL_LIGHT_BULB = new BlockCPVSLBulb("caged_pendant_voxel_small_light_bulb_off", false).setLightLevel(0.0F);
	public static final Block BLOCK_CAGED_PENDANT_VOXEL_SMALL_LIGHT_BULB_ON = new BlockCPVSLBulb("caged_pendant_voxel_small_light_bulb_on", true);
	public static final Block BLOCK_CAGED_LIGHT_BULB = new BlockCLBulb("caged_light_bulb_off", false).setLightLevel(0.0F);
	public static final Block BLOCK_CAGED_LIGHT_BULB_ON = new BlockCLBulb("caged_light_bulb_on", true);
	public static final Block BLOCK_RED_LAMP = new BlockRedLamp("red_lamp_off", false).setLightLevel(0.0F);
	public static final Block BLOCK_RED_LAMP_ON = new BlockRedLamp("red_lamp_on", true);
}
