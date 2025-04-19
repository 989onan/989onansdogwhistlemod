package com.dogwhistlemod;

import com.dogwhistlemod.components.Components;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipData;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

public class Onansdogwhistlemod implements ModInitializer {
	public static final String MOD_ID = "onansdogwhistlemod";

	public static final Item DOG_WHISTLE_REGISTRY = register("dog_whistle", com.dogwhistlemod.items.DogWhistleItem::new, new Item.Settings().maxCount(1)
					.component(Components.SIT_OR_STAND, (short) 0),ItemGroups.TOOLS);
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Hello from dog whistle mod fabric!\n    by: 989onan");
		Components.initialize();
	}

	public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings, RegistryKey<ItemGroup> group) {
		// Create the item key.
		RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name));

		// Create the item instance.
		Item item = itemFactory.apply(settings.registryKey(itemKey));



		// Register the item.
		Registry.register(Registries.ITEM, itemKey, item);

		//put it into the specified group.
		ItemGroupEvents.modifyEntriesEvent(group).register((itemGroup) -> itemGroup.add(item));
		return item;
	}
}