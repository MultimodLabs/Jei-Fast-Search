package com.multimod.jeifastsearch.forge;

import com.multimod.jeifastsearch.JEIFastSearchMod;
import com.multimod.jeifastsearch.JEIFastSearchModClient;
import com.multimod.jeifastsearch.forge.client.jei.JEIFastSearchJEIPlugin;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.client.ClientRawInputEvent;
import dev.architectury.platform.forge.EventBuses;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(JEIFastSearchMod.MOD_ID)
public class JEIFastSearchModForge {

  public JEIFastSearchModForge() {
    // Submit our event bus to let architectury register our content on the right
    // time
    EventBuses.registerModEventBus(JEIFastSearchMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
    FMLJavaModLoadingContext.get().getModEventBus().register(this);
    JEIFastSearchMod.init();
    // set the mod client only
    DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> JEIFastSearchModClient::new);

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
