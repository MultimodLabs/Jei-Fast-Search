package com.multimod.jeifastsearch.forge.client.jei;

import com.multimod.jeifastsearch.JEIFastSearchMod;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class JEIFastSearchJEIPlugin implements IModPlugin {
  private static IJeiRuntime runtime;
  private static final ResourceLocation PLUGIN_ID = new ResourceLocation(JEIFastSearchMod.MOD_ID, "jei_plugin");

  @Override
  public void onRuntimeAvailable(IJeiRuntime runtime) {
    JEIFastSearchJEIPlugin.runtime = runtime;
  }

  public static IJeiRuntime getRuntime() {
    return runtime;
  }

  @Override
  public ResourceLocation getPluginUid() {
    return PLUGIN_ID;
  }

}
