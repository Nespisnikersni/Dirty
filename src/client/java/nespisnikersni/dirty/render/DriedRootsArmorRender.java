package nespisnikersni.dirty.render;

import nespisnikersni.dirty.items.DriedRootsArmorItem;
import net.minecraft.entity.Entity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class DriedRootsArmorRender extends GeoArmorRenderer<DriedRootsArmorItem> {
    public DriedRootsArmorRender(GeoModel model) {
        super(model);
    }
    @Override
    public void setAngles(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }

}
