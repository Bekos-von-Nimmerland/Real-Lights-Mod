package su.workbench.reallights.util.handlers;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
//for this code special thanks to dalobstah from minecraftforge forum; code was altered to use position instead of coordinates as input
public class ParticleMessageHandler implements IMessage
{

	private boolean messageValid;
	
	private double x, y, z;
	
	public ParticleMessageHandler()
	{
		this.messageValid = false;
	}
	
	public ParticleMessageHandler(BlockPos pos)
	{
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
		
		this.messageValid = true;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		try
		{
			this.x = buf.readDouble();
			this.y = buf.readDouble();
			this.z = buf.readDouble();
		}
		catch(IndexOutOfBoundsException ioe)
		{
			return;
		}
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		if(!this.messageValid)
			return;
		
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);
	}
	
	
	public static class Handler implements IMessageHandler<ParticleMessageHandler, IMessage>
	{

		@Override
		public IMessage onMessage(ParticleMessageHandler message, MessageContext ctx)
		{
			if(!message.messageValid && ctx.side != Side.CLIENT)
			{
				return null;
			}
			
			
			Minecraft minecraft = Minecraft.getMinecraft();
		    final WorldClient worldClient = minecraft.world;
			
		    minecraft.addScheduledTask(() -> processMessage(message, worldClient));
		    
			return null;
		}
		
		void processMessage(ParticleMessageHandler message, WorldClient worldClient)
		{
			worldClient.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, message.x + 0.5D, message.y + 1.0D, message.z + 0.5D, worldClient.rand.nextGaussian()*0.5D, worldClient.rand.nextGaussian()*0.5D, worldClient.rand.nextGaussian()*0.5D);
			worldClient.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, message.x + 0.5D, message.y + 1.0D, message.z + 0.5D, worldClient.rand.nextGaussian()*0.5D, worldClient.rand.nextGaussian()*0.5D, worldClient.rand.nextGaussian()*0.5D);
			worldClient.spawnParticle(EnumParticleTypes.FLAME, message.x + 0.5D, message.y + 1.0D, message.z + 0.5D, worldClient.rand.nextGaussian()*0.5D, worldClient.rand.nextGaussian()*0.5D, worldClient.rand.nextGaussian()*0.5D);
			worldClient.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, message.x + 0.5D, message.y + 1.0D, message.z + 0.5D, worldClient.rand.nextGaussian()*0.5D, worldClient.rand.nextGaussian()*0.5D, worldClient.rand.nextGaussian()*0.5D);
		}
	}
}
