package com.github.jjlrjjlr.jjlrsrandomadditions.client.block;

import com.github.jjlrjjlr.jjlrsrandomadditions.blocks.entities.EntitySoulfireAlter;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;

/**
 * Render_Soulfire_Alter
 */
@Environment(EnvType.CLIENT)
public class Render_Soulfire_Alter extends BlockEntityRenderer<EntitySoulfireAlter> {

    public Render_Soulfire_Alter(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(EntitySoulfireAlter blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        
        
        
        ItemStack burnitem = blockEntity.getInvStack(0);
        
        if(!burnitem.isEmpty()){
            matrices.push();
            matrices.translate(0.5d, 0.7d, 0.5d);
            matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(75.0f));
            matrices.scale(0.375f, 0.375f, 0.375f);
            int abovelight = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), blockEntity.getPos().up());
            MinecraftClient.getInstance().getItemRenderer().renderItem(burnitem, ModelTransformation.Mode.FIXED, abovelight, overlay, matrices, vertexConsumers);
            matrices.pop();
        }
    }
    
}