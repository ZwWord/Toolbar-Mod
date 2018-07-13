package gzz_lin.toolbar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class MyInventory extends InventoryPlayer{

	public MyInventory(EntityPlayer playerIn) {
		super(playerIn);
	}

	@Override
	public ItemStack getCurrentItem() {
		return (ItemStack)this.mainInventory.get(this.currentItem) ;
	}
	
}
