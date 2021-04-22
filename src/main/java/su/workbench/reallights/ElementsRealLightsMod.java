package su.workbench.reallights;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.potion.Potion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.block.Block;

import java.util.function.Supplier;
import java.util.Random;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;

public class ElementsRealLightsMod implements IWorldGenerator {
	public final List<ModElement> elements = new ArrayList<>();
	public final List<Supplier<Block>> blocks = new ArrayList<>();
	public final List<Supplier<Item>> items = new ArrayList<>();
	public final List<Supplier<Biome>> biomes = new ArrayList<>();
	public final List<Supplier<EntityEntry>> entities = new ArrayList<>();
	public final List<Supplier<Potion>> potions = new ArrayList<>();
	public static Map<ResourceLocation, net.minecraft.util.SoundEvent> sounds = new HashMap<>();
	public ElementsRealLightsMod() {
		sounds.put(new ResourceLocation("real_lights", "buzz_lamp"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("real_lights", "buzz_lamp")));
		sounds.put(new ResourceLocation("real_lights", "bulb_lamp_turn_on"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("real_lights", "bulb_lamp_turn_on")));
		sounds.put(new ResourceLocation("real_lights", "fluorescent_lamp_buzz"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("real_lights", "fluorescent_lamp_buzz")));
		sounds.put(new ResourceLocation("real_lights", "fluorescent_lamp_turn_on"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("real_lights", "fluorescent_lamp_turn_on")));
		sounds.put(new ResourceLocation("real_lights", "electricshock"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("real_lights", "electricshock")));
		sounds.put(new ResourceLocation("real_lights", "lamp_off_break"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("real_lights", "lamp_off_break")));
		sounds.put(new ResourceLocation("real_lights", "lamp_on_break"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("real_lights", "lamp_on_break")));
	}

	public void preInit(FMLPreInitializationEvent event) {
		try {
			for (ASMDataTable.ASMData asmData : event.getAsmData().getAll(ModElement.Tag.class.getName())) {
				Class<?> clazz = Class.forName(asmData.getClassName());
				if (clazz.getSuperclass() == ElementsRealLightsMod.ModElement.class)
					elements.add((ElementsRealLightsMod.ModElement) clazz.getConstructor(this.getClass()).newInstance(this));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.sort(elements);
		elements.forEach(ElementsRealLightsMod.ModElement::initElements);
		this.addNetworkMessage(RealLightsModVariables.WorldSavedDataSyncMessageHandler.class, RealLightsModVariables.WorldSavedDataSyncMessage.class,
				Side.SERVER, Side.CLIENT);
	}

	public void registerSounds(RegistryEvent.Register<net.minecraft.util.SoundEvent> event) {
		for (Map.Entry<ResourceLocation, net.minecraft.util.SoundEvent> sound : sounds.entrySet())
			event.getRegistry().register(sound.getValue().setRegistryName(sound.getKey()));
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator cg, IChunkProvider cp) {
		elements.forEach(element -> element.generateWorld(random, chunkX * 16, chunkZ * 16, world, world.provider.getDimension(), cg, cp));
	}


	public int getItemBurnTime(ItemStack fuel) {
		for (ModElement element : elements) {
			int ret = element.addFuel(fuel);
			if (ret != 0)
				return ret;
		}
		return 0;
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.player.world.isRemote) {
			WorldSavedData mapdata = RealLightsModVariables.MapVariables.get(event.player.world);
			WorldSavedData worlddata = RealLightsModVariables.WorldVariables.get(event.player.world);
			if (mapdata != null)
				RealLightsMod.PACKET_HANDLER.sendTo(new RealLightsModVariables.WorldSavedDataSyncMessage(0, mapdata), (EntityPlayerMP) event.player);
			if (worlddata != null)
				RealLightsMod.PACKET_HANDLER.sendTo(new RealLightsModVariables.WorldSavedDataSyncMessage(1, worlddata),
						(EntityPlayerMP) event.player);
		}
	}

	@SubscribeEvent
	public void onPlayerChangedDimension(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.player.world.isRemote) {
			WorldSavedData worlddata = RealLightsModVariables.WorldVariables.get(event.player.world);
			if (worlddata != null)
				RealLightsMod.PACKET_HANDLER.sendTo(new RealLightsModVariables.WorldSavedDataSyncMessage(1, worlddata),
						(EntityPlayerMP) event.player);
		}
	}
	private int messageID = 0;
	public <T extends IMessage, V extends IMessage> void addNetworkMessage(Class<? extends IMessageHandler<T, V>> handler, Class<T> messageClass,
			Side... sides) {
		for (Side side : sides)
			RealLightsMod.PACKET_HANDLER.registerMessage(handler, messageClass, messageID, side);
		messageID++;
	}
	public static class GuiHandler implements IGuiHandler {
		@Override
		public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
			return null;
		}

		@Override
		public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
			return null;
		}
	}
	public List<ModElement> getElements() {
		return elements;
	}

	public List<Supplier<Block>> getBlocks() {
		return blocks;
	}

	public List<Supplier<Item>> getItems() {
		return items;
	}

	public List<Supplier<Biome>> getBiomes() {
		return biomes;
	}

	public List<Supplier<EntityEntry>> getEntities() {
		return entities;
	}

	public List<Supplier<Potion>> getPotions() {
		return potions;
	}
	public static class ModElement implements Comparable<ModElement> {
		@Retention(RetentionPolicy.RUNTIME)
		public @interface Tag {
		}
		protected final ElementsRealLightsMod elements;
		protected final int sortid;
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
		protected static final AxisAlignedBB INC_LAMP_NORTH_AABB = new AxisAlignedBB(0.25D, 0.25D, 0.5D, 0.75D, 0.75D, 1.0D);
	    protected static final AxisAlignedBB INC_LAMP_SOUTH_AABB = new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 0.5D);
	    protected static final AxisAlignedBB INC_LAMP_WEST_AABB = new AxisAlignedBB(0.5D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
	    protected static final AxisAlignedBB INC_LAMP_EAST_AABB = new AxisAlignedBB(0.0D, 0.25D, 0.25D, 0.5D, 0.75D, 0.75D);
	    protected static final AxisAlignedBB INC_LAMP_UP_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
	    protected static final AxisAlignedBB INC_LAMP_DOWN_AABB = new AxisAlignedBB(0.25D, 0.5D, 0.25D, 0.75D, 1.0D, 0.75D);
	    protected static final AxisAlignedBB LIGHT_BULB_NORTH_AABB = new AxisAlignedBB(0.25D, 0.25D, 0.5D, 0.75D, 0.75D, 1.0D);
	    protected static final AxisAlignedBB LIGHT_BULB_SOUTH_AABB = new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 0.5D);
	    protected static final AxisAlignedBB LIGHT_BULB_WEST_AABB = new AxisAlignedBB(0.5D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
	    protected static final AxisAlignedBB LIGHT_BULB_EAST_AABB = new AxisAlignedBB(0.0D, 0.25D, 0.25D, 0.5D, 0.75D, 0.75D);
	    protected static final AxisAlignedBB LIGHT_BULB_UP_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
	    protected static final AxisAlignedBB LIGHT_BULB_DOWN_AABB = new AxisAlignedBB(0.25D, 0.5D, 0.25D, 0.75D, 1.0D, 0.75D);
	    protected static final AxisAlignedBB RED_LAMP_NORTH_AABB = new AxisAlignedBB(0.25D, 0.25D, 0.6D, 0.75D, 0.75D, 1.0D);
	    protected static final AxisAlignedBB RED_LAMP_SOUTH_AABB = new AxisAlignedBB(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 0.4D);
	    protected static final AxisAlignedBB RED_LAMP_WEST_AABB = new AxisAlignedBB(0.6D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);
	    protected static final AxisAlignedBB RED_LAMP_EAST_AABB = new AxisAlignedBB(0.0D, 0.25D, 0.25D, 0.4D, 0.75D, 0.75D);
	    protected static final AxisAlignedBB RED_LAMP_UP_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.4D, 0.75D);
	    protected static final AxisAlignedBB RED_LAMP_DOWN_AABB = new AxisAlignedBB(0.25D, 0.6D, 0.25D, 0.75D, 1.0D, 0.75D);
	    
		public ModElement(ElementsRealLightsMod elements, int sortid) {
			this.elements = elements;
			this.sortid = sortid;
		}

		public void initElements() {
		}

		public void init(FMLInitializationEvent event) {
		}

		public void preInit(FMLPreInitializationEvent event) {
		}

		public void generateWorld(Random random, int posX, int posZ, World world, int dimID, IChunkGenerator cg, IChunkProvider cp) {
		}

		public void serverLoad(FMLServerStartingEvent event) {
		}

		public void registerModels(ModelRegistryEvent event) {
		}

		public int addFuel(ItemStack fuel) {
			return 0;
		}

		@Override
		public int compareTo(ModElement other) {
			return this.sortid - other.sortid;
		}
	}
}
