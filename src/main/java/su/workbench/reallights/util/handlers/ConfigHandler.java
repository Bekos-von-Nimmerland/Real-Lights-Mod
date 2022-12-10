package su.workbench.reallights.util.handlers;
//Credits: TurtyWurty. Really cool guy with good tutorials.
import java.io.File;
import su.workbench.reallights.Main;
import su.workbench.reallights.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler {
	public static Configuration config;
	public static boolean ELECTRIC_SHOCK = true;
	public static float ELECTRIC_SHOCK_HEALTH_LOSS = 8;
	public static boolean LAMP_BREAK_BY_ITEMS = true;
	public static boolean LAMP_BREAK_BY_PROJECTILES = true;
	public static boolean CAN_PASS_THROUGH = true;
	public static boolean TOUCH_HEAT_HIT = true;
	public static float TOUCH_HEAT_HIT_HEALTH_LOSS = 2;
	public static boolean TOUCH_SHATTERS_HIT = true;
	public static float TOUCH_SHATTERS_HIT_HEALTH_LOSS = 1;
	public static void init(File file) {
		config = new Configuration(file);
		String category;
		category = "Base mod mechanics";
		config.addCustomCategoryComment(category, "Defines whether mod mechanics present in game.");
		ELECTRIC_SHOCK = config.getBoolean("Electric Shock mechanic", category, true, "Whether the player gets electrified when hitting powered devices");
		LAMP_BREAK_BY_ITEMS = config.getBoolean("Lamp Break by Item mechanic", category, true, "Whether the lamps can be broken by items held in hands(except Silk Touch)");
		LAMP_BREAK_BY_PROJECTILES = config.getBoolean("Lamp Break by Projectiles mechanic", category, true, "Whether the lamps can be broken by projectiles");
		CAN_PASS_THROUGH = config.getBoolean("Passing through mod blocks", category, true, "Whether player and other objects may pass through mod blocks");
		TOUCH_HEAT_HIT = config.getBoolean("Heat Touch mechanic", category, true, "Whether the player gets hurt when touching heated and powered devices");
		TOUCH_SHATTERS_HIT = config.getBoolean("Shatter Touch mechanic", category, true, "Whether the player gets hurt when touching shattered devices");
		category = "Mod values";
		config.addCustomCategoryComment(category, "Defines certain values of mod.");
		ELECTRIC_SHOCK_HEALTH_LOSS = config.getFloat("Electric Shock hit power", category, 8, 1, 20, "How many hitpoints player lose when hitting powered devices");
		TOUCH_HEAT_HIT_HEALTH_LOSS = config.getFloat("Device Heat hit power", category, 2, 1, 20, "How many hitpoints player lose when touching heated and powered devices");
		TOUCH_SHATTERS_HIT_HEALTH_LOSS = config.getFloat("Device Shatters hit power", category, 1, 1, 20, "How many hitpoints player lose when touching shattered devices");
		config.save();
	}
	public static void registerConfig(FMLPreInitializationEvent event)
	{
		Main.config = new File(event.getModConfigurationDirectory() + "/" + Reference.MODID);
		Main.config.mkdirs();
		init(new File(Main.config.getPath(), Reference.MODID + ".cfg"));
	}
} 
