package com.multimod.jeifastsearch;

import org.slf4j.Logger;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.logging.LogUtils;

import dev.architectury.event.events.client.ClientTooltipEvent;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;

public class JEIFastSearchModClient {
    public static final int THREE_TICKS = 3;

    public static final Logger LOGGER = LogUtils.getLogger();

    public static final Minecraft client = Minecraft.getInstance();

    public static KeyMapping KEY = new KeyMapping(
            "key.jei_fast_search.search",
            InputConstants.Type.KEYSYM,
            InputConstants.getKey("key.keyboard.y").getValue(),
            "key.jei_fast_search.category");

    public static ItemStack lastRenderedStack = ItemStack.EMPTY;

    public static long lastTooltipTime = 0;

    public JEIFastSearchModClient() {
        init();
    }

    private static void init() {
        KeyMappingRegistry.register(KEY);
        // Get last hovered itemStack
        ClientTooltipEvent.ITEM.register((stack, lines, flag) -> {
            if (!stack.isEmpty() && Minecraft.getInstance().level != null) {
                lastRenderedStack = stack;
                lastTooltipTime = Minecraft.getInstance().level.getGameTime();
            }
        });
    }
}
