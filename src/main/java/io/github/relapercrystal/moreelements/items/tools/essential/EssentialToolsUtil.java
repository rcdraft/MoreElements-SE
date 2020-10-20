// Copyright (C) RelaperCrystal 2020.
// Licensed under GNU General Public License version 3.

package io.github.relapercrystal.moreelements.items.tools.essential;

import io.github.relapercrystal.moreelements.HighHot;
import io.github.relapercrystal.moreelements.SharedConstants;
import io.github.relapercrystal.moreelements.materials.EssentialMaterial;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * A util for processing registration of Essential made tools.
 * Do not call directly.
 */
public class EssentialToolsUtil {
    private EssentialToolsUtil() {}

    public static final EssentialSwordItem ESSENTIAL_SWORD = new EssentialSwordItem(EssentialMaterial.INSTANCE, 5, -2.0f, new Item.Settings().group(HighHot.CRYSTAL_GROUP));
    public static final EssentialShovelItem ESSENTIAL_SHOVEL = new EssentialShovelItem(EssentialMaterial.INSTANCE, 1, -1f, new Item.Settings().group(HighHot.CRYSTAL_GROUP));
    public static final EssentialPickaxeItem ESSENTIAL_PICKAXE = new EssentialPickaxeItem(EssentialMaterial.INSTANCE, 1, -1f, new Item.Settings().group(HighHot.CRYSTAL_GROUP));
    public static final EssentialAxeItem ESSENTIAL_AXE = new EssentialAxeItem(EssentialMaterial.INSTANCE, 3, 2, new Item.Settings().group(HighHot.CRYSTAL_GROUP));

    public static void registerTools() {
        Registry.register(Registry.ITEM, new Identifier(SharedConstants.NAMESPACE, "essential_sword"), ESSENTIAL_SWORD);
        Registry.register(Registry.ITEM, new Identifier(SharedConstants.NAMESPACE, "essential_shovel"), ESSENTIAL_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(SharedConstants.NAMESPACE, "essential_pickaxe"), ESSENTIAL_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(SharedConstants.NAMESPACE, "essential_axe"), ESSENTIAL_AXE);
    }
}
