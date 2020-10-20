// Copyright (C) RelaperCrystal 2020.
// Licensed under GNU General Public License version 3.

package io.github.relapercrystal.moreelements.materials;

import io.github.relapercrystal.moreelements.items.ModItems;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class EssentialMaterial implements ToolMaterial {
    public static final EssentialMaterial INSTANCE = new EssentialMaterial();

    @Override
    public int getDurability() {
        return 2130;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 9.0F;
    }

    @Override
    public float getAttackDamage() {
        return 4.5F;
    }

    @Override
    public int getMiningLevel() {
        return 5;
    }

    @Override
    public int getEnchantability() {
        return 18;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.ESSENTIAL);
    }
    
}
