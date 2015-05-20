package edu.chl.Game.view.screens;

import edu.chl.Game.controller.State;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import edu.chl.Game.controller.RefreshTimer;
import edu.chl.Game.view.Frame;
import edu.chl.Game.view.screens.tween.ActorAccessor;

/**
 * LibGDX
 * Main menu for the game
 * @author Martin Tran
 *
 */
public class MainMenu implements Screen {
	
	private Stage stage;
	private Skin skin;
	private Table table;
	private TweenManager tweenManager;

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		//Clear the screen with the given color, in this case Black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
		
		tweenManager.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		//stage.getViewport().update(width, height, false);
		//table.invalidateHierarchy();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"), new TextureAtlas("ui/atlas.pack"));
		
		table = new Table(skin);
		table.setFillParent(true);
		
		//Set the title
		Label title = new Label(Frame.title, skin, "big");
		title.setFontScale(2);
		
		//Creating StartButton
		TextButton buttonStart = new TextButton("Play", skin, "big");
		buttonStart.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				//Animation: Moves up & fading
				Timeline.createParallel().beginParallel()
				.push(Tween.to(table, ActorAccessor.ALPHA, .75f).target(0))
				.push(Tween.to(table, ActorAccessor.Y, .75f).target(table.getY() -50)
						.setCallback(new TweenCallback(){
							@Override
							public void onEvent(int type, BaseTween<?> source) {
								RefreshTimer.state = State.MAP;
								((Game) Gdx.app.getApplicationListener()).dispose();
							}
						}))
					.end().start(tweenManager);
			}
		});
		buttonStart.pad(10);
		
		//OptionButton
		TextButton buttonOption = new TextButton("Options", skin);
		buttonOption.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				//Animation: Moves up
				stage.addAction(sequence(moveTo(0, stage.getHeight() -50, .5f), run(new Runnable(){
					@Override
					public void run() {
						((Game) Gdx.app.getApplicationListener()).setScreen(new IntroSplash());
					}	
				})));
			}
		});
		buttonOption.pad(15);
		
		//ExitButton
		TextButton buttonExit = new TextButton("Exit", skin, "big");
		buttonExit.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				//Animation: Move down & fading
				Timeline.createParallel().beginParallel()
					.push(Tween.to(table, ActorAccessor.ALPHA, .75f).target(0))
					.push(Tween.to(table, ActorAccessor.Y, .75f).target(table.getY() +50)
							.setCallback(new TweenCallback(){
								@Override
								public void onEvent(int type, BaseTween<?> source) {
									Gdx.app.exit();
								}
							}))
						.end().start(tweenManager);
			}
		});
		buttonExit.pad(10);
		
		//Add and organizes the objects
		table.add(title).spaceBottom(50).row();
		table.add(buttonStart).spaceBottom(15).row();
		table.add(buttonOption).spaceBottom(15).row();
		table.add(buttonExit);
		
		stage.addActor(table);
		
		//Animations
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class,  new ActorAccessor());
		
		//Title color animation
		Timeline.createSequence().beginSequence()
			.push(Tween.to(title,  ActorAccessor.RGB, .5f).target(0,0,1))
			.push(Tween.to(title,  ActorAccessor.RGB, .5f).target(0,1,0))
			.push(Tween.to(title,  ActorAccessor.RGB, .5f).target(1,0,0))
			.push(Tween.to(title,  ActorAccessor.RGB, .5f).target(1,1,0))
			.push(Tween.to(title,  ActorAccessor.RGB, .5f).target(0,1,1))
			.push(Tween.to(title,  ActorAccessor.RGB, .5f).target(1,0,1))
			.push(Tween.to(title,  ActorAccessor.RGB, .5f).target(1,1,1))
			.end().repeat(Tween.INFINITY, 0).start(tweenManager);
		
		//Title and buttons fade-in
		Timeline.createSequence().beginSequence()
			.push(Tween.set(buttonStart, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(buttonOption, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(buttonExit, ActorAccessor.ALPHA).target(0))
			.push(Tween.from(title, ActorAccessor.ALPHA, .25f).target(0))
			.push(Tween.to(buttonStart, ActorAccessor.ALPHA, .25f).target(1))
			.push(Tween.to(buttonOption, ActorAccessor.ALPHA, .25f).target(1))
			.push(Tween.to(buttonExit, ActorAccessor.ALPHA, .25f).target(1))
			.end().start(tweenManager);
		
		//table fade-in
		Tween.from(table, ActorAccessor.ALPHA, .75f).target(0).start(tweenManager);
		Tween.from(table, ActorAccessor.Y, .75f).target(Gdx.graphics.getHeight()/8).start(tweenManager);
		
		tweenManager.update(Gdx.graphics.getDeltaTime());
	}
}
