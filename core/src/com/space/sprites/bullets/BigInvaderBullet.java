package com.space.sprites.bullets;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.space.sprites.BigInvader;

public class BigInvaderBullet extends AbstractBullet {


    public BigInvaderBullet(RayHandler rayHandler, BigInvader bigInvader) {
        super();
        this.bulletTexture = new Texture("bigLaser.png");
        int bulletMiddlePositionCorrection = 6;
        this.bulletPosition.set(bigInvader.getBigInvaderPosition().x + (float) bigInvader.getBigInvaderTexture().getWidth() / 2 - bulletMiddlePositionCorrection,
                bigInvader.getBigInvaderPosition().y);
        this.bulletLight = new PointLight(rayHandler, 5, Color.PINK, 60, this.bulletPosition.x + (float) bulletTexture.getWidth() / 2,
                this.bulletPosition.y + (float) this.bulletTexture.getHeight() / 2);
        this.bulletRectangle = new Rectangle(this.bulletPosition.x, this.bulletPosition.y, this.bulletTexture.getWidth(), this.bulletTexture.getHeight());
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(this.bulletTexture, this.bulletPosition.x, this.bulletPosition.y);

    }

    @Override
    public void bulletPositionUpdate(float delta) {
        int speed = 10;
        this.bulletPosition.y -= speed;


    }

    @Override
    public void bulletLightUpdate() {
        this.bulletLight.setPosition(this.bulletPosition.x + (float) this.bulletTexture.getWidth() / 2,
                this.bulletPosition.y + (float) this.bulletTexture.getHeight() / 2);
    }

    @Override
    public void bulletRectangleUpdate() {
        this.bulletRectangle.setPosition(this.bulletPosition);
    }


}
