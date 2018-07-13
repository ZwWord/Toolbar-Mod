package gzz_lin.toolbar;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;

public class ToolbarRender {
	private static ResourceLocation toolbarTextures = new ResourceLocation("textures/gui/widgets.png");
	private static ResourceLocation iconTextures = new ResourceLocation("textures/gui/icons.png");
	public static Minecraft mc = Minecraft.getMinecraft();

	private static void bind(ResourceLocation res) {
		mc.getTextureManager().bindTexture(res);
	}

	private static int getWidth() {
		return new ScaledResolution(mc).getScaledWidth();
	}

	private static int getHeight() {
		return new ScaledResolution(mc).getScaledHeight();
	}

	private static GuiIngame getGui() {
		return mc.ingameGUI;
	}

	public void renderHotbar() {

		bind(toolbarTextures);
		GuiIngame gui = getGui();
		int width = getWidth() / 2;
		int height = getHeight();
		int toolWidth = 182;
		int toolHeight = 22;
		int select = ToolbarUtil.getSelect();

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		gui.drawTexturedModalRect(width - toolWidth / 2, height - toolHeight * 2 - 1, 0, 0, toolWidth, toolHeight);
		gui.drawTexturedModalRect(width - toolWidth / 2, height - toolHeight - 1, 0, 0, toolWidth, toolHeight);
		int c = select > 8 ? select - 27 : select;
		gui.drawTexturedModalRect(width - toolWidth / 2 - 1 + c * 20, height - (select > 8 ? 2 : 1) * toolHeight - 1.0f,
				0, 23, 24, 24);
		ItemStack leftItem = mc.player.getHeldItem(EnumHand.OFF_HAND);
		if (!leftItem.isEmpty()) {
			gui.drawTexturedModalRect(width - toolWidth / 2 - 30, height - toolHeight - 1, 24, 23, 24, 24);
		}
		// 绘制物品开始
		GlStateManager.enableRescaleNormal();
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		RenderHelper.enableGUIStandardItemLighting();
		// 物品绘制start
		RenderItem renderItem = mc.getRenderItem();
		FontRenderer fontRenderer = mc.fontRenderer;
		for (int i = 0; i < 18; i++) {
			int index = i;
			if (i > 8) {
				index = i + 18;
			}
			ItemStack item = mc.player.inventory.mainInventory.get(index);
			
			int x = width - toolWidth / 2 + 3 + (i % 9) * 20;
			int y = height - toolHeight * (((int) i / 9) + 1) + 2;
			renderItem.renderItemIntoGUI(item, x, y);
			int count = item.getCount();
			if (count > 1) {
				GlStateManager.translate(0f, 0f, 275f);
				String text = "" + count;
				int textW = fontRenderer.getStringWidth(text);
				fontRenderer.drawStringWithShadow(text, x + 17 - textW, y + 9, 0xFFFFFF);
				GlStateManager.translate(0f, 0f, -275f);
			}
		}
		if (leftItem != null) {
			int x = width - toolWidth / 2 - 27;
			int y = height - toolHeight + 2;
			renderItem.renderItemIntoGUI(leftItem, x, y);
			int count = leftItem.getCount();
			if (count > 1) {
				GlStateManager.translate(0f, 0f, 275f);
				String text = "" + count;
				int textW = fontRenderer.getStringWidth(text);
				fontRenderer.drawStringWithShadow(text, x + 17 - textW, y + 9, 0xFFFFFF);
				GlStateManager.translate(0f, 0f, -275f);
			}
		}
		// 物品绘制end
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.disableBlend();
		// 绘制物品结束
	}

	public void renderExperience() {
		bind(iconTextures);
		GuiIngame gui = getGui();
		int width = getWidth();
		int height = getHeight();

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disableBlend();

		if (mc.playerController.gameIsSurvivalOrAdventure()) {
			mc.mcProfiler.startSection("expBar");
			int cap = mc.player.xpBarCap();
			int left = width / 2 - 91;

			if (cap > 0) {
				short barWidth = 182;
				int filled = (int) (mc.player.experience * (float) (barWidth + 1));
				int top = height - 52;
				gui.drawTexturedModalRect(left, top, 0, 64, barWidth, 5);

				if (filled > 0) {
					gui.drawTexturedModalRect(left, top, 0, 69, filled, 5);
				}
			}

			mc.mcProfiler.endSection();

			if (mc.playerController.gameIsSurvivalOrAdventure() && mc.player.experienceLevel > 0) {
				mc.mcProfiler.startSection("expLevel");
				boolean flag1 = false;
				int color = flag1 ? 16777215 : 8453920;
				String text = "" + mc.player.experienceLevel;
				int x = (width - mc.fontRenderer.getStringWidth(text)) / 2;
				int y = height - 55;
				mc.fontRenderer.drawString(text, x + 1, y, 0);
				mc.fontRenderer.drawString(text, x - 1, y, 0);
				mc.fontRenderer.drawString(text, x, y + 1, 0);
				mc.fontRenderer.drawString(text, x, y - 1, 0);
				mc.fontRenderer.drawString(text, x, y, color);
				mc.mcProfiler.endSection();
			}
		}
		GlStateManager.enableBlend();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	}
}
