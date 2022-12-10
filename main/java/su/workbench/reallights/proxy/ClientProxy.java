package su.workbench.reallights.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import su.workbench.reallights.util.handlers.ParticleMessageHandler;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerModel(Item item, int metadata) 
	{
		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	//for this code special thanks to dalobstah from minecraftforge forum
	public static void preInitClientOnly()
	{
		CommonProxy.simpleNetworkWrapper.registerMessage(ParticleMessageHandler.Handler.class, ParticleMessageHandler.class, CommonProxy.LAMP_SHATTERED, Side.CLIENT);
	}
}
