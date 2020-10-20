// Copyright (C) RelaperCrystal 2020.
// Licensed under GNU General Public License version 3.

package io.github.relapercrystal.moreelements.items;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class EssentialItem extends Item {
    private static final Logger logger = LogManager.getLogger();
    public EssentialItem(Settings settings) {
        super(settings);
    }
    
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand)
    {
        ItemStack stack = playerEntity.getStackInHand(hand);
        playerEntity.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0F, 1.0F);
        if(world.isClient) {
            playerEntity.sendMessage(new TranslatableText("item.moreelements.essential.break"), false);
        }
        int count = playerEntity.getStackInHand(hand).getCount();
        if(count != 0 && !playerEntity.abilities.creativeMode) {
            stack.setCount(count - 1);
        }
        else {
            logger.info("[MoreElements/EssentialItem] Use but don't seems to decrease!");
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.moreelements.essential.tooltip"));
    }
}