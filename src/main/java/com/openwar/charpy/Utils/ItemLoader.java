package com.openwar.charpy.Utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.lwjgl.Sys;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemLoader {

    private static final String CSV_FILE_PATH = "config/OPbanned/items.csv";


    public List<ItemStack> readItemsFromCSV() {
        List<ItemStack> items = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                String itemName = line[0];
                Item item;
                ItemStack itemStack = null;
                int damageValue = 0;
                if (itemName.contains("$")) {
                    String[] meta = itemName.split("\\$");
                    itemName = meta[0];
                    damageValue = Integer.parseInt(meta[1]);
                    itemStack = new ItemStack(getItemFromName(itemName), 1, damageValue);
                } else {
                    itemStack = new ItemStack(getItemFromName(itemName), 1);
                }
                if (itemStack != null) {
                    items.add(itemStack);
                } else {
                    System.out.println("error for : " + itemName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    private Item getItemFromName(String itemName) {
        ResourceLocation resourceLocation = new ResourceLocation(itemName);
        Item item = ForgeRegistries.ITEMS.getValue(resourceLocation);
        return item;
    }
}
