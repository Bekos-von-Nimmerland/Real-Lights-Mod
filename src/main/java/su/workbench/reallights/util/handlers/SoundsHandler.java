package su.workbench.reallights.util.handlers;

import su.workbench.reallights.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler 
{
	public static SoundEvent BUZZ_LAMP, BULB_LAMP_TURN_ON, FLUORESCENT_LAMP_BUZZ, FLUORESCENT_LAMP_TURN_ON, ELECTRICSHOCK, LAMP_OFF_BREAK, LAMP_ON_BREAK, TOUCH_HEAT_HIT, TOUCH_SHATTERS_HIT;
	
	public static void registerSounds()
	{
		BUZZ_LAMP = registerSound("buzz_lamp");
		BULB_LAMP_TURN_ON = registerSound("bulb_lamp_turn_on");
		FLUORESCENT_LAMP_BUZZ = registerSound("fluorescent_lamp_buzz");
		FLUORESCENT_LAMP_TURN_ON = registerSound("fluorescent_lamp_turn_on");
		ELECTRICSHOCK = registerSound("electricshock");
		LAMP_OFF_BREAK = registerSound("lamp_off_break");
		LAMP_ON_BREAK = registerSound("lamp_on_break");
		TOUCH_HEAT_HIT = registerSound("touch_heat_hit");
		TOUCH_SHATTERS_HIT = registerSound("touch_shatters_hit");
	}
	
	private static SoundEvent registerSound(String name)
	{
		ResourceLocation location = new ResourceLocation(Reference.MODID, name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
}
