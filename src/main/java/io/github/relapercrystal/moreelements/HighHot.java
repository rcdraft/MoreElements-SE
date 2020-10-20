// Copyright (C) RelaperCrystal 2020.
// Licensed under GNU General Public License version 3.

package io.github.relapercrystal.moreelements;

import io.github.relapercrystal.moreelements.blocks.ModBlocks;
import io.github.relapercrystal.moreelements.items.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class HighHot implements ModInitializer {
    public static final ItemGroup CRYSTAL_GROUP = FabricItemGroupBuilder.build(new Identifier("moreelements","crystal_group"), () -> new ItemStack(ModItems.ESSENTIAL));

    @Override
    public void onInitialize() {
        ModBlocks.initializeEntities();
        ModItems.initializeEntities();

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ESSENTIAL_BLOCK, RenderLayer.getTranslucent());
    }
    
}
