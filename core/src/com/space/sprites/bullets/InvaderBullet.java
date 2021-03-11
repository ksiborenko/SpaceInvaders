package com.space.sprites.bullets;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.space.sprites.Invader;

public class InvaderBullet extends AbstractBullet {


    public InvaderBullet(RayHandler rayHandler, Invader invader) {
        super();
        this.bulletPosition.set(invader.getINVADER_POSITION().x + (float) invader.getInvaderTexture().getWidth() / 2,
                invader.getINVADER_POSITION().y + (float) invader.getInvaderTexture().getHeight() / 2);
        this.bulletLight = new PointLight(rayHandler, 20, Color.RED, 60,
                this.bulletPosition.x, this.bulletPosition.y);
        this.bulletRectangle.set(this.bulletPosition.x, this.bulletPosition.y, this.bulletTexture.getWidth(),
                this.bulletTexture.getHeight());

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(this.bulletTexture, this.bulletPosition.x, this.bulletPosition.y);
        this.bulletPositionUpdate(Gdx.graphics.getDeltaTime());
        this.bulletLightUpdate();
        this.bulletRectangleUpdate();
    }

    @Override
    public void bulletPositionUpdate(float delta) {
        this.bulletPosition.y -= (float) this.bulletSpeed * delta;
    }

    @Override
    public void bulletRectangleUpdate() {
        this.bulletRectangle.setPosition(this.bulletPosition);
    }

    @Override
    public void bulletLightUpdate() {
        this.bulletLight.setPosition(this.bulletPosition.x + (float) this.bulletTexture.getWidth() / 2,
                this.bulletPosition.y + (float) this.bulletTexture.getHeight() / 2);
    }
}
