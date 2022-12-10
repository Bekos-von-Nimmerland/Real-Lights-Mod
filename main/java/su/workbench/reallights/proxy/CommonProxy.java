package su.workbench.reallights.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import su.workbench.reallights.util.handlers.ParticleMessageHandler;
import su.workbench.reallights.util.handlers.ParticleServerHandlerDummy;

public class CommonProxy 
{
	public void registerModel(Item item, int metadata) {}
	public static final byte LAMP_SHATTERED = 88;
	public static SimpleNetworkWrapper simpleNetworkWrapper;
	//for this code special thanks to dalobstah from minecraftforge forum
	public static void preInitCommon()
	{
		simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("RLParticleChannel");
		simpleNetworkWrapper.registerMessage(ParticleServerHandlerDummy.class, ParticleMessageHandler.class, LAMP_SHATTERED, Side.SERVER);
	}
}