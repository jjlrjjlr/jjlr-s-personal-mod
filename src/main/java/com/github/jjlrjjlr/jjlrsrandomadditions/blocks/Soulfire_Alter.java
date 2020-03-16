package com.github.jjlrjjlr.jjlrsrandomadditions.blocks;

import java.util.Random;
import com.github.jjlrjjlr.jjlrsrandomadditions.blocks.entities.EntitySoulfireAlter;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class Soulfire_Alter extends Block implements BlockEntityProvider {

    protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0d, 0.0d, 4.0d, 12.0d, 7.0d, 12.0d);
    public static final BooleanProperty LIT = BooleanProperty.of("lit");

    public Soulfire_Alter(Settings settings) {
        super(settings);
        this.setDefaultState(((BlockState)this.stateManager.getDefaultState()).with(LIT, true));
    }
    
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {
        
        if(player.getMainHandStack().getItem() == Items.FLINT_AND_STEEL && !state.get(LIT)){
            world.setBlockState(pos, BlocksRegistry.SOULFIRE_ALTER.getDefaultState().with(LIT, true));
        }

        if(!world.isClient){
        
            Inventory blockEntity = (Inventory)world.getBlockEntity(pos);

             if(!player.getStackInHand(hand).isEmpty()){
                if(blockEntity.getInvStack(0).isEmpty()){
                    blockEntity.setInvStack(0, player.getMainHandStack().copy());
                    player.getMainHandStack().setCount(0);
                    world.getBlockEntity(pos).markDirty();
                }
            } else if(player.getStackInHand(hand).isEmpty()){
                if(!blockEntity.getInvStack(0).isEmpty()){
                    player.inventory.offerOrDrop(world, blockEntity.getInvStack(0));
                    blockEntity.removeInvStack(0);
                    world.getBlockEntity(pos).markDirty();
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void rainTick(World world, BlockPos pos) {
        
        if(world.isSkyVisible(pos)) {
            
            world.setBlockState(pos, BlocksRegistry.SOULFIRE_ALTER.getDefaultState().with(LIT, false));

        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
        return SHAPE;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if((Boolean)state.get(LIT)){
            if(random.nextInt(2) == 0){
                world.playSound((double)((float)pos.getX() + 0.5f), (double)((float)pos.getX() + 0.5f), (double)((float)pos.getX() + 0.5f), SoundEvents.BLOCK_BLASTFURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 0.5f + random.nextFloat(), random.nextFloat() * 0.6f + 0.5f, false);
            }
            //if(state.get(LIT) && random.nextInt(3) == 0){
            //    world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, (double)((float)pos.getX() + 0.5f), (double)((float)pos.getY() + 0.7f), (double)((float)pos.getZ() + 0.5f), (double)((random.nextFloat() / 99.0f) * random.nextInt(2) - 1), (random.nextFloat() * 0.03f), (double)((random.nextFloat() / 99.0f) * random.nextInt(2) - 1));
            //}
        }
    }

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(LIT);
        
    }

	@Override
	public BlockEntity createBlockEntity(BlockView view) {
		return new EntitySoulfireAlter();
	}
}