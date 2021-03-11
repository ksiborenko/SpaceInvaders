package com.space.sprites.bullets;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.space.sprites.Ship;

public class ShipBullet extends AbstractBullet {


    public ShipBullet(RayHandler rayHandler, Ship ship) {
        super();
        this.bulletPosition.set(ship.getShipPosition().x + (float) ship.getShipTexture().getWidth() / 2,
                ship.getShipPosition().y + (float) ship.getShipTexture().getHeight() / 2);

        this.bulletLight = new PointLight(rayHandler, 20, Color.RED, 60,
                this.bulletPosition.x, this.bulletPosition.y);

        this.bulletRectangle.set(ship.getShipPosition().x, ship.getShipPosition().y,
                bulletTexture.getWidth(), bulletTexture.getHeight());
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(this.bulletTexture, this.bulletPosition.x, this.bulletPosition.y);
        this.bulletPositionUpdate(Gdx.graphics.getDeltaTime());
        this.bulletRectangleUpdate();
        this.bulletLightUpdate();
    }

    @Override
    protected void bulletPositionUpdate(float delta) {
        this.bulletPosition.y += this.bulletSpeed * delta;
    }

    @Override
    protected void bulletRectangleUpdate() {
        this.bulletRectangle.setPosition(this.bulletPosition);
    }

    @Override
    protected void bulletLightUpdate() {
        this.bulletLight.setPosition(this.bulletPosition.x + (float) this.bulletTexture.getWidth() / 2,
                this.bulletPosition.y + (float) this.bulletTexture.getHeight() / 6);
    }
}
