package io.github.relapercrystal.moreelements.items;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import io.github.relapercrystal.moreelements.HighHot;
import io.github.relapercrystal.moreelements.SharedConstants;
import io.github.relapercrystal.moreelements.blocks.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class ModItems {
    private ModItems() {}

    public static final EssentialItem ESSENTIAL = new EssentialItem(new Item.Settings().group(HighHot.CRYSTAL_GROUP));
    public static final FluidBagItem EMPTY_WATER_BAG = new FluidBagItem(new Item.Settings().group(HighHot.CRYSTAL_GROUP).maxCount(8));
    public static final WaterBagItem FILLED_WATER_BAG = new WaterBagItem(new Item.Settings().group(HighHot.CRYSTAL_GROUP).maxCount(4));
    public static final Item PLASTIC_INGOT = new Item(new Item.Settings().group(HighHot.CRYSTAL_GROUP));
    public static final Item PLASTIC_STICK = new Item(new Item.Settings().group(HighHot.CRYSTAL_GROUP));

    public static void initializeEntities() {
        String ns = SharedConstants.NAMESPACE;

        Registry.register(Registry.ITEM, new Identifier(ns, "essential"), ESSENTIAL);
        Registry.register(Registry.ITEM, new Identifier(ns, "essential_block"), new BlockItem(ModBlocks.ESSENTIAL_BLOCK, new Item.Settings().group(HighHot.CRYSTAL_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(ns, "empty_water_bag"), EMPTY_WATER_BAG);
        Registry.register(Registry.ITEM, new Identifier(ns, "filled_water_bag"), FILLED_WATER_BAG);
        Registry.register(Registry.ITEM, new Identifier(ns, "plastic_ingot"), PLASTIC_INGOT);
        Registry.register(Registry.ITEM, new Identifier(ns, "plastic_stick"), PLASTIC_STICK);
    }
}
