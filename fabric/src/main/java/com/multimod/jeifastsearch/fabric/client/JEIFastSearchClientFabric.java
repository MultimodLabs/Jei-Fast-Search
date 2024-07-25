package com.multimod.jeifastsearch.fabric.client;

import com.multimod.jeifastsearch.JEIFastSearchModClient;
import com.multimod.jeifastsearch.fabric.client.jei.JEIFastSearchJEIPlugin;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.client.ClientRawInputEvent;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.world.item.ItemStack;

public class JEIFastSearchClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        new JEIFastSearchModClient();

        // Listen Key Pressed
        ClientRawInputEvent.KEY_PRESSED.register((client, keyCode, scanCode, action, modifiers) -> {
            // Did we hovered less than 3 ticks ?
            if (!JEIFastSearchModClient.lastRenderedStack.isEmpty() && client.level != null
                    && client.level.getGameTime()
                            - JEIFastSearchModClient.lastTooltipTime < JEIFastSearchModClient.THREE_TICKS) {
                if (JEIFastSearchModClient.KEY.matches(keyCode, scanCode)) {
                    writeToJEISearchBar(JEIFastSearchModClient.lastRenderedStack);
                }
            }
            return EventResult.pass();
        });
    }

    private void writeToJEISearchBar(ItemStack item) {
        JEIFastSearchJEIPlugin.getRuntime().getIngredientFilter()
                .setFilterText(item.getHoverName().getString());
    }
}
