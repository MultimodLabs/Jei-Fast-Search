package com.multimod.jeifastsearch;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.architectury.platform.Platform;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class JEIFastSearchMod {

  public static final String MOD_ID = "jei_fast_search";

  public static JEIFastSearchConfig CONFIG = new JEIFastSearchConfig();

  public static List<BiPredicate<BlockEntity, ItemStack>> BLOCK_CHECKERS = new ArrayList<>();

  public static void init() {

    File file = new File(Platform.getConfigFolder() + File.separator + MOD_ID + ".json");
    if (!file.exists()) {
      createConfig(file);
    }
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    try {
      FileReader reader = new FileReader(file);
      CONFIG = gson.fromJson(reader, JEIFastSearchConfig.class);
      reader.close();
    } catch (Exception e) {
      e.printStackTrace();
      createConfig(file);
    }
  }

  private static void createConfig(File file) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    try {
      FileWriter fileWriter = new FileWriter(file);
      gson.toJson(CONFIG, fileWriter);
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
