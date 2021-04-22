package su.workbench.reallights.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.EnumFacing;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.IProperty;

import su.workbench.reallights.block.BlockFluorescentLampOn;
import su.workbench.reallights.ElementsRealLightsMod;

import java.util.Map;

@ElementsRealLightsMod.ModElement.Tag
public class ProcedureFluorescentLampRedstoneOn extends ElementsRealLightsMod.ModElement {
	public ProcedureFluorescentLampRedstoneOn(ElementsRealLightsMod instance) {
		super(instance, 4);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure FluorescentLampRedstoneOn!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure FluorescentLampRedstoneOn!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure FluorescentLampRedstoneOn!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure FluorescentLampRedstoneOn!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		world.playSound((EntityPlayer) null, x, y, z, (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
				.getObject(new ResourceLocation("real_lights:fluorescent_lamp_turn_on")), SoundCategory.BLOCKS, 0.1F, 1.0F);
		if (((new Object() {
			public EnumFacing getEnumFacing(BlockPos pos) {
				try {
					IBlockState _bs = world.getBlockState(pos);
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing"))
							return _bs.getValue((PropertyDirection) prop);
					}
					return EnumFacing.NORTH;
				} catch (Exception e) {
					return EnumFacing.NORTH;
				}
			}
		}.getEnumFacing(new BlockPos((int) x, (int) y, (int) z))) == EnumFacing.DOWN)) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), BlockFluorescentLampOn.block.getDefaultState(), 3);
			try {
				IBlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
				for (IProperty<?> prop : _bs.getProperties().keySet()) {
					if (prop.getName().equals("facing")) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.withProperty((PropertyDirection) prop, EnumFacing.DOWN), 3);
						break;
					}
				}
			} catch (Exception e) {
			}
		} else {
			if (((new Object() {
				public EnumFacing getEnumFacing(BlockPos pos) {
					try {
						IBlockState _bs = world.getBlockState(pos);
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing"))
								return _bs.getValue((PropertyDirection) prop);
						}
						return EnumFacing.NORTH;
					} catch (Exception e) {
						return EnumFacing.NORTH;
					}
				}
			}.getEnumFacing(new BlockPos((int) x, (int) y, (int) z))) == EnumFacing.UP)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), BlockFluorescentLampOn.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos((int) x, (int) y, (int) z), _bs.withProperty((PropertyDirection) prop, EnumFacing.UP),
									3);
							break;
						}
					}
				} catch (Exception e) {
				}
			} else {
				if (((new Object() {
					public EnumFacing getEnumFacing(BlockPos pos) {
						try {
							IBlockState _bs = world.getBlockState(pos);
							for (IProperty<?> prop : _bs.getProperties().keySet()) {
								if (prop.getName().equals("facing"))
									return _bs.getValue((PropertyDirection) prop);
							}
							return EnumFacing.NORTH;
						} catch (Exception e) {
							return EnumFacing.NORTH;
						}
					}
				}.getEnumFacing(new BlockPos((int) x, (int) y, (int) z))) == EnumFacing.NORTH)) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), BlockFluorescentLampOn.block.getDefaultState(), 3);
					try {
						IBlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
						for (IProperty<?> prop : _bs.getProperties().keySet()) {
							if (prop.getName().equals("facing")) {
								world.setBlockState(new BlockPos((int) x, (int) y, (int) z),
										_bs.withProperty((PropertyDirection) prop, EnumFacing.NORTH), 3);
								break;
							}
						}
					} catch (Exception e) {
					}
				} else {
					if (((new Object() {
						public EnumFacing getEnumFacing(BlockPos pos) {
							try {
								IBlockState _bs = world.getBlockState(pos);
								for (IProperty<?> prop : _bs.getProperties().keySet()) {
									if (prop.getName().equals("facing"))
										return _bs.getValue((PropertyDirection) prop);
								}
								return EnumFacing.NORTH;
							} catch (Exception e) {
								return EnumFacing.NORTH;
							}
						}
					}.getEnumFacing(new BlockPos((int) x, (int) y, (int) z))) == EnumFacing.SOUTH)) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), BlockFluorescentLampOn.block.getDefaultState(), 3);
						try {
							IBlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
							for (IProperty<?> prop : _bs.getProperties().keySet()) {
								if (prop.getName().equals("facing")) {
									world.setBlockState(new BlockPos((int) x, (int) y, (int) z),
											_bs.withProperty((PropertyDirection) prop, EnumFacing.SOUTH), 3);
									break;
								}
							}
						} catch (Exception e) {
						}
					} else {
						if (((new Object() {
							public EnumFacing getEnumFacing(BlockPos pos) {
								try {
									IBlockState _bs = world.getBlockState(pos);
									for (IProperty<?> prop : _bs.getProperties().keySet()) {
										if (prop.getName().equals("facing"))
											return _bs.getValue((PropertyDirection) prop);
									}
									return EnumFacing.NORTH;
								} catch (Exception e) {
									return EnumFacing.NORTH;
								}
							}
						}.getEnumFacing(new BlockPos((int) x, (int) y, (int) z))) == EnumFacing.WEST)) {
							world.setBlockState(new BlockPos((int) x, (int) y, (int) z), BlockFluorescentLampOn.block.getDefaultState(), 3);
							try {
								IBlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
								for (IProperty<?> prop : _bs.getProperties().keySet()) {
									if (prop.getName().equals("facing")) {
										world.setBlockState(new BlockPos((int) x, (int) y, (int) z),
												_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
										break;
									}
								}
							} catch (Exception e) {
							}
						} else {
							world.setBlockState(new BlockPos((int) x, (int) y, (int) z), BlockFluorescentLampOn.block.getDefaultState(), 3);
							try {
								IBlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
								for (IProperty<?> prop : _bs.getProperties().keySet()) {
									if (prop.getName().equals("facing")) {
										world.setBlockState(new BlockPos((int) x, (int) y, (int) z),
												_bs.withProperty((PropertyDirection) prop, EnumFacing.EAST), 3);
										break;
									}
								}
							} catch (Exception e) {
							}
						}
					}
				}
			}
		}
	}
}
