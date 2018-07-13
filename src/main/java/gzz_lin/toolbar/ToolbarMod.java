package gzz_lin.toolbar;

import org.apache.logging.log4j.Logger;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ToolbarMod.MODID, name = ToolbarMod.NAME, version = ToolbarMod.VERSION,acceptedMinecraftVersions = "[1.12,)")
public class ToolbarMod
{
    public static final String MODID = "toolbar";
    public static final String NAME = "Toolbar Mod";
    public static final String VERSION = "1.8";
    public static ToolbarRender toolbrRender=new ToolbarRender();
    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	
        logger = event.getModLog();
        MinecraftForge.EVENT_BUS.register(new EventLoader());//×¢²áÊÂ¼þ¼àÌýÆ÷
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	new Key();
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
