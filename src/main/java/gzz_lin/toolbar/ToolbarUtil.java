package gzz_lin.toolbar;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class ToolbarUtil {
	private static Minecraft mc = Minecraft.getMinecraft();

	
	public static synchronized void setSelect(int index) {
		
		EntityPlayerSP player = mc.player;
		
		player.inventory.currentItem=index;
	}
	public static void nextSelect() {
		int index=mc.player.inventory.currentItem;
		index++;
		if(index==9) {
			index=27;
		}else if(index==36) {
			index=0;
		}
		setSelect(index);
	}
	public static void upSelect() {
		int index=mc.player.inventory.currentItem;
		index--;
		if(index==-1) {
			index=35;
		}else if(index==26) {
			index=8;
		}
		setSelect(index);
	}
	public static int getSelect() {
		return mc.player.inventory.currentItem;
	}
	
	public static void test() {
		System.out.println("≤‚ ‘");
		mc.player.sendHorseInventory();
	}
}
