package io.github.relapercrystal.moreelements.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.entity.EntityType;

public class ModBlocks {
    private ModBlocks() {}

    public static final EssentialBlock ESSENTIAL_BLOCK = new EssentialBlock(FabricBlockSettings.of(Material.GLASS).nonOpaque().allowsSpawning(ModBlocks::never).hardness(0.5f).suffocates(ModBlocks::never).blockVision(ModBlocks::never).solidBlock(ModBlocks::never));    

    /**
    * A shortcut to always return {@code false} a context predicate, used as
    * {@code settings.solidBlock(Blocks::never)}.
    */
   private static boolean never(BlockState state, BlockView world, BlockPos pos) {
    return false;
   }
    /**
    * A shortcut to always return {@code false} in a typed context predicate with an
    * {@link EntityType}, used like {@code settings.allowSpawning(Blocks::never)}.
    */
    private static boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
      return false;
   }

    public static void initializeEntities() {
        Registry.register(Registry.BLOCK, new Identifier("moreelements", "essential_block"), ESSENTIAL_BLOCK);
    }
 }

    

