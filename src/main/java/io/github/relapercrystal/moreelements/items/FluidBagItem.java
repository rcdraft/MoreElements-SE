// Copyright (C) RelaperCrystal 2020.
// Licensed under GNU General Public License version 3.

package io.github.relapercrystal.moreelements.items;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidDrainable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.RaycastContext;

public class FluidBagItem extends Item{
    public FluidBagItem(Settings settings) {
        super(settings);
    }
    
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        HitResult hit = raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);

        if (hit.getType() == HitResult.Type.MISS) {
            return TypedActionResult.pass(stack);
         } else if (hit.getType() != HitResult.Type.BLOCK) {
            return TypedActionResult.pass(stack);
         } else {
            BlockHitResult blockHitResult = (BlockHitResult)hit;
            BlockPos blockPos = blockHitResult.getBlockPos();
            Direction direction = blockHitResult.getSide();
            BlockPos blockPos2 = blockPos.offset(direction);

            if (world.canPlayerModifyAt(user, blockPos) && user.canPlaceOn(blockPos2, direction, stack)) {
                BlockState blockState;
                blockState = world.getBlockState(blockPos);

                if (blockState.getBlock() instanceof FluidDrainable) {
                    Fluid fluid = ((FluidDrainable)blockState.getBlock()).tryDrainFluid(world, blockPos, blockState);
                    if (fluid != Fluids.EMPTY) {
                        if (fluid != Fluids.WATER) {
                            user.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1.0F, 1.0F);
                            stack.decrement(1);
                        }
                        user.incrementStat(Stats.USED.getOrCreateStat(this));
                        user.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
                        ItemStack itemStack2 = ItemUsage.method_30012(stack, user, new ItemStack(ModItems.FILLED_WATER_BAG));
                        if (!world.isClient) {
                           Criteria.FILLED_BUCKET.trigger((ServerPlayerEntity)user, new ItemStack(this));
                        }
                        return TypedActionResult.method_29237(itemStack2, world.isClient());
                }
                
            }
         }

        
        return new TypedActionResult<>(ActionResult.SUCCESS, user.getStackInHand(hand));
    }
}}
