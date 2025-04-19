package com.dogwhistlemod.components;

import com.dogwhistlemod.Onansdogwhistlemod;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Components{

    public static void initialize() {
        Onansdogwhistlemod.LOGGER.info("Registering {} components", Onansdogwhistlemod.MOD_ID);


    }

    public static final ComponentType<Short> SIT_OR_STAND = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Onansdogwhistlemod.MOD_ID, "sitorstand"),
            ComponentType.<Short>builder().codec(Codec.SHORT).build()
    );
}
