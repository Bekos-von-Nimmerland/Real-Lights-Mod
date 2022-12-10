package su.workbench.reallights.util.handlers;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ParticleServerHandlerDummy implements IMessageHandler<ParticleMessageHandler, IMessage>
{
	//for this code special thanks to dalobstah from minecraftforge forum
	@Override
	public IMessage onMessage(ParticleMessageHandler message, MessageContext ctx)
	{
		return null;
	}

}
