package gzz_lin.toolbar;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Key {
	public static KeyBinding[] toolKeys = new KeyBinding[9];
	static {
		registryKey();
	}

	@SideOnly(Side.CLIENT)
	private static void registryKey() {
		toolKeys[0]=new KeyBinding("tool.key1", Keyboard.KEY_Y, "toolbar");
		toolKeys[1]=new KeyBinding("tool.key2", Keyboard.KEY_U, "toolbar");
		toolKeys[2]=new KeyBinding("tool.key3", Keyboard.KEY_I, "toolbar");
		toolKeys[3]=new KeyBinding("tool.key4", Keyboard.KEY_O, "toolbar");
		toolKeys[4]=new KeyBinding("tool.key5", Keyboard.KEY_P, "toolbar");
		toolKeys[5]=new KeyBinding("tool.key6", Keyboard.KEY_H, "toolbar");
		toolKeys[6]=new KeyBinding("tool.key7", Keyboard.KEY_J, "toolbar");
		toolKeys[7]=new KeyBinding("tool.key8", Keyboard.KEY_K, "toolbar");
		toolKeys[8]=new KeyBinding("tool.key9", Keyboard.KEY_L, "toolbar");
		for(int i=0;i<9;i++) {
			ClientRegistry.registerKeyBinding(toolKeys[i]);
		}
		
	}
}
