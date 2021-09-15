package com.dogwhistlemod;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.dogwhistlemod.items.DogWhistleItem;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("onansdogwhistlemod")
public class DogWhistleModMain
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "onansdogwhistlemod";
    
    public DogWhistleModMain() {

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
    	public static final Item DOG_WHISTLE = new DogWhistleItem(new Item.Properties().stacksTo(64).tab(CreativeModeTab.TAB_TOOLS)).setRegistryName(DogWhistleModMain.MODID+":dog_whistle");;
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Item> event) {
            event.getRegistry().register(DOG_WHISTLE);
        }
    }
}
