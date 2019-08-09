package me.creepinson.mod.entity.renderer;

import java.util.Map;

import com.google.common.collect.Maps;

import me.creepinson.mod.entity.EntityTrojanHorse;
import me.creepinson.mod.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTrojanHorse extends RenderLiving<EntityTrojanHorse>
{
    private static final Map<String, ResourceLocation> LAYERED_LOCATION_CACHE = Maps.<String, ResourceLocation>newHashMap();

    public RenderTrojanHorse(RenderManager p_i47205_1_)
    {
        super(p_i47205_1_, new ModelHorse(), 0.75F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityTrojanHorse entity)
    {
        ResourceLocation resourcelocation = new ResourceLocation(Reference.MODID, "textures/entity/trojan_horse.png");


        return resourcelocation;
    }
}