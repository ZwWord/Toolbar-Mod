package gzz_lin.toolbar;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventLoader {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onMouseEvent(MouseEvent event) {
		if (event.getDwheel() > 0) {
			ToolbarUtil.upSelect();
			event.setCanceled(true);
		} else if (event.getDwheel() < 0) {
			ToolbarUtil.nextSelect();
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			MyInventory inventory = new MyInventory(player);
			inventory.copyInventory(player.inventory);
			inventory.currentItem = player.inventory.currentItem;
			player.inventory = inventory;
			// Í¬²½
			player.inventoryContainer = new ContainerPlayer(inventory, !event.getWorld().isRemote, player);
			player.openContainer = player.inventoryContainer;

			if (player instanceof EntityPlayerMP) {
				EntityPlayerMP playerMP = (EntityPlayerMP) player;
				playerMP.connection = new MyNetHandlerPlayServer(playerMP.mcServer,
						playerMP.connection.getNetworkManager(), playerMP);
			}

		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		for (int i = 0; i < 9; i++) {
			if (Key.toolKeys[i].isPressed()) {
				ToolbarUtil.setSelect(i + 27);
				return;
			}
			if (Minecraft.getMinecraft().gameSettings.keyBindsHotbar[i].isPressed()) {
				ToolbarUtil.setSelect(i);
				return;
			}
//			if (Key.test.isPressed()) {
//
//				ToolbarUtil.test();
//
//			}
		}

	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderHUD(RenderGameOverlayEvent.Pre event) {
		ElementType type = event.getType();
		if (type == ElementType.CROSSHAIRS) {
			GuiIngameForge.renderExperiance = false;
			GuiIngameForge.renderHotbar = false;
			GuiIngameForge.right_height = 64;
			GuiIngameForge.left_height = 64;
			ToolbarMod.toolbrRender.renderHotbar();
			ToolbarMod.toolbrRender.renderExperience();
		}
	}

}
