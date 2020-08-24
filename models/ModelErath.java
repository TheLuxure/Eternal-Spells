// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelErath extends EntityModel<Entity> {
	private final ModelRenderer head;
	private final ModelRenderer stick1;
	private final ModelRenderer stick2;
	private final ModelRenderer stick3;
	private final ModelRenderer stick4;
	private final ModelRenderer stick5;
	private final ModelRenderer stick6;
	private final ModelRenderer stick7;
	private final ModelRenderer stick8;
	private final ModelRenderer stick9;
	private final ModelRenderer stick10;
	private final ModelRenderer stick11;
	private final ModelRenderer stick12;

	public ModelErath() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-4.0F, 3.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		stick1 = new ModelRenderer(this);
		stick1.setRotationPoint(7.0F, -2.0F, -7.0F);
		stick1.setTextureOffset(8, 36).addBox(-15.0F, 7.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		stick2 = new ModelRenderer(this);
		stick2.setRotationPoint(-7.0F, -2.0F, -7.0F);
		stick2.setTextureOffset(0, 36).addBox(13.0F, 7.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		stick3 = new ModelRenderer(this);
		stick3.setRotationPoint(-7.0F, -2.0F, 7.0F);
		stick3.setTextureOffset(32, 0).addBox(13.0F, 7.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		stick4 = new ModelRenderer(this);
		stick4.setRotationPoint(7.0F, -2.0F, 7.0F);
		stick4.setTextureOffset(16, 32).addBox(-15.0F, 7.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		stick5 = new ModelRenderer(this);
		stick5.setRotationPoint(5.0F, 2.0F, -5.0F);
		stick5.setTextureOffset(30, 30).addBox(-11.0F, 7.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		stick6 = new ModelRenderer(this);
		stick6.setRotationPoint(-5.0F, 2.0F, -5.0F);
		stick6.setTextureOffset(28, 16).addBox(9.0F, 7.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		stick7 = new ModelRenderer(this);
		stick7.setRotationPoint(-5.0F, 2.0F, 5.0F);
		stick7.setTextureOffset(8, 26).addBox(9.0F, 7.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		stick8 = new ModelRenderer(this);
		stick8.setRotationPoint(5.0F, 2.0F, 5.0F);
		stick8.setTextureOffset(0, 26).addBox(-11.0F, 7.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		stick9 = new ModelRenderer(this);
		stick9.setRotationPoint(3.0F, 10.0F, -3.0F);
		stick9.setTextureOffset(22, 24).addBox(-6.0F, -11.0F, 0.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		stick10 = new ModelRenderer(this);
		stick10.setRotationPoint(-3.0F, 10.0F, -3.0F);
		stick10.setTextureOffset(16, 16).addBox(5.0F, -11.0F, 0.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		stick11 = new ModelRenderer(this);
		stick11.setRotationPoint(-3.0F, 10.0F, 3.0F);
		stick11.setTextureOffset(8, 16).addBox(5.0F, -11.0F, -2.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		stick12 = new ModelRenderer(this);
		stick12.setRotationPoint(3.0F, 10.0F, 3.0F);
		stick12.setTextureOffset(0, 16).addBox(-6.0F, -11.0F, -2.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		stick1.render(matrixStack, buffer, packedLight, packedOverlay);
		stick2.render(matrixStack, buffer, packedLight, packedOverlay);
		stick3.render(matrixStack, buffer, packedLight, packedOverlay);
		stick4.render(matrixStack, buffer, packedLight, packedOverlay);
		stick5.render(matrixStack, buffer, packedLight, packedOverlay);
		stick6.render(matrixStack, buffer, packedLight, packedOverlay);
		stick7.render(matrixStack, buffer, packedLight, packedOverlay);
		stick8.render(matrixStack, buffer, packedLight, packedOverlay);
		stick9.render(matrixStack, buffer, packedLight, packedOverlay);
		stick10.render(matrixStack, buffer, packedLight, packedOverlay);
		stick11.render(matrixStack, buffer, packedLight, packedOverlay);
		stick12.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}