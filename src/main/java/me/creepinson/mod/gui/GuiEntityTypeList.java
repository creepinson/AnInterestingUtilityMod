/**
 * @author Creepinson
 * 
 */
package me.creepinson.mod.gui;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.lwjgl.input.Mouse;

import com.google.common.collect.Lists;

import me.creepinson.mod.gui.util.ICustomScrollListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiEntityTypeList extends GuiScreen implements ICustomScrollListener {
	private List<String> list;
	public HashMap<String, Class<? extends EntityLivingBase>> data = new HashMap<String, Class<? extends EntityLivingBase>>();

	public List<String> selected;
	public GuiCustomScroll scroll;

	private int x;
	private int y;

	public GuiEntityTypeList(int x, int y, List<Class<? extends Entity>> selected) {
		this(x, y);
		// this.selected = selected;

	}

	public GuiEntityTypeList(int x, int y) {
		this.width = 175;
		this.height = 310;
		this.x = x;
		this.y = y;
		this.selected = new ArrayList<String>();
		Set<ResourceLocation> mapping = EntityList.getEntityNameList();
		for (ResourceLocation name : mapping) {
			Class<? extends Entity> c = EntityList.getClass(name);
			try {
				if (c != null && EntityLiving.class.isAssignableFrom(c)
						&& c.getConstructor(new Class[] { World.class }) != null
						&& !Modifier.isAbstract(c.getModifiers())) {
					data.put(name.toString(), c.asSubclass(EntityLivingBase.class));
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
			}
		}
		list = new ArrayList<String>(data.keySet());
		list.add("Player");
		Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
		this.setWorldAndResolution(Minecraft.getMinecraft(), 175, height);
		
	}

	@Override
	public void initGui() {
		super.initGui();
		scroll = new GuiCustomScroll(this, 0, true);
		scroll.setUnsortedList(list);
		scroll.guiLeft = x;
		scroll.guiTop = y;
		scroll.setSize(100, height - 70);

	}

	@Override
	public void customScrollClicked(int i, int j, int k, GuiCustomScroll scroll) {
		selected = new ArrayList<String>(scroll.getSelectedList());
		// initGui();
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		scroll.mouseClicked(mouseX, mouseY, mouseButton);
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.scroll.drawScreen(mouseX, mouseY, partialTicks, Mouse.getEventDWheel());
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

}
