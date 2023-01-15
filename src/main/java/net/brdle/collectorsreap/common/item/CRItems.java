package net.brdle.collectorsreap.common.item;

import net.brdle.collectorsreap.Util;
import net.brdle.collectorsreap.CollectorsReap;
import net.brdle.collectorsreap.common.block.CRBlocks;
import net.brdle.collectorsreap.common.item.food.Nutrition;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Supplier;

public class CRItems extends ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CollectorsReap.MODID);

    public static final RegistryObject<Item> PORTOBELLO_COLONY = registerBlock(CRBlocks.PORTOBELLO_COLONY);
    public static final RegistryObject<Item> PORTOBELLO = registerBlock(CRBlocks.PORTOBELLO);
    public static final RegistryObject<Item> BAKED_PORTOBELLO_CAP = registerFood("baked_portobello_cap",
        Nutrition.BAKED_PORTOBELLO_CAP);
    public static final RegistryObject<Item> STUFFED_PORTOBELLO_CAP = registerFood("stuffed_portobello_cap",
        Nutrition.STUFFED_PORTOBELLO_CAP);
    public static final RegistryObject<Item> PORTOBELLO_BURGER = registerFood("portobello_burger",
        Nutrition.PORTOBELLO_BURGER);
    public static final RegistryObject<Item> PORTOBELLO_WRAP = registerFood("portobello_wrap",
        Nutrition.PORTOBELLO_WRAP);
    public static final RegistryObject<Item> PORTOBELLO_RICE_SOUP = registerItem("portobello_rice_soup",
        () -> new ConsumableItem(bowlFoodItem(Nutrition.PORTOBELLO_RICE_SOUP)));
    public static final RegistryObject<Item> PORTOBELLO_RISOTTO = registerItem("portobello_risotto",
        () -> new ConsumableItem(bowlFoodItem(Nutrition.PORTOBELLO_RISOTTO)));
    public static final RegistryObject<Item> PORTOBELLO_PASTA = registerItem("portobello_pasta",
        () -> new ConsumableItem(bowlFoodItem(Nutrition.PORTOBELLO_PASTA)));
    public static final RegistryObject<Item> PORTOBELLO_QUICHE = registerBlock(CRBlocks.PORTOBELLO_QUICHE);
    public static final RegistryObject<Item> PORTOBELLO_QUICHE_SLICE = registerFood("portobello_quiche_slice",
        Nutrition.PORTOBELLO_QUICHE_SLICE);
    public static final RegistryObject<Item> POMEGRANATE = registerFood("pomegranate",
        Nutrition.POMEGRANATE);
    public static final RegistryObject<Item> POMEGRANATE_SEEDS = registerItem("pomegranate_seeds",
        () -> new BlockItem(CRBlocks.POMEGRANATE_BUSH.get(), (new Item.Properties()
            .tab(FarmersDelight.CREATIVE_TAB)
            .food(Nutrition.POMEGRANATE_SEEDS))));
    public static final RegistryObject<Item> LIME_SEEDS = registerBlock("lime_seeds",
        CRBlocks.SMALL_LIME_BUSH);
    public static final RegistryObject<Item> LIME = registerFood("lime",
        Nutrition.LIME);
    public static final RegistryObject<Item> LIME_SLICE = registerFood("lime_slice",
        Nutrition.LIME_SLICE);
    public static final RegistryObject<Item> CANDIED_LIME = registerFood("candied_lime",
        Nutrition.CANDIED_LIME);
    public static final RegistryObject<Item> LIME_PIE = registerBlock(CRBlocks.LIME_PIE);
    public static final RegistryObject<Item> LIME_PIE_SLICE = registerFood("lime_pie_slice",
        Nutrition.LIME_PIE_SLICE);
    public static final RegistryObject<Item> LIME_POPSICLE = registerFood("lime_popsicle",
        Nutrition.LIME_POPSICLE);
    public static final RegistryObject<Item> LIME_COOKIE = registerFood("lime_cookie",
        Nutrition.LIME_COOKIE);
    public static final RegistryObject<Item> HONEY_LIME_CHICKEN = registerFood("honey_lime_chicken",
        Nutrition.HONEY_LIME_CHICKEN);
    public static final RegistryObject<Item> MEDITERRANEAN_SALMON = registerFood("mediterranean_salmon",
        Nutrition.MEDITERRANEAN_SALMON);
    public static final RegistryObject<Item> SALMON_TARTARE = registerFood("salmon_tartare",
        Nutrition.SALMON_TARTARE);
    public static final RegistryObject<Item> POTATO_FRITTERS = registerFood("potato_fritters",
        Nutrition.POTATO_FRITTERS);
    public static final RegistryObject<Item> COD_CEVICHE = registerFood("cod_ceviche",
        Nutrition.COD_CEVICHE);
    public static final RegistryObject<Item> LIMEADE = registerItem("limeade", () ->
        new DrinkableItem(drinkItem().food(Nutrition.LIMEADE), false, false));
    public static final RegistryObject<Item> BERRY_LIMEADE = registerItem("berry_limeade", () ->
        new DrinkableItem(drinkItem().food(Nutrition.BERRY_LIMEADE), false, false));

    // Sets Creative Tab
    public static RegistryObject<Item> registerBlock(RegistryObject<Block> block) {
        return registerBlock(Util.name(block), block);
    }

    // Sets Creative Tab
    public static RegistryObject<Item> registerBlock(String name, RegistryObject<Block> block) {
        return registerItem(name, () -> new BlockItem(block.get(), (new Item.Properties().tab(FarmersDelight.CREATIVE_TAB))));
    }

    // Creative tab should be set before calling this function
    public static RegistryObject<Item> registerFood(String name, FoodProperties properties) {
        return registerItem(name, () -> new Item((new Item.Properties()
            .tab(FarmersDelight.CREATIVE_TAB).food(properties))));
    }

    // Creative tab should be set before calling this function
    public static RegistryObject<Item> registerItem(String name, Supplier<Item> item) {
        return ITEMS.register(name, item);
    }

    public static void create(IEventBus bus) {
        ITEMS.register(bus);
    }
}