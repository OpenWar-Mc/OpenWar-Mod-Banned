package com.openwar.charpy.Utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemLoader {

    private static final String CSV_FILE_PATH = "config/OPbanned/items.csv";
    private static final String CSV_FILE_PATH_ADMIN =  "config/OPbanned/admin.csv";

    public List<String> readAdminsFromCSV() {
        List<String> admin = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH_ADMIN))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                String adminName = line[0];
                admin.add(adminName);
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return admin;
    }
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
