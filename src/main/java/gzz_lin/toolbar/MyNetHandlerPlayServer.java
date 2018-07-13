package gzz_lin.toolbar;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.server.MinecraftServer;

public class MyNetHandlerPlayServer extends NetHandlerPlayServer {

	public MyNetHandlerPlayServer(MinecraftServer server, NetworkManager networkManagerIn, EntityPlayerMP playerIn) {
		super(server, networkManagerIn, playerIn);
	}

	@Override
	public void processHeldItemChange(CPacketHeldItemChange packetIn) {
		PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());

        if ((packetIn.getSlotId() >= 0 && packetIn.getSlotId() < InventoryPlayer.getHotbarSize())||(packetIn.getSlotId() >= 27 && packetIn.getSlotId() < 36))
        {
            this.player.inventory.currentItem = packetIn.getSlotId();
            this.player.markPlayerActive();
        }
	}

	
}
