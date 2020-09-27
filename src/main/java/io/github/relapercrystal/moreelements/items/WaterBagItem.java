package io.github.relapercrystal.moreelements.items;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidFillable;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.RaycastContext;

public class WaterBagItem extends Item{
    public WaterBagItem(Settings settings) {
        super(settings);
    }
    
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        HitResult hit = raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);

        BlockHitResult blockHitResult = (BlockHitResult) hit;
        BlockPos blockPos = blockHitResult.getBlockPos();
        net.minecraft.util.math.Direction direction = blockHitResult.getSide();
        BlockPos blockPos2 = blockPos.offset(direction);
        BlockState blockState = world.getBlockState(blockPos);
        BlockPos blockPos3 = blockState.getBlock() instanceof FluidFillable ? blockPos
                : blockPos2;

        if (world.canPlayerModifyAt(user, blockPos) && user.canPlaceOn(blockPos2, direction, stack)) {
            
           
            if (this.placeFluid(user, world, blockPos3, blockHitResult)) {
                if (user instanceof ServerPlayerEntity) {
                    Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity) user, blockPos3, stack);
                }

                user.incrementStat(Stats.USED.getOrCreateStat(this));
                return TypedActionResult.method_29237(this.getEmptiedStack(stack, user), world.isClient());
            } else {
                return TypedActionResult.fail(stack);
               }
        } else {
            return TypedActionResult.fail(user.getStackInHand(hand));
        }
    }

    protected ItemStack getEmptiedStack(ItemStack stack, PlayerEntity player) {
        return !player.abilities.creativeMode ? new ItemStack(ModItems.EMPTY_WATER_BAG) : stack;
     }

    protected void playEmptyingSound(PlayerEntity player, WorldAccess world, BlockPos pos) {
        SoundEvent soundEvent = SoundEvents.ITEM_BUCKET_EMPTY;
        world.playSound(player, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
     }

    public boolean placeFluid(PlayerEntity player, World world, BlockPos pos, BlockHitResult blockHitResult) {
        BlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        Material material = blockState.getMaterial();
        boolean bl = blockState.canBucketPlace(Fluids.WATER);
        boolean bl2 = blockState.isAir() || bl || block instanceof FluidFillable && ((FluidFillable)block).canFillWithFluid(world, pos, blockState, Fluids.WATER);

        if (!bl2) {
            return blockHitResult != null && this.placeFluid(player, world, blockHitResult.getBlockPos().offset(blockHitResult.getSide()), (BlockHitResult)null);
         } else if (world.getDimension().isUltrawarm() && Fluids.WATER.isIn(FluidTags.WATER)) {
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();
            world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);

            for(int l = 0; l < 8; ++l) {
               world.addParticle(ParticleTypes.LARGE_SMOKE, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0D, 0.0D, 0.0D);
            }

            return true;
         } else if (block instanceof FluidFillable) {
            ((FluidFillable)block).tryFillWithFluid(world, pos, blockState, ((FlowableFluid)Fluids.WATER).getStill(false));
            this.playEmptyingSound(player, world, pos);
            return true;
         } else {
            if (!world.isClient && bl && !material.isLiquid()) {
               world.breakBlock(pos, true);
            }

            if (!world.setBlockState(pos, Fluids.WATER.getDefaultState().getBlockState(), 11) && !blockState.getFluidState().isStill()) {
               return false;
            } else {
               this.playEmptyingSound(player, world, pos);
               return true;
            }
         }
    }
}
